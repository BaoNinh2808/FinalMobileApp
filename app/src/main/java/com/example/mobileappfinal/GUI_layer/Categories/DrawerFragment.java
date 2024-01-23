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

public class DrawerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageButton drawer1;
    private ImageButton drawer2;
    private ImageButton drawer3, drawer4, drawer5, drawer6;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton back_button_Drawer;
    public DrawerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrawerFragment newInstance(String param1, String param2) {
        DrawerFragment fragment = new DrawerFragment();
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
        // Inflate the layout for this fragment,
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_drawer, container, false);
        back_button_Drawer = (ImageButton)v.findViewById(R.id.back_button2);
        back_button_Drawer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new CategoriesFragment());
                fragmentTransaction.commit();
            }
        });
        drawer1 = (ImageButton) v.findViewById(R.id.drawer1);
        drawer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/storage1.obj?alt=media&token=f64082eb-a85f-43dc-9f76-f08b2931bbcf";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        drawer2 = (ImageButton) v.findViewById(R.id.drawer2);
        drawer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/storage2.obj?alt=media&token=6fb3909b-f59c-44b5-8365-e5c00848855a";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        drawer3 = (ImageButton) v.findViewById(R.id.drawer3);
        drawer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/storage3.obj?alt=media&token=3c15fcec-6a05-4f24-995e-fd48ad37ff78";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture1.png?alt=media&token=5abc06df-f95d-4dbc-a6ea-e6524de703a0";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });

        drawer4 = (ImageButton) v.findViewById(R.id.drawer4);
        drawer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/storage1.obj?alt=media&token=f64082eb-a85f-43dc-9f76-f08b2931bbcf";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        drawer5 = (ImageButton) v.findViewById(R.id.drawer5);
        drawer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/storage2.obj?alt=media&token=6fb3909b-f59c-44b5-8365-e5c00848855a";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/table_texture5.png?alt=media&token=3fdc7c7f-c55f-4467-bd20-e668c8144cce";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        drawer6 = (ImageButton) v.findViewById(R.id.drawer6);
        drawer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/storage3.obj?alt=media&token=3c15fcec-6a05-4f24-995e-fd48ad37ff78";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture1.png?alt=media&token=5abc06df-f95d-4dbc-a6ea-e6524de703a0";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }

            }
        });
        return v;
    }
}

