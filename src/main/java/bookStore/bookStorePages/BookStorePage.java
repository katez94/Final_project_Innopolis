package bookStore.bookStorePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BookStorePage {
 //   private WebDriver webDriver; // зачем тут нужен?

//    public BookStorePage(WebDriver webDriver) {
//        PageFactory.initElements(webDriver,this);
//        this.webDriver = webDriver;
//    }

    @FindBy(id = "searchBox")
    private SelenideElement searchBox;

    @FindBy(css = ".action-buttons > span > a")
    private List<SelenideElement> booksTitles;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[3][contains(text(), ' ')]")
    private List<SelenideElement> booksAuthors;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[4][contains(text(), ' ')]")
    private List<SelenideElement> booksPublishers;

    @FindBy(css = ".-previous > button")
    private SelenideElement previousButton;

    @FindBy(css = ".-next > button")
    private SelenideElement nextButton;

    @FindBy(css = ".-pageSizeOptions > select")
    private SelenideElement rowsSelector;

    @FindBy(css = ".-pageJump > input")
    private SelenideElement pageNumber;

    public void findBooks(String keys) {
        searchBox.sendKeys(keys);
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

    public SelenideElement getBookAuthor(int index) {
        return booksAuthors.get(index);
    }

    public List<String> getBookAuthors() {
        List<String> authors = new ArrayList<>();
        for (SelenideElement author : booksAuthors) {
            authors.add(author.getText());
        }
        return authors;
    }

    public SelenideElement getBookPublisher(int index) {
        return booksPublishers.get(index);
    }

    public List<String> getBookPublishers() {
        List<String> publishers = new ArrayList<>();
        for (WebElement publisher : booksPublishers) {
            publishers.add(publisher.getText());
        }
        return publishers;
    }

    public void clickPreviousButton() {
        previousButton.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void selectRow(String value){
        Select rows = new Select(rowsSelector);
        rows.selectByValue(value);
    }

    public String getPageFieldValue() {
        return pageNumber.getAttribute("value");
    }
}

