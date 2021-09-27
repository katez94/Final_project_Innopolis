package bookStore.bookStoreModel;

public class StringObjectModel {
    private String isbn;
    private   String userId;

    public StringObjectModel(String isbn, String userId) {
        this.isbn = isbn;
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUserId() {
        return userId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
