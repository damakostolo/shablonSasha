async function deleteMaterial(button) {

        // Получаем родительский элемент карточки
        const card = button.closest('.deleteBtn');

        // Получаем ID товара
        const id = card.id;


        // Отправляем PUT-запрос на сервер
        try {
            const response = await fetch(`api/${id}`, {
                method: 'delete'
            });

            if (response.ok) {
                    window.open('http://localhost:5000/main', );
                    alert(' видалення товару успишне!');
            } else {
                alert('Помилка видалення товару!');
            }
        } catch (error) {
            console.error('Помилка запиту:', error);
            alert('Не вдалося оновити товар!');
        }
        }
