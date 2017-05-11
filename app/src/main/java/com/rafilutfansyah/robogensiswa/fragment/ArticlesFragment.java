package com.rafilutfansyah.robogensiswa.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafilutfansyah.robogensiswa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {

    protected Context context;

    public ArticlesFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        return view;
    }
}

