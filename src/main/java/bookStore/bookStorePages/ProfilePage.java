package bookStore.bookStorePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage {

    @FindBy (id = "userName-value")
    private SelenideElement userNameLabel;

    @FindBy (id = "searchBox")
    private SelenideElement profileSearchBox;

    @FindBy (id = "basic-addon2")
    private SelenideElement searchBtn;

    @FindBy (id = "submit")
    private SelenideElement logOutBtn;

    @FindBy (id = "gotoStore")
    private SelenideElement gotoStoreBtn;

    @FindBy (css = ".text-center.button > button")
    private SelenideElement deleteAccountBtn;

    @FindBy (css = ".text-right.button.di > button")
    private SelenideElement deleteAllBooksBtn;

    @FindBy(css = ".-previous > button")
    private SelenideElement previousButton;

    @FindBy(css = ".-next > button")
    private SelenideElement nextButton;

    @FindBy(css = ".-pageSizeOptions > select")
    private SelenideElement rowsSelector;

    @FindBy(css = ".-pageJump > input")
    private SelenideElement pageNumber;

    @FindBy(css = ".action-buttons > span > a")
    private List<SelenideElement> booksTitles;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[3][contains(text(), ' ')]")
    private List<SelenideElement> booksAuthors;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[4][contains(text(), ' ')]")
    private List<SelenideElement> booksPublishers;

    @FindBy(id = "delete-record-undefined")
    private List<SelenideElement> deleteActionBtns;

    public void clickSearchBtn() {
        searchBtn.click();
    }

    public void clickLogOutBtn() {
        logOutBtn.click();
    }

    public void clickDeleteAccountBtn() {
        deleteAccountBtn.click();
    }

    public void clickDeleteAllBooksBtn() {
        deleteAllBooksBtn.click();
    }

    public void clickPreviousBtn() {
        previousButton.click();
    }

    public void clickNextBtn() {
        nextButton.click();
    }

    public void findBooks(String keys) {
        profileSearchBox.sendKeys(keys);
    }

    public SelenideElement getBookTitle(int index) {
        return booksTitles.get(index);
    }

    public List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();
        for (SelenideElement title : booksTitles) {
            titles.add(title.getText());
        }
        return titles;
    }
    public void selectRow(String value){
        Select rows = new Select(rowsSelector);
        rows.selectByValue(value);
    }

    public String getPageFieldValue() {
        return pageNumber.getAttribute("value");
    }

//    public SelenideElement get(int bookIndex) {
//        return deleteActionBtns.get(bookIndex);
//    }

    public String getUserNameLabel() {
        return userNameLabel.getText();
    }
}
