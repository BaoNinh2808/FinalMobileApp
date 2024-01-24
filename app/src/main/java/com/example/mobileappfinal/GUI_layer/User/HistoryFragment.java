package com.example.mobileappfinal.GUI_layer.User;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.DTO.Pay;
import com.example.mobileappfinal.Data_layer.Pay.PayDatabase;
import com.example.mobileappfinal.R;

import java.util.List;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private PayDatabase payDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        payDatabase = new PayDatabase();
        payDatabase.fetchPayItemList();

        setAndGetAllView(view);
        observeHistoryList();

        return view;
    }

    private void observeHistoryList() {
        payDatabase.getPayListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Pay>>() {
            @Override
            public void onChanged(List<Pay> pays) {
                setHistoryList(pays);

            }
        });
    }

    private void setHistoryList(List<Pay> pays) {
        Log.d("TEST_SIZE", String.valueOf(pays.size()));
        historyAdapter = new HistoryAdapter(requireContext(), pays);

        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setAndGetAllView(View view) {
        recyclerView = view.findViewById(R.id.rvHistoryList);
    }
}

