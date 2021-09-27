import bookStore.bookStoreModel.CollectionOfIsbn;
import bookStore.bookStoreModel.BooksToAdd;
import bookStore.bookStoreModel.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static apiTestHelper.ApiTestHelper.*;

@Epic("ApiTest")
@Feature("BookStoreApiTest")
@Tag("api")
public class ApiTest {
    private static final User user = getUser();
    public static final String ISBN = getRandomIsbn();


    //GET - Books
    @Step("GetAllBooks")
    @Test
    void getAllBooksTest() {
        Assertions.assertEquals(200, getAllBooks().getStatusCode());
    }

    // GET - One Book
    @Step("GetOneBook")
    @Test
    void getBookByISBNTest() {
        Assertions.assertEquals(200, getBookByISBN(ISBN).getStatusCode());
    }

    @Step("AddListOfBooks")
    @Test // добавили рандомную книгу, проверили статус код
    void addListOfBooksToUserTest() {
        CollectionOfIsbn collection = new CollectionOfIsbn();
        collection.setIsbn(ISBN);
        BooksToAdd booksToAdd = new BooksToAdd(user.getUserId(), Collections.singletonList(collection));
        Assertions.assertEquals(201,addBooksToUser(booksToAdd).getStatusCode());
    }

    @Step("DeleteBooks")
    @Test // добавили список книг, затем удалили, и проеверили статус код
    void deleteBooksTest() {
        CollectionOfIsbn collection = new CollectionOfIsbn();

        System.out.println(getListOfBooks().getBooks());
        BooksToAdd booksToAdd = new BooksToAdd(user.getUserId(),Collections.singletonList(collection));
        Assertions.assertEquals(201,addBooksToUser(booksToAdd).getStatusCode());
        Assertions.assertEquals(204, deleteBooks(getUser().getUserId()).getStatusCode());
    }

    @Step("DeleteBook")
    @Test
    void deleteBookTest() {

        CollectionOfIsbn collection = new CollectionOfIsbn();
        collection.setIsbn(ISBN);
        BooksToAdd booksToAdd = new BooksToAdd(user.getUserId(),Collections.singletonList(collection));
        Assertions.assertEquals(201,addBooksToUser(booksToAdd).getStatusCode());

        Assertions.assertEquals(204, deleteBooks(getUser().getUserId()).getStatusCode());

    }
}
