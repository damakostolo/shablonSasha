async function deleteItem(id) {


        // Отправляем PUT-запрос на сервер
        try {
            const response = await fetch(`/api/addItems/${id}`, {
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
