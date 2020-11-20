package th.ac.su.cp.storytelling1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import th.ac.su.cp.storytelling1.db.AppDatabase;
import th.ac.su.cp.storytelling1.model.WordItem;
import th.ac.su.cp.storytelling1.util.AppExecutors;

public class HomeActivity extends AppCompatActivity {
    // สร้างตัวแปรสำหรับ RecyclerView
    private RecyclerView mRecyclerView;
    //
    protected void onResume(){
        super.onResume();
        // สร้างExecutorเพื่อจะแยกthreadในการทำงาน
        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //เข้าถึงdatabase
                AppDatabase db = AppDatabase.getInstance(HomeActivity.this);
                //ดึงข้อมูลจากdatabaseเก็บไว้ในตัวแปรitems
                final WordItem[] items = db.itemDao().getAllItem();
                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        //สร้าง adapter object
                        ItemAdapter adapter = new ItemAdapter(HomeActivity.this,items);
                        //กำหนดadapterให้recyclerView
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //เข้าถึง RecyclerView ใน  layout
        mRecyclerView = findViewById(R.id.recycler_view);
        // กำหนด layout manager ให้กับ RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        //เข้าถึงปุ่ม FloatingActionButton ใน layout
        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to หน้า AddStoryActivity.class
                Intent intent = new Intent(HomeActivity.this,AddStoryActivity.class);
                startActivity(intent);
            }
        });
        //เข้าถึงปุ่ม search ใน layout
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //เข้าถึงช่อง search ใน layout
                EditText searchET = findViewById(R.id.search_edit_text);
                //set tostring ในข้อความ
                final String searchTitle = searchET.getText().toString();
                //สร้าง executor แยก thread เพื่อเข้าถึงข้อมูลใน database
                final AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(HomeActivity.this);
                        final WordItem[] items;
                        //เช็คว่าถ้าข้อความที่ค้นหาเป็นค่าว่างหรือnull ให้แสดงข้อมูลทั้งหมด
                        if(searchTitle.isEmpty() || searchTitle==null){
                            items = db.itemDao().getAllItem();
                        }
                        //ถ้าไม่ใช่ให้ค้นหาตามคำที่สืบค้น
                        else{
                            items = db.itemDao().getItemByTitle(searchTitle+"%");
                        }
                        executors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                //สร้าง adapter object
                                ItemAdapter adapter = new ItemAdapter(HomeActivity.this,items);
                                //กำหนดadapterให้recyclerView
                                mRecyclerView.setAdapter(adapter);
                            }
                        });
                    }
                });
            }
        });
    }
}