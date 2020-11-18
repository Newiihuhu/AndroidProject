package th.ac.su.cp.storytelling1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import th.ac.su.cp.storytelling1.model.WordItem;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String itemJson = intent.getStringExtra("item");
        WordItem item = new Gson().fromJson(itemJson, WordItem.class);

        //Toast.makeText(DetailActivity.this,item,Toast.LENGTH_SHORT).show();
        Log.i(TAG, item.title);
        TextView titleTV = findViewById(R.id.titleDetail_text_view);
        titleTV.setText("Newii");
        TextView nameTV = findViewById(R.id.nameDetail_text_view);
        //nameTV.setText(item.name);
        TextView storyTV = findViewById(R.id.storyDetail_text_view);
        //storyTV.setText(item.story);


    }
}