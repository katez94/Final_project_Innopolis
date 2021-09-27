package bookStore.bookStoreModel;

import java.util.List;

public class BooksToAdd {
    public String userId;
    public List<CollectionOfIsbn> collectionOfIsbns;

    public BooksToAdd(String userId, List<CollectionOfIsbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public List<CollectionOfIsbn> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }
}
