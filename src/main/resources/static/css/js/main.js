async function deleteItem(button) {
        // Получаем родительский элемент карточки
        const card = button.closest('.item-card');

        console.log(card)

        // Получаем ID товара
        const itemId = card.id;


        // Отправляем PUT-запрос на сервер
        try {
            const response = await fetch(`/api/addItems/${itemId}`, {
                method: 'PUT'
            });

            if (response.ok) {

            } else {
                alert('Помилка оновлення товару!');
            }
        } catch (error) {
            console.error('Помилка запиту:', error);
            alert('Не вдалося оновити товар!');
        }
        }
