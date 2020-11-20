package th.ac.su.cp.storytelling1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import th.ac.su.cp.storytelling1.model.WordItem;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
    private Context mContext;
    private static WordItem[] items;

    public ItemAdapter(Context context,WordItem[] item){
        this.mContext = context;
        this.items = item;
    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_word_item, parent, false);
        return new MyViewHolder(mContext,v);
    }
    //แสดงtitle name story บน View ของเรา
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        WordItem item = items[position];
        holder.titleTextView.setText(item.title);
        holder.nameTextView.setText(item.name);
        holder.storyTextView.setText(item.story);
    }
    //set ขนาดของ recycler view
    public int getItemCount() {
        return items.length;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView nameTextView;
        TextView storyTextView;
        View rootView;
        public MyViewHolder(final Context context,View itemView){
            super(itemView);
            //เข้าถึงข้อมูลจาก layout
            titleTextView = itemView.findViewById(R.id.title_text_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            storyTextView = itemView.findViewById(R.id.story_text_view);
            rootView = itemView;
            //เมื่อคลิ๊ก view จะส่ง context และไปที่หน้า DetailActivity โดยใช้ library Gson
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    WordItem item = items[pos];
                    Intent intent = new Intent(context,DetailActivity.class);
                    String itemJson = new Gson().toJson(item);
                    intent.putExtra("item",itemJson);
                    context.startActivity(intent);
                }
            });

        }
    }
}
