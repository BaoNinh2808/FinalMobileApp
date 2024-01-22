package com.example.mobileappfinal.GUI_layer.Categories;

import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.cnt;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.isObjectReplaced;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.obj_file;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.png_file;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobileappfinal.R;

public class DeskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton desk1;
    private ImageButton desk2, desk3, desk4, desk5, desk6;
    private ImageButton back_button_Desk;
    public DeskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeskFragment newInstance(String param1, String param2) {
        DeskFragment fragment = new DeskFragment();
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
        // Inflate the layout for this fragment
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_desk, container, false);
        back_button_Desk = (ImageButton)v.findViewById(R.id.back_button3);
        back_button_Desk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new CategoriesFragment());
                fragmentTransaction.commit();
            }
        });

        desk1 = (ImageButton) v.findViewById(R.id.desk1);
        desk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk1.obj";
                    png_file = "models/table_texture6.png";
                    isObjectReplaced = true;
                }
            }
        });
        desk2 = (ImageButton) v.findViewById(R.id.desk2);
        desk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture5.png";
                    isObjectReplaced = true;
                }
            }
        });

        desk3 = (ImageButton) v.findViewById(R.id.desk3);
        desk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk1.obj";
                    png_file = "models/table_texture4.png";
                    isObjectReplaced = true;
                }
            }
        });
        desk4 = (ImageButton) v.findViewById(R.id.desk4);
        desk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture4.png";
                    isObjectReplaced = true;
                }
            }
        });

        desk5 = (ImageButton) v.findViewById(R.id.desk5);
        desk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk1.obj";
                    png_file = "models/table_texture5.png";
                    isObjectReplaced = true;
                }
            }
        });
        desk6 = (ImageButton) v.findViewById(R.id.desk6);
        desk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_file = "models/desk2.obj";
                    png_file = "models/table_texture6.png";
                    isObjectReplaced = true;
                }
            }
        });
        return v;
    }
}