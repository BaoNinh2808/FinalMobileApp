package com.example.mobileappfinal.GUI_layer.Categories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mobileappfinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {

    public CategoriesFragment() {
        // Required empty public constructor
    }


    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_categories, container, false);

        ImageButton btnShowView1 = v.findViewById(R.id.bed);
        btnShowView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Create a new instance of your BedFragment
                SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Bed");

                fragmentTransaction.replace(R.id.fragment_place, specifyCategoryFragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btnShowView2 = v.findViewById(R.id.Drawer);
        btnShowView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Create a new instance of your BedFragment
                SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Drawer");

                fragmentTransaction.replace(R.id.fragment_place, specifyCategoryFragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btnShowView3 = v.findViewById(R.id.Desk);
        btnShowView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Create a new instance of your BedFragment
                SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Desk");

                fragmentTransaction.replace(R.id.fragment_place, specifyCategoryFragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btnShowView4 = v.findViewById(R.id.chair);
        btnShowView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Create a new instance of your BedFragment
                SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Chair");

                fragmentTransaction.replace(R.id.fragment_place, specifyCategoryFragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btnShowView5 = v.findViewById(R.id.sofa);
        btnShowView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Create a new instance of your BedFragment
                SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Sofa");

                fragmentTransaction.replace(R.id.fragment_place, specifyCategoryFragment);
                fragmentTransaction.commit();
            }
        });

        ImageButton btnShowView6 = v.findViewById(R.id.table);
        btnShowView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Create a new instance of your BedFragment
                SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Table");

                fragmentTransaction.replace(R.id.fragment_place, specifyCategoryFragment);
                fragmentTransaction.commit();
            }
        });


        return v;
    }
}
