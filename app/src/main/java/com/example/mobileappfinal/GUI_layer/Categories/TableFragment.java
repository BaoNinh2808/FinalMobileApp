package com.example.mobileappfinal.GUI_layer.Categories;

import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.cnt;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.isObjectReplaced;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.obj_url;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.png_url;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobileappfinal.R;

public class TableFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton table1;
    private ImageButton table2;
    private ImageButton table3;
    private ImageButton table4;
    private ImageButton table5;
    private ImageButton table6;

    private ImageButton back_button_Table;
    public TableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TableFragment newInstance(String param1, String param2) {
        TableFragment fragment = new TableFragment();
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
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_table, container, false);
        back_button_Table = (ImageButton)v.findViewById(R.id.back_button6);
        back_button_Table.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new CategoriesFragment());
                fragmentTransaction.commit();
            }
        });

        table1 = (ImageButton) v.findViewById(R.id.table1);
        table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table1.obj?alt=media&token=09944712-a8d6-4157-9db8-08eec2be407e";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture4.png?alt=media&token=0a68da70-a582-4470-bb3e-174bc80db289";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        table2 = (ImageButton) v.findViewById(R.id.table2);
        table2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table2.obj?alt=media&token=14ac85c9-8ab5-4a41-bab6-c1dab5865a15";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture4.png?alt=media&token=0a68da70-a582-4470-bb3e-174bc80db289";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        table3 = (ImageButton) v.findViewById(R.id.table3);
        table3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table3.obj?alt=media&token=cf6053d6-39d5-47d7-a949-d62bde507832";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture6.png?alt=media&token=4484f727-9186-46e9-979e-3b23918b1119";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        table4 = (ImageButton) v.findViewById(R.id.table4);
        table4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table4.obj?alt=media&token=3595e8de-5d7d-459f-965e-95b66eb60a41";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
                    isObjectReplaced = true;
                }
            }
        });
        table5 = (ImageButton) v.findViewById(R.id.table5);
        table5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table5.obj?alt=media&token=f8a9b23e-d51b-4bff-85dc-3ce747ab7c28";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
                    isObjectReplaced = true;
                }
            }
        });
        table6 = (ImageButton) v.findViewById(R.id.table6);
        table6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table6.obj?alt=media&token=4e624992-28d8-4796-95f5-dcf3fbf3288f";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
                    isObjectReplaced = true;
                }
            }
        });
        return v;
    }
}
