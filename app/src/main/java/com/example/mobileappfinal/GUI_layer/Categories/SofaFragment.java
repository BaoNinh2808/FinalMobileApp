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

public class SofaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton sofa1;
    private ImageButton sofa2;
    private ImageButton sofa3;
    private ImageButton sofa4;
    private ImageButton sofa5;
    private ImageButton sofa6;

    private ImageButton back_button_Sofa;
    public SofaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SofaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SofaFragment newInstance(String param1, String param2) {
        SofaFragment fragment = new SofaFragment();
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
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sofa, container, false);
        back_button_Sofa = (ImageButton)v.findViewById(R.id.back_button5);
        back_button_Sofa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new CategoriesFragment());
                fragmentTransaction.commit();
            }
        });

        sofa1 = (ImageButton) v.findViewById(R.id.sofa1);
        sofa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/sofa1.obj?alt=media&token=879cb97c-208b-4c3d-ae62-4f15fed89cfa";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture3.png?alt=media&token=406a003f-bac4-4e0e-92cb-72bbc791e920";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        sofa2 = (ImageButton) v.findViewById(R.id.sofa2);
        sofa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/sofa2.obj?alt=media&token=54d2df7e-5fa3-4f21-9d25-ad393dcfa3d8";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        sofa3 = (ImageButton) v.findViewById(R.id.sofa3);
        sofa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/sofa3.obj?alt=media&token=dd58fa31-1c4c-43b4-9710-579b20c5aaa7";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        sofa4 = (ImageButton) v.findViewById(R.id.sofa4);
        sofa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/sofa4.obj?alt=media&token=7180292d-ba6c-404d-bad0-35187f6d16ba";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture4.png?alt=media&token=0a68da70-a582-4470-bb3e-174bc80db289";
                    isObjectReplaced = true;
                }
            }
        });
        sofa5 = (ImageButton) v.findViewById(R.id.sofa5);
        sofa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/sofa4.obj?alt=media&token=7180292d-ba6c-404d-bad0-35187f6d16ba";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture4.png?alt=media&token=0a68da70-a582-4470-bb3e-174bc80db289";
                    isObjectReplaced = true;
                }
            }
        });
        sofa6 = (ImageButton) v.findViewById(R.id.sofa6);
        sofa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/sofa4.obj?alt=media&token=7180292d-ba6c-404d-bad0-35187f6d16ba";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture6.png?alt=media&token=4484f727-9186-46e9-979e-3b23918b1119";
                    isObjectReplaced = true;
                }
            }
        });
        return v;
    }
}
