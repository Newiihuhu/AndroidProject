package th.ac.su.cp.storytelling1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import th.ac.su.cp.storytelling1.db.AppDatabase;
import th.ac.su.cp.storytelling1.model.WordItem;
import th.ac.su.cp.storytelling1.util.AppExecutors;

public class AddStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        //เข้าถึงปุ่มpostในlayout
        Button postButton = findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //เข้าถึงตัว edit text ของ title และ set toString ให้ข้อความ
                EditText titleET = findViewById(R.id.title_edit_text);
                String title = titleET.getText().toString();

                //เข้าถึงตัว edit text ของ name และ set toString ให้ข้อความ
                EditText nameET = findViewById(R.id.name_edit_text);
                String name = nameET.getText().toString();

                //เข้าถึงตัว edit text ของ story และ set toString ให้ข้อความ
                EditText storyET = findViewById(R.id.story_edit_text);
                String story = storyET.getText().toString();

                //ส่งข้อมูลไปที่ WordItem โดยสร้าง executor
                final WordItem item = new WordItem(0,title, name, story);
                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(AddStoryActivity.this);
                        db.itemDao().addItem(item); //addข้อมูลเข้า database
                        finish();
                    }
                });

            }
        });
        //เข้าถึงปุ่ม cancle
        Button cancleButton = findViewById(R.id.cancle_button);
        //เมื่อกดจะไปที่หน้า HomeActivity.class
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStoryActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}