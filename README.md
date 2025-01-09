# Master-service
Приложение имеет в своем составе Master-Detail таблицы.

Master - cписок документов с полями: номер, дата, сумма, примечание.

Detail - список спецификаций с полями: наименование, сумма.

Реализовано добавление/редактирование/удаление/ документа и его спецификации(набора позиций). При этом сумма документа не редактируется, а пересчитывается на основе спецификаций.

Дополнительно реализована проверка уникальности значения номера документа и наименования позиции на уровне транзакции, с выдачей сообщения пользователю и с логированием ошибки в отдельную таблицу БД (в отдельной транзакции).

--------
### Стек технологий:
Java, PostgreSQL, Spring Boot, Hibernate, REST API, Thymeleaf, Apache Maven, Docker
--------

#### Стартовая страница приложения:
![2025-01-08_18-39-12](https://github.com/user-attachments/assets/e4976efc-1443-41a4-9b6a-b9db1b29868e)
После заполнения полей формы и нажатия кнопки "Создать" вновь созданный документ отражается в списке:
![2025-01-08_18-41-32](https://github.com/user-attachments/assets/99447edf-58c2-451f-a77f-2e4570832597)
При выборе документа из списка и нажатии кнопки "Удалить документ" он удаляется и списка.
При выборе документа из списка и нажатии кнопки "Редактировать документ" открывается
#### Страница редактирования документа:
![2025-01-08_18-46-55](https://github.com/user-attachments/assets/414d9b38-fbf6-4758-9a27-92a1982fa21c)
При попытке создать новый документ с уже существующим номером пользователь получает сообщение об ошибке:
![2025-01-08_18-44-29](https://github.com/user-attachments/assets/71b1b063-e7ec-48e1-882a-cc53b4b5d834)
Ошибка, также, логируется в БД:
![2025-01-08_18-45-31](https://github.com/user-attachments/assets/2aa91768-3c7a-4eb7-bf39-5c80471f1418)
При выборе документа из списка и нажатии кнопки "Посмотреть специфиикации" открывается
#### Страница спецификаций к конкретному документу:
![2025-01-08_18-55-11](https://github.com/user-attachments/assets/4a23a7d5-95ec-43bd-8fb1-8f8992eb8737)
После заполнения полей формы и нажатия кнопки "Создать" вновь созданная позиция отражается в списке.
При выборе позиции из списка и нажатии кнопки "Удалить спецификацию" она удаляется и списка.
При выборе позиции из списка и нажатии кнопки "Редактировать спецификацию" открывается
#### Страница редактирования спецификации:
![2025-01-08_18-57-20](https://github.com/user-attachments/assets/040e1ab3-e5f0-4fd8-9424-cca269fdef6a)

### Инструкция по сборке и запуску приложения:
Приложение написано на Java 21 и Spring Boot 3.
#### Запустить приложение можно двумя способами.
##### Первый способ:
• Предварительно создать БД POSTGRES не ниже версии 15 с именем «_master-details-db_».

• Поменять в application.properties spring.datasource.username и spring.datasource.password на свои логин и пароль от Postgres. 

• Скомпилировать _jar_ файл с помощью maven командой `mvn clean install`.

• Запустить приложение командой java `java -jar master-service-1.0.0-SNAPSHOT.jar`.

• Приложение будет доступно по адресу: _http://localhost:8080/masters_.

##### Второй способ:
• Установить и запустить _Docker_.

• Поменять в docker-compose.yaml POSTGRES_USER и POSTGRES_PASSWORD на свои логин и пароль от Postgres. 

• Запустить контейнер командой `docker-compose up`.

• Приложение будет доступно по адресу: _http://localhost:8080/masters_.
