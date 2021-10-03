import apiTestHelper.ApiTestHelper;
import apiTestHelper.BaseTest;
import requests.BookStoreApi;
import bookStore.bookStoreModel.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("ApiTest")
@Feature("BookStoreApiTest")
@Tag("api")
public class ApiTest extends BaseTest {
    BookStoreApi bookStoreApi = new BookStoreApi();
    ApiTestHelper apiTestHelper = new ApiTestHelper();
    public String ISBN = apiTestHelper.getRandomIsbn();

    @Step("GetAllBooks")
    @Test
    void getAllBooksTest() {
        bookStoreApi.getListOfBooks();
    }

    @Step("GetOneBook")
    @Test
    void getBookByISBNTest() {
        Book book = bookStoreApi.getBookByISBN(ISBN);
        Assertions.assertEquals(ISBN, book.getIsbn());
    }

    @Step("AddOneBook")
    @Test
    void addOneBookToUserTest() {
        BooksToAdd bookToAdd = apiTestHelper.getBookToAdd(bookStoreApi.getUser().getUserId(), ISBN);
        bookStoreApi.addBooksToUser(bookToAdd, 201);
        bookStoreApi.addBooksToUser(bookToAdd, 400);
    }

    @Step("ReplaceBook")
    @Test
    void replaceBookTest() {
        String randomIsbn = apiTestHelper.getRandomIsbn();
        while (randomIsbn.equals(ISBN)) {
            randomIsbn = apiTestHelper.getRandomIsbn();
        }
        bookStoreApi.addBooksToUser(apiTestHelper.getBookToAdd(bookStoreApi.getUser().getUserId(), ISBN), 201);
        ReplaceIsbn replacableIsbn = new ReplaceIsbn(randomIsbn, bookStoreApi.getUser().getUserId());
        bookStoreApi.replaceBook(ISBN, replacableIsbn);
        bookStoreApi.addBooksToUser(apiTestHelper.getBookToAdd(bookStoreApi.getUser().getUserId(), ISBN), 201);
        bookStoreApi.addBooksToUser(apiTestHelper.getBookToAdd(bookStoreApi.getUser().getUserId(), randomIsbn), 400);
    }


}
