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
 */public class ImagesRecyclerViewAdapter extends RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImagesViewHolder> {
    private Context mContext;
    private ArrayList<String> mImageURLArrayList;
    private ArrayList<String> mImageIdArrayList;

    public ImagesRecyclerViewAdapter(Context mContext, ArrayList<String> mImageURLArrayList, ArrayList<String> mImageIdArrayList) {
        this.mContext = mContext;
        this.mImageURLArrayList = mImageURLArrayList;
        this.mImageIdArrayList = mImageIdArrayList;

    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rvView = LayoutInflater.from(mContext).inflate(R.layout.activity_rv_image_item, parent, false);
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
        return mImageIdArrayList.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public ImagesViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.flickr_thumbnail_iv);
            mTextView = itemView.findViewById(R.id.image_id);

        }
    }
}
