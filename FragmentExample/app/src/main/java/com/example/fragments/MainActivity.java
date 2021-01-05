package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements ListFragment.IListFragment, DetailFragment.IDetailFragment {

    @Override
    public void itemClicked(int newItemId) {
        replaceDetailFragment(newItemId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        ListFragment fragment = (ListFragment)
                fm.findFragmentById(R.id.fragment_list);
    }

    public void replaceDetailFragment(int newItemId) {
        Bundle arguments = new Bundle();
        arguments.putInt("itemId", newItemId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arguments);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.fragment_container, fragment);
        trans .addToBackStack(null);
        trans.commit();
    }

    @Override
    public void test(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }
}