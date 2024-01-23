package com.example.mobileappfinal.GUI_layer.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.DTO.Favorite;
import com.example.mobileappfinal.Data_layer.Cart.FavoriteDatabase;
import com.example.mobileappfinal.R;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;

    private FavoriteDatabase favoriteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        favoriteDatabase = new FavoriteDatabase();
        favoriteDatabase.fetchFavoriteList();

        setAndGetAllView(view);
        observeCartItemList();

        return view;
    }

    private void observeCartItemList() {
        favoriteDatabase.getFavoriteListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                setFavoriteList(favorites);
            }
        });
    }

    private void setFavoriteList(List<Favorite> favorites) {
        favoriteAdapter = new FavoriteAdapter(requireContext(), favorites);

        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setAndGetAllView(View view) {
        recyclerView = view.findViewById(R.id.rvFavoriteList);
    }
}

