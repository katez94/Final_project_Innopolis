## Book store tests (Final project Innopolis)
https://github.com/katez94/Final_project_Innopolis.git

Тестируемое приложение - https://demoqa.com/books

Данный тестовый фреймворк написан для тестирования приложения DemoQA BookStore с использованием технологий Java, RestAssured, JUnit5,
Selenide, Cucumber, Gradle, Allure и состоит из трех тестовых слоев: Api Tests, UI Tests и E2E Test. 



### Api Tests
Этот слой тестирует по одному виду запроса из категории BookStore (https://demoqa.com/swagger/), а именно GET, POST, PUT DELETE.

##### Тесты GetAllBooks и GetOneBook
C помощью GET запросов мы получаем список всех книг, хранящихся в библиотеке BookStore, либо одну книгу по идентификатору ISBN.

##### Тест AddOneBook
C помощью POST запроса добавляем книгу в личный кабинет пользователя и убеждаемся, что она не может быть добавлена повторно.

##### Тест ReplaceBook
C помощью PUT запроса заменяем добавленную книгу другой рандомной книгой из списка
и убеждаемся, что книга успешно заменена.

Запрос DELETE позволяет удалять все добавленные книги пользователя из личного кабинета. 
Реализован в методе deleteBooksInit() класса BaseTest, запускается перед каждым тестом.

Запускается из gradle task - apiTests. Формируется allure отчет.

### UI Tests
Этот слой покрывает тестами страницу Входа в личный кабинет и страницу библиотеки BookStore.

##### Тесты LogIn, LogInPageStatics и goToNewUserPage
На странице входа проверяем непосредственно сам вход в личный кабинет, статику, работу кнопок.

##### Тесты SearchByValidTitle, Check5RowsSelector и LastNextBtnNotActive
На странице BookStore проверяем поле ввода, кнопки, выпадающий список.

Запускается из gradle task - uiTests. Формируется allure отчет.

### E2E Test
Этот слой тестирует сценарий входа в личный кабинет, добавления и удаления книг пользователю, выход
из личного кабинета, описанный в feature файле.

Запускается из gradle task - cucumber. Формируется cucumber отчет.
