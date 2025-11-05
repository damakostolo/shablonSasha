package com.example.serving_web_content.service;

import com.example.serving_web_content.dto.EventForm;
import com.example.serving_web_content.dto.RegistrationForm;
import com.example.serving_web_content.entity.Event;
import com.example.serving_web_content.entity.Location;
import com.example.serving_web_content.entity.Participant;
import com.example.serving_web_content.entity.Ticket;
import com.example.serving_web_content.repository.EventRepository;
import com.example.serving_web_content.repository.LocationRepository;
import com.example.serving_web_content.repository.ParticipantRepository;
import com.example.serving_web_content.repository.TicketRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EventService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final ParticipantRepository participantRepository;
    private final TicketRepository ticketRepository;

    public EventService(EventRepository eventRepository,
                        LocationRepository locationRepository,
                        ParticipantRepository participantRepository,
                        TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.participantRepository = participantRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByStartDateTimeAsc();
    }

    @Transactional(readOnly = true)
    public Event getEvent(Long id) {
        return eventRepository.findDetailedById(id)
                .orElseThrow(() -> new IllegalArgumentException("Подію не знайдено"));
    }

    @Transactional
    public Event createEvent(EventForm form) {
        if (form.getTitle() == null || form.getTitle().isBlank()) {
            throw new IllegalArgumentException("Назва події обов'язкова");
        }

        if (form.getStartDateTime() == null || form.getStartDateTime().isBlank()) {
            throw new IllegalArgumentException("Дата та час початку обов'язкові");
        }

        if (form.getTicketPrice() == null) {
            throw new IllegalArgumentException("Вкажіть базову вартість квитка");
        }

        if (form.getCapacity() == null || form.getCapacity() <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути більшою за нуль");
        }

        Location location = resolveLocation(form);

        if (location.getCapacity() != null && form.getCapacity() != null
                && location.getCapacity() < form.getCapacity()) {
            throw new IllegalArgumentException("Місткість події перевищує можливості локації");
        }

        if (location.getId() == null) {
            location = locationRepository.save(location);
        }

        Event event = new Event();
        event.setTitle(form.getTitle());
        event.setDescription(form.getDescription());
        event.setStartDateTime(LocalDateTime.parse(form.getStartDateTime(), DATE_TIME_FORMATTER));
        event.setTicketPrice(form.getTicketPrice());
        event.setCapacity(form.getCapacity());
        event.setLocation(location);

        return eventRepository.save(event);
    }

    @Transactional
    public Ticket registerParticipant(Long eventId, RegistrationForm form) {
        Event event = getEvent(eventId);

        if (form.getFullName() == null || form.getFullName().isBlank()) {
            throw new IllegalArgumentException("Ім'я учасника обов'язкове");
        }

        if (form.getEmail() == null || form.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email учасника обов'язковий");
        }

        BigDecimal ticketPrice = form.getPrice() != null ? form.getPrice() : event.getTicketPrice();

        long registered = ticketRepository.countByEventId(eventId);
        if (registered >= event.getCapacity()) {
            throw new IllegalStateException("На подію більше немає вільних місць");
        }

        Participant participant = participantRepository.findByEmailIgnoreCase(form.getEmail())
                .map(existing -> updateParticipantName(existing, form.getFullName()))
                .orElseGet(() -> createParticipant(form));

        if (ticketRepository.existsByEventIdAndParticipantId(eventId, participant.getId())) {
            throw new IllegalStateException("Цей учасник уже має квиток на подію");
        }

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setParticipant(participant);
        ticket.setPrice(ticketPrice);
        ticket.setPurchaseDateTime(LocalDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);
        event.getTickets().add(savedTicket);
        return savedTicket;
    }

    @Transactional(readOnly = true)
    public List<Location> getLocations() {
        return locationRepository.findAll(Sort.by("name").ascending());
    }

    private Location resolveLocation(EventForm form) {
        if (form.getLocationId() != null) {
            return locationRepository.findById(form.getLocationId())
                    .orElseThrow(() -> new IllegalArgumentException("Оберіть коректну локацію"));
        }

        if (form.getLocationName() == null || form.getLocationName().isBlank()) {
            throw new IllegalArgumentException("Назва локації обов'язкова");
        }

        if (form.getLocationAddress() == null || form.getLocationAddress().isBlank()) {
            throw new IllegalArgumentException("Адреса локації обов'язкова");
        }

        Integer locationCapacity = form.getLocationCapacity() != null ? form.getLocationCapacity() : form.getCapacity();

        Location location = new Location();
        location.setName(form.getLocationName());
        location.setAddress(form.getLocationAddress());
        location.setCapacity(locationCapacity != null && locationCapacity > 0 ? locationCapacity : form.getCapacity());
        return location;
    }

    private Participant updateParticipantName(Participant participant, String fullName) {
        if (fullName != null && !fullName.isBlank() && !fullName.equals(participant.getFullName())) {
            participant.setFullName(fullName);
            return participantRepository.save(participant);
        }
        return participant;
    }

    private Participant createParticipant(RegistrationForm form) {
        Participant participant = new Participant();
        participant.setFullName(form.getFullName());
        participant.setEmail(form.getEmail());
        return participantRepository.save(participant);
    }
}
