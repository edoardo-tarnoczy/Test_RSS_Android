package it.unimib.disco.teamade.cvfrag;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;



public class RssFeedListAdapter extends RecyclerView.Adapter<RssFeedListAdapter.FeedModelViewHolder> {

    private List<news> mRssFeedModels;

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public RssFeedListAdapter(List<news> rssFeedModels) {
        mRssFeedModels = rssFeedModels;
    }

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_singlenews_card, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final news rssFeedModel = mRssFeedModels.get(position);

        String imgUrl = extractImageUrl(rssFeedModel.getDesc());
        if(!imgUrl.isEmpty())
            Picasso.get().load(imgUrl).into((ImageView) holder.rssFeedView.findViewById(R.id.newsImage));
        ((TextView) holder.rssFeedView.findViewById(R.id.newsTitle)).setText(rssFeedModel.getTitle());
        ((TextView) holder.rssFeedView.findViewById(R.id.newsDesc)).setText(rssFeedModel.getDesc().substring(0, 100));
        ((TextView) holder.rssFeedView.findViewById(R.id.newsLink)).setText(rssFeedModel.getLink());
        ((TextView) holder.rssFeedView.findViewById(R.id.newsPubDate)).setText(rssFeedModel.getPubDate());
    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }

    private String extractImageUrl(String description) {
        Document document = Jsoup.parse(description);
        Elements imgs = document.select("img");

        for (Element img : imgs) {
            if (img.hasAttr("src")) {
                return img.attr("src").replace("http:", "https:");
            }
        }

        // no image URL
        return "";
    }
}
