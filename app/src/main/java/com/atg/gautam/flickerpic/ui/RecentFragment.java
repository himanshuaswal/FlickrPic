package com.atg.gautam.flickerpic.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atg.gautam.flickerpic.R;
import com.atg.gautam.flickerpic.adapters.ImagesRecyclerViewAdapter;

/*
 ** Created by Gautam Krishnan {@link https://github.com/GautiKrish}
 */public class RecentFragment extends Fragment {

     private RecyclerView mRecyclerView;

     public RecentFragment(){

     }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_recent,container,false);
        mRecyclerView = rootView.findViewById(R.id.rv_recent_images);
        int columns = 2;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            columns = 4;
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(),columns);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        ImagesRecyclerViewAdapter mImagesRecyclerViewAdapter =  new ImagesRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mImagesRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }
}

