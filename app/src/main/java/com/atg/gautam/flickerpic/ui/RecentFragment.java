package com.atg.gautam.flickerpic.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.atg.gautam.flickerpic.BuildConfig;
import com.atg.gautam.flickerpic.R;
import com.atg.gautam.flickerpic.adapters.ImagesRecyclerViewAdapter;
import com.atg.gautam.flickerpic.model.FlickrResponse;
import com.atg.gautam.flickerpic.model.Image;
import com.atg.gautam.flickerpic.model.Images;
import com.atg.gautam.flickerpic.network.RetrofitAPI;
import com.atg.gautam.flickerpic.utills.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 ** Created by Gautam Krishnan {@link https://github.com/GautiKrish}
 */public class RecentFragment extends Fragment {
    private static String METHOD_NAME = "flickr.photos.getRecent";
    private static String FORMAT = "json";
    private static int NO_JSON_CALL_BACK = 1;
    private static String EXTRAS = "url_s";
    private GridLayoutManager mGridLayoutManager;
    private ImagesRecyclerViewAdapter mImagesRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ArrayList<String> imageURLArrayList = new ArrayList<>();
    private ArrayList<String> imageIdArrayList = new ArrayList<>();

    public RecentFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent, container, false);
        mRecyclerView = rootView.findViewById(R.id.rv_recent_images);
        mProgressBar = rootView.findViewById(R.id.loading_indicator);
        fetchImages();
        int columns = 2;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            columns = 4;
        mGridLayoutManager = new GridLayoutManager(getActivity(), columns);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        Log.i("No of images",String.valueOf(imageURLArrayList.size()));
        mImagesRecyclerViewAdapter = new ImagesRecyclerViewAdapter(getActivity(), imageURLArrayList, imageIdArrayList);
        mRecyclerView.setAdapter(mImagesRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    private void fetchImages() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        RetrofitAPI retrofitAPI = NetworkUtils.getCacheEnabledRetrofit(getActivity()).create(RetrofitAPI.class);
        Call<FlickrResponse> call = retrofitAPI.getImages(METHOD_NAME, BuildConfig.FLICKR_API_TOKEN, FORMAT, NO_JSON_CALL_BACK, EXTRAS);
        call.enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
                FlickrResponse mFlickrResponse = response.body();
                imageURLArrayList.clear();
                imageIdArrayList.clear();
                if (mFlickrResponse != null) {
                    Images mImages = mFlickrResponse.getImages();
                    List<Image> imageList = mImages.getImage();
                    for (Image image : imageList) {
                        imageURLArrayList.add(image.getImageUrl());
                        imageIdArrayList.add(image.getId());
                    }
                    Log.i("No. of images in AL", String.valueOf(imageURLArrayList.size()));
                    mImagesRecyclerViewAdapter.notifyDataSetChanged();
                }
                mRecyclerView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
                mRecyclerView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }
}

