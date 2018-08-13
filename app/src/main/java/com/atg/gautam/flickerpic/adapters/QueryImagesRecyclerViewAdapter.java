package com.atg.gautam.flickerpic.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atg.gautam.flickerpic.R;
import com.atg.gautam.flickerpic.utills.GlideApp;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

/*
 ** Created by Gautam Krishnan {@link https://github.com/GautiKrish}
 */public class QueryImagesRecyclerViewAdapter extends RecyclerView.Adapter<QueryImagesRecyclerViewAdapter.ImagesViewHolder> {
    private Context mContext;
    private ArrayList<String> mImageURLArrayList;
    private ArrayList<String> mImageIdArrayList;

    public QueryImagesRecyclerViewAdapter(Context mContext, ArrayList<String> mImageURLArrayList, ArrayList<String> mImageIdArrayList) {
        this.mContext = mContext;
        this.mImageURLArrayList = mImageURLArrayList;
        this.mImageIdArrayList = mImageIdArrayList;

    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rvView = LayoutInflater.from(mContext).inflate(R.layout.activity_rv_query_image_item, parent, false);
        return new ImagesViewHolder(rvView);

    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        GlideApp.with(mContext)
                .load(mImageURLArrayList.get(position))
                .centerCrop()
                .transition(new DrawableTransitionOptions().crossFade())
                .into(holder.mImageView);
        holder.mTextView.setText(mImageIdArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mImageIdArrayList != null)
            return mImageIdArrayList.size();
        else
            return 0;
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public ImagesViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.flickr_query_thumbnail_iv);
            mTextView = itemView.findViewById(R.id.query_image_id);

        }
    }

}
