package th.ac.su.cp.storytelling1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import th.ac.su.cp.storytelling1.model.WordItem;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //รับ intent มาจากหน้า ItemAdapter
        Intent intent = getIntent();
        String itemJson = intent.getStringExtra("item");
        WordItem item = new Gson().fromJson(itemJson, WordItem.class);
        //set ข้อความไปบน layout
        TextView titleTV = findViewById(R.id.titleDetail_text_view);
        titleTV.setText(item.title);
        TextView nameTV = findViewById(R.id.nameDetail_text_view);
        nameTV.setText(item.name);
        TextView storyTV = findViewById(R.id.storyDetail_text_view);
        storyTV.setText(item.story);
        storyTV.setMovementMethod(new ScrollingMovementMethod());
    }
}