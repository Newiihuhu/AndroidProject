package th.ac.su.cp.storytelling1.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import th.ac.su.cp.storytelling1.model.WordItem;

@Dao
public interface ItemDao {
    //แสดงitemทั้งหมด
    @Query("SELECT * FROM item")
    WordItem[] getAllItem();

    //selectเพื่อค้นหาข้อมูลด้วยtitle
    @Query("SELECT * FROM item WHERE title like :title")
    WordItem[] getItemByTitle(String title);

    //เพิ่มข้อมูล
    @Insert
    void addItem(WordItem... items);
}
