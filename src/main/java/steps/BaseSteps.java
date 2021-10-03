package steps;

import bookStore.bookStorePages.BookStorePage;
import bookStore.bookStorePages.LogInPage;
import bookStore.bookStorePages.ProfilePage;
import com.codeborne.selenide.Configuration;

import io.cucumber.java.bg.И;
import io.cucumber.java.bg.То;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static bookStore.TestConst.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;


//@Tag("ui")
public class BaseSteps {

    private static List<String> titles;
    private static Book book;
    private static String firstBookToAddTitle;

//    @BeforeAll
//    @Step("SetUp")
//    static void setUp() {
//        Configuration.browser = "chrome";
//        //Configuration.headless = true;
//        Configuration.startMaximized = true;
//        Configuration.timeout = 3000;
//    }

    public void e2eTest() {

        // логинимся

       // initLogin();

        // возвращаемся в BookStore, добавляем рандом книгу по названию
        //goToProfile();
        //List<String> titles = goToBookStoreFromProfile();
        //String firstBookToAddTitle = E2ETestHelper.getRandomTitle(titles);
        //Book addedBook = addBookToUser(firstBookToAddTitle);

//        BookStorePage.clickAddToCollectionBtn();
//        Assertions.assertEquals(ALERT_BOOK_ALREADY_ADDED_TEXT,switchTo().alert().getText());
//        switchTo().alert().accept();
        //goToBookStoreFromBookDescription();

        // возвращаемся в профиль,
        //goToProfile();

        //проверили по автору и издателю, что добавили ту книгу,
        //checkAddedBook(addedBook.getAuthor(), addedBook.getPublisher());
        //goToProfile();
        // возвращаемся в BookStore, добавляем еще одну рандом книгу, отличную от первой
        //goToBookStoreFromProfile();
        //addNewRandomBook(titles, firstBookToAddTitle);

        // возвращаемся в профиль, удаляем все книги, проверяем, что книг нет
        //goToProfile();


        //deleteAllBooks();

        // разлогиниваемся
        //logOut();

    }

    @Допустим("^выполнили вход в личный кабинет BookStore$")
    public void initLogin() {
        LogInPage logInPage = open(LOGIN_URL, LogInPage.class);
        LogInPage.inputUserName(TEST_USER_NAME);
        LogInPage.inputPassword(TEST_USER_PASSWORD);
        LogInPage.getLoginBtn().scrollIntoView(true);
        LogInPage.clickLogInBtn();
        webdriver().shouldHave(url(PROFILE_URL));
    }
    @Затем("^Перешли в BookStore из личного кабинета$")
    public void goToBookStoreFromProfile() {
        open(PROFILE_URL, ProfilePage.class);
        ProfilePage.getGotoBookStoreBtn().scrollIntoView(true);
        ProfilePage.clickGoToBookStoreBtn();
        webdriver().shouldHave(url(BOOKSTORE_URL));
        open(BOOKSTORE_URL, BookStorePage.class);
        titles =  BookStorePage.getBookTitles();
    }

    @И("^Добавили пользователю рандомную книгу$")
    public void addBookToUser() {
        firstBookToAddTitle = E2ETestHelper.getRandomTitle(titles);
        BookStorePage.findBooks(firstBookToAddTitle);
        book = new Book();
        book.setPublisher(BookStorePage.getBookPublisher(0).getText());
        book.setAuthor(BookStorePage.getBookAuthor(0).getText());
        BookStorePage.clickChosenBook();
        BookStorePage.getAddToCollectionBtn().scrollIntoView(true);
        BookStorePage.clickAddToCollectionBtn();
        Assertions.assertEquals(ALERT_BOOK_ADDED_TEXT, switchTo().alert().getText());
        switchTo().alert().accept();
    }

    public void goToBookStoreFromBookDescription() {
        BookStorePage.getBackToBookStoreBtn().scrollIntoView(true);
        BookStorePage.clickBackToStoreBtn();
    }
    @И("^Вернулись в личный кабинет$")
    public void goToProfile() {
        open(PROFILE_URL, ProfilePage.class);
    }

    @Затем("^Проверили по автору и издателю, что добавили ту книгу$")
    public void checkAddedBook() {
        String addedBookAuthor = ProfilePage.getBookAuthor(0).getText();
        String bookPublisher = ProfilePage.getBookPublisher(0).getText();
        Assertions.assertEquals(book.getAuthor(), addedBookAuthor);
        Assertions.assertEquals(book.getPublisher(), bookPublisher);
    }
    @И("^Добавили еще одну рандомную книгу, отличную от первой$")
    public void addNewRandomBook() {
        String secondBookToAddTitle = E2ETestHelper.compareBooks(firstBookToAddTitle, E2ETestHelper.getRandomTitle(titles), titles);
        BookStorePage.findBooks(secondBookToAddTitle);
        BookStorePage.getChosenBook().scrollIntoView(true);
        BookStorePage.clickChosenBook();
        BookStorePage.getAddToCollectionBtn().scrollIntoView(true);
        BookStorePage.clickAddToCollectionBtn();
        switchTo().alert().accept();
    }

    @Когда("^Удалили все книги, проверили, что книг нет$")
    public void deleteAllBooks() {
        ProfilePage.getDeleteAllBooksBtn().scrollIntoView(true);
        ProfilePage.clickDeleteAllBooksBtn();
        ProfilePage.clickOkDeleteBtn();
        switchTo().alert().accept();
        Assertions.assertEquals(0, ProfilePage.getBookTitles().size());
    }
    @То("^Выполнили выход из личного кабинета$")
    public void logOut() {
        ProfilePage.clickLogOutBtn();
        webdriver().shouldHave(url(LOGIN_URL));
    }

















}
