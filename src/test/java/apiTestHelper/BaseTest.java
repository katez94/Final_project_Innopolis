package apiTestHelper;

import org.junit.jupiter.api.BeforeEach;
import requests.BookStoreApi;

public class BaseTest {
    BookStoreApi bookStoreApi = new BookStoreApi();

    @BeforeEach
    public void deleteBooksInit() {
        bookStoreApi.deleteBooks(bookStoreApi.getUser().getUserId());
    }

}
