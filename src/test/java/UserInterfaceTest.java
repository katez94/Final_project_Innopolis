import bookStore.bookStorePages.BookStorePage;
import bookStore.bookStorePages.LogInPage;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static bookStore.TestConst.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static uiTestHelper.statics.LogInPageStatic.*;

@Epic("UITest")
@Feature("BookStoreUITest")
@Tag("ui")
public class UserInterfaceTest {

    @BeforeAll
//    @Step("SetUp")
    static void setUp() {
        Configuration.browser = "chrome";
//      Configuration.headless = true;
        Configuration.startMaximized = true;
        Configuration.timeout = 3000;
    }

//  @Step("LogIn")
    @Test
    void logInTest() {
        LogInPage logInPage = open(LOGIN_URL, LogInPage.class);
        LogInPage.inputUserName(TEST_USER_NAME);
        LogInPage.inputPassword(TEST_USER_PASSWORD);
        LogInPage.getLoginBtn().scrollIntoView(true);
        LogInPage.clickLogInBtn();
        webdriver().shouldHave(url(PROFILE_URL));

    }

    @Test
    public void loginPageStaticsTest(){
        LogInPage logInPage = open(LOGIN_URL, LogInPage.class);
        Assertions.assertEquals(LOGIN_HEADER,LogInPage.getHeader().getText());
        Assertions.assertEquals(TITLE_H2,LogInPage.getTitleH2().getText());
        Assertions.assertEquals(TITLE_H5,LogInPage.getTitleH5().getText());
        Assertions.assertEquals(USERNAME_LABEL,LogInPage.getUsernameLabel().getText());
        Assertions.assertEquals(PASSWORD_LABEL,LogInPage.getPasswordLabel().getText());
    }

    @Test
    public void goToNewUserPageTest(){
        LogInPage logInPage = open(LOGIN_URL, LogInPage.class);
        LogInPage.getNewUserBtn().scrollIntoView(true);
        LogInPage.clickNewUserBtn();
        webdriver().shouldHave(url(REGISTER_URL));
    }


    @Test
    void clickNextBtn(){
        BookStorePage bookStorePage = open(BOOKSTORE_URL,BookStorePage.class);
//        bookStorePage.clickNextButton();
    }

}
