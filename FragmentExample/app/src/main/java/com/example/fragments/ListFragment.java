package com.example.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ListFragment extends Fragment {
    public ListFragment() {
        // Required empty public constructor
    }

    private int itemId;
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layoutRoot = inflater.inflate(R.layout.fragment_list,
                container, false);

        return layoutRoot;
    }

    private IListFragment iListFragment;
    public interface IListFragment {
        void itemClicked(int newItemId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iListFragment = (IListFragment) context;
    }

    public void sendDataToActivity(int newItemId) {
        iListFragment.itemClicked(newItemId);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = getArguments();
        if (bundle != null) {
            itemId = bundle.getInt("itemId");
        }

        View view = getView();
        if (view != null) {

            Button detail1Btn = view.findViewById(R.id.detail1Btn);
            detail1Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendDataToActivity(1);
                }
            });

            Button detail2Btn = view.findViewById(R.id.detail2Btn);
            detail2Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendDataToActivity(2);
                }
            });

            Button detail3Btn = view.findViewById(R.id.detail3Btn);
            detail3Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendDataToActivity(3);
                }
            });
        }
    }
}