package th.ac.su.cp.storytelling1.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import th.ac.su.cp.storytelling1.model.WordItem;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    WordItem[] getAllItem();

    @Query("SELECT * FROM item WHERE title = :title")
    WordItem getItemByTitle(String title);

    @Insert
    void addItem(WordItem... items);
}
