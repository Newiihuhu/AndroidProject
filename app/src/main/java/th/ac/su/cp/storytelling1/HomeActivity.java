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
    private static final String TAG = HomeActivity.class.getName();
    private RecyclerView mRecyclerView;
    protected void onResume(){
        super.onResume();
        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(HomeActivity.this);
                final WordItem[] items = db.itemDao().getAllItem();
                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        ItemAdapter adapter = new ItemAdapter(HomeActivity.this,items);
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

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AddStoryActivity.class);
                startActivity(intent);
            }
        });
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchET = findViewById(R.id.search_edit_text);
                
            }
        });
    }
}