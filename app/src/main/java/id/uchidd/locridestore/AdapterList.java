package id.uchidd.locridestore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    ArrayList<String> text1;
    ArrayList<String> text2;
    ArrayList<String> text3;
    ArrayList<String> text4;
    ArrayList<Integer> image;

    public AdapterList(ArrayList<String> text1, ArrayList<String> text2, ArrayList<String> text3, ArrayList<String> text4 , ArrayList<Integer> image){

        this.image = image;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tvText1.setText(text1.get(i));
        viewHolder.tvText2.setText(text2.get(i) + " | " + text3.get(i));
        viewHolder.tvText3.setText(text4.get(i));
        Picasso.get().load(image.get(i)).into(viewHolder.ivImage);

    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private TextView tvText1;
        private TextView tvText2;
        private TextView tvText3;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView)itemView.findViewById(R.id.ivList);
            tvText1 = (TextView)itemView.findViewById(R.id.tvList1);
            tvText2 = (TextView)itemView.findViewById(R.id.tvList2);
            tvText3 = (TextView)itemView.findViewById(R.id.tvList3);
        }
    }
}
