async function deleteMaterial(button) {

        // Получаем родительский элемент карточки
        const btn = button.closest('.deleteBtn');

        // Получаем ID товара
        const id = btn.id;


    // Отправляем DELETE-запрос на сервер
    try {
        const response = await fetch(`/materialPage/api/${id}`, {
            method: 'DELETE',
        });

        if (response) {
            // Перезагружаем страницу после успешного удаления
            window.location.href = '/main';
            alert('Видалення товару успішне!');
        } else {
            alert('Помилка видалення товару!');
        }
    } catch (error) {
        console.error('Помилка запиту:', error);
        alert('Не вдалося оновити товар!');
    }
}