package com.sk.tvschedule.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sk.tvschedule.R;

/**
 * Created by Сергій on 02.02.2017.
 */

public class ChannelTabFragment extends Fragment {

    public ChannelTabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.channel_tab_layout, null);
    }



}
