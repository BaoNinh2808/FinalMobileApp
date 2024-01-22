package com.example.mobileappfinal.GUI_layer.Categories;

import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.cnt;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.isObjectReplaced;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.obj_file;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.png_file;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mobileappfinal.R;

//import static com.google.ar.core.examples.java.helloar.MainActivity.cnt;
//import static com.google.ar.core.examples.java.helloar.MainActivity.isObjectReplaced;
//import static com.google.ar.core.examples.java.helloar.MainActivity.obj_file;
//import static com.google.ar.core.examples.java.helloar.MainActivity.png_file;

public class ChairFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton chair1;
    private ImageButton chair2;
    private ImageButton chair3;
    private ImageButton chair4;
    private ImageButton chair5;
    private ImageButton chair6;
    private ImageButton back_button_Chair;
    public ChairFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChairFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChairFragment newInstance(String param1, String param2) {
        ChairFragment fragment = new ChairFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment1
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_chair, container, false);
        back_button_Chair = (ImageButton)v.findViewById(R.id.back_button4);
        back_button_Chair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new CategoriesFragment());
                fragmentTransaction.commit();
            }
        });

        chair1 = (ImageButton) v.findViewById(R.id.chair1);
        chair1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/chair1.obj";
                    png_file = "models/table_texture5.png";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        chair2 = (ImageButton) v.findViewById(R.id.chair2);
        chair2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/chair2.obj";
                    png_file = "models/table_texture4.png";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        chair3 = (ImageButton) v.findViewById(R.id.chair3);
        chair3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/chair3.obj";
                    png_file = "models/table_texture5.png";
////                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        chair4 = (ImageButton) v.findViewById(R.id.chair4);
        chair4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/chair4.obj";
                    png_file = "models/table_texture5.png";
                    isObjectReplaced = true;
                }
            }
        });
        chair5 = (ImageButton) v.findViewById(R.id.chair5);
        chair5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/chair2.obj";
                    png_file = "models/table_texture5.png";
                    isObjectReplaced = true;
                }
            }
        });
        chair6 = (ImageButton) v.findViewById(R.id.chair6);
        chair6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/chair2.obj";
                    png_file = "models/table_texture4.png";
                    isObjectReplaced = true;
                }
            }
        });
        return v;
    }
}

