package me.roguemc9000.pooltimechemicalcalculator;

import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;

/**
 * Created by Nico on 7/7/2015.
 */
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PageFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        AdView adview = null;
        switch (mPage) {
            case 1: view = inflater.inflate(R.layout.ph_fragment, container, false);
                adview = (AdView)view.findViewById(R.id.adViewPh);
                break;
            case 2: view = inflater.inflate(R.layout.alk_fragment, container, false);
                adview = (AdView)view.findViewById(R.id.adViewAlk);
                break;
            case 3: view = inflater.inflate(R.layout.gal_fragment, container, false);
                adview = (AdView)view.findViewById(R.id.adViewGal);
        }
        adview.loadAd(MainActivity.adRequest);
        return view;
    }
}
