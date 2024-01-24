package com.example.mobileappfinal.GUI_layer.AR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobileappfinal.GUI_layer.Categories.SpecifyCategoryFragment;
import com.example.mobileappfinal.R;

public class CategoriesARFragment extends Fragment {

        public CategoriesARFragment() {
            // Required empty public constructor
        }


        public static CategoriesARFragment newInstance() {
            return new CategoriesARFragment();
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
                    SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Bed","true");

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
                    SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Drawer", "true");

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
                    SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Desk", "true");

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
                    SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Chair", "true");

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
                    SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Sofa", "true");

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
                    SpecifyCategoryFragment specifyCategoryFragment = SpecifyCategoryFragment.newInstance("Table","true");

                    fragmentTransaction.replace(R.id.fragment_place, specifyCategoryFragment);
                    fragmentTransaction.commit();
                }
            });


            return v;
        }
    }
