import bookStore.bookStorePages.BookStorePage;
import bookStore.bookStorePages.LogInPage;
import bookStore.bookStorePages.ProfilePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import javafx.scene.control.Alert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import uiTestHelper.statics.UITestHelper;

import java.util.List;
import java.util.Random;

import static bookStore.TestConst.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@Tag("ui")
public class End2EndUiTest {

    @BeforeAll
    @Step("SetUp")
    static void setUp() {
        Configuration.browser = "chrome";
//        Configuration.headless = true;
        Configuration.startMaximized = true;
        Configuration.timeout = 3000;
    }

    @Test
    public void e2eTest(){
        // логинимся
        LogInPage logInPage = open(LOGIN_URL, LogInPage.class);
        LogInPage.inputUserName(TEST_USER_NAME);
        LogInPage.inputPassword(TEST_USER_PASSWORD);
        LogInPage.getLoginBtn().scrollIntoView(true);
        LogInPage.clickLogInBtn();
        webdriver().shouldHave(url(PROFILE_URL));

        // возвращаемся в BookStore, добавляем рандом книгу по названию
        ProfilePage profilePage = open(PROFILE_URL, ProfilePage.class);
        ProfilePage.getGotoBookStoreBtn().scrollIntoView(true);
        ProfilePage.clickGoToBookStoreBtn();
        webdriver().shouldHave(url(BOOKSTORE_URL));
        BookStorePage bookStorePage = open(BOOKSTORE_URL,BookStorePage.class);
        List<String> titles = BookStorePage.getBookTitles();
        String firstBookToAddTitle = titles.get(new Random().nextInt(titles.size()-1));
        BookStorePage.findBooks(firstBookToAddTitle);
        String firstBookToAddAuthor = BookStorePage.getBookAuthor(0).getText();
        String firstBookToAddPublisher = BookStorePage.getBookPublisher(0).getText();
        BookStorePage.clickChosenBook();
        BookStorePage.getAddToCollectionBtn().scrollIntoView(true);
        BookStorePage.clickAddToCollectionBtn();
        Assertions.assertEquals(ALERT_BOOK_ADDED_TEXT,switchTo().alert().getText());
        switchTo().alert().accept();
        BookStorePage.clickAddToCollectionBtn();
        Assertions.assertEquals(ALERT_BOOK_ALREADY_ADDED_TEXT,switchTo().alert().getText());
        switchTo().alert().accept();
        BookStorePage.getAddToCollectionBtn().scrollIntoView(true);
        BookStorePage.clickBackToStoreBtn();

        // возвращаемся в профиль, проверяем, что добавили ту книгу, по автору и издателю
        String addedBookAuthor = ProfilePage.getBookAuthor(0).getText();
        String bookPublisher = ProfilePage.getBookPublisher(0).getText();
        Assertions.assertEquals(firstBookToAddAuthor,addedBookAuthor);
        Assertions.assertEquals(firstBookToAddPublisher,bookPublisher);

        // возвращаемся в BookStore, добавляем еще одну рандом книгу, отличную от первой
        ProfilePage.getGotoBookStoreBtn().scrollIntoView(true);
        ProfilePage.clickGoToBookStoreBtn();
        String secondBookToAddTitle = titles.get(new Random().nextInt(titles.size()-1));
        secondBookToAddTitle = UITestHelper.compareBooks(firstBookToAddTitle,secondBookToAddTitle,titles);
        BookStorePage.findBooks(secondBookToAddTitle);
        BookStorePage.getChosenBook().scrollIntoView(true);
        BookStorePage.clickChosenBook();
        BookStorePage.clickAddToCollectionBtn();
        switchTo().alert().accept();


    }
}
