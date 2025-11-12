async function updateStatus(button) {
    const card = button.closest('.ticket-card');
    if (!card) {
        console.error('Не вдалося знайти елемент заявки.');
        return;
    }

    const ticketId = card.id;
    const statusSelect = card.querySelector('.status-select');
    const assigneeInput = card.querySelector('.assignee-input');

    const payload = {
        status: statusSelect ? statusSelect.value : null,
        assignedTo: assigneeInput && assigneeInput.value ? assigneeInput.value.trim() : null
    };

    try {
        const response = await fetch(`/api/tickets/${ticketId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            alert('Не вдалося оновити заявку. Спробуйте ще раз.');
            return;
        }

        const updatedTicket = await response.json();

        const statusSpan = card.querySelector('.status');
        if (statusSpan) {
            statusSpan.textContent = updatedTicket.status;
        }

        const assigneeSpan = card.querySelector('.assignee');
        if (assigneeSpan) {
            assigneeSpan.textContent = updatedTicket.assignedTo && updatedTicket.assignedTo.trim().length > 0
                ? updatedTicket.assignedTo
                : 'Не призначено';
        }

        alert('Заявку успішно оновлено!');
    } catch (error) {
        console.error('Помилка при оновленні заявки:', error);
        alert('Сталася помилка. Перевірте підключення до мережі.');
    }
}
