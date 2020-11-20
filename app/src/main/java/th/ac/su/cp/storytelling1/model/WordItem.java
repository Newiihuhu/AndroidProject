package th.ac.su.cp.storytelling1.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import th.ac.su.cp.storytelling1.R;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "item")
public class WordItem {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    @ColumnInfo(name = "title")
    public final String title;
    @ColumnInfo(name = "name")
    public final String name;
    @ColumnInfo(name = "story")
    public final String story;
    // set title name story ที่รับเข้ามา
    public WordItem(int id, String title,String name,String story){
        this.id = id;
        this.title = title;
        this.name = name;
        this.story = story;
    }

}