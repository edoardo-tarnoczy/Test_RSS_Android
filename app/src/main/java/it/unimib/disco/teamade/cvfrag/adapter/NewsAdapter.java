package it.unimib.disco.teamade.cvfrag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.unimib.disco.teamade.cvfrag.R;
import it.unimib.disco.teamade.cvfrag.news;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<news> newsList;
    Context context;

    public NewsAdapter(List<news> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_singlenews_card, parent, false);
        NewsViewHolder nvh = new NewsViewHolder(groceryProductView);
        return nvh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        //holder.imageProductImage.setImageResource(grocderyItemList.get(position).getProductImage());
        holder.txtNewsTitle.setText(newsList.get(position).getTitle());
        holder.txtNewsLink.setText(newsList.get(position).getLink());

//        holder.imageProductImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String productName = newsList.get(position).getTitle().toString();
//                Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        //ImageView imageProductImage;
        TextView txtNewsTitle;
        TextView txtNewsLink;

        public NewsViewHolder(View view) {
            super(view);
            //imageProductImage=view.findViewById(R.id.idProductImage);
            txtNewsTitle = view.findViewById(R.id.newsTitle);
            txtNewsLink = view.findViewById(R.id.newsLink);
        }
    }
}
