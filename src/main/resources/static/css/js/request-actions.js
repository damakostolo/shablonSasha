async function deleteRequest(button) {
    const id = button.id;

    try {
        const response = await fetch(`/api/requests/${id}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            alert('Заявку успішно видалено');
            window.location.href = '/requests';
        } else if (response.status === 404) {
            alert('Заявку не знайдено');
        } else {
            alert('Сталася помилка при видаленні заявки');
        }
    } catch (error) {
        console.error('Помилка при відправці запиту:', error);
        alert('Не вдалося видалити заявку.');
    }
}
