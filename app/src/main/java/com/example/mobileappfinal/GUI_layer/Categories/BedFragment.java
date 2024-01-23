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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.Data_layer.Product.ProductDatabase;
import com.example.mobileappfinal.R;

import java.util.List;

public class BedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton back_button_LvRoom;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ImageButton bed1;
    private ImageButton bed2;
    private ImageButton bed3, bed4, bed5, bed6;
    public BedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BedFragment newInstance(String param1, String param2) {
        BedFragment fragment = new BedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BedFragment newInstance() {
        return new BedFragment();
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
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_bed, container, false);
        back_button_LvRoom = (ImageButton)v.findViewById(R.id.back_button1);
        back_button_LvRoom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                ((MainActivity)getActivity()).replaceFragment(MainFragment.newInstance());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new CategoriesFragment());
                fragmentTransaction.commit();
            }
        });

         recyclerView = v.findViewById(R.id.recyclerView);
        ProductDatabase productDatabase = ProductDatabase.getInstance();
         List<Product> products = productDatabase.getProductByCategory("plant");
         categoryAdapter = new CategoryAdapter(getContext(), products);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                if (cnt == 0) {
                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed1.obj?alt=media&token=306f9129-061b-40a9-bf2f-0341e8a02e28";
                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture3.png?alt=media&token=406a003f-bac4-4e0e-92cb-72bbc791e920";
//                    cnt = cnt + 1;
                    isObjectReplaced = true;
                }
            }
        });

        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));

//        bed1 = (ImageButton) v.findViewById(R.id.bed1);
//        bed1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cnt == 0) {
//                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed1.obj?alt=media&token=306f9129-061b-40a9-bf2f-0341e8a02e28";
//                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture3.png?alt=media&token=406a003f-bac4-4e0e-92cb-72bbc791e920";
////                    cnt = cnt + 1;
//                    isObjectReplaced = true;
//                }
//
//            }
//        });
//        bed2 = (ImageButton) v.findViewById(R.id.bed2);
//        bed2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cnt == 0) {
//                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed2.obj?alt=media&token=31393109-0840-4019-8cd6-8838e6643ddd";
//                    png_url = "models/table_texture4.png";
////                    cnt = cnt + 1;
//                    isObjectReplaced = true;
//                }
//
//            }
//        });
//        bed3 = (ImageButton) v.findViewById(R.id.bed3);
//        bed3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cnt == 0) {
//                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed3.obj?alt=media&token=4df8f545-5fa7-45c2-bfdc-3410a9dc86c7";
//                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture3.png?alt=media&token=406a003f-bac4-4e0e-92cb-72bbc791e920";
////                    cnt = cnt + 1;
//                    isObjectReplaced = true;
//                }
//
//            }
//        });
//        bed4 = (ImageButton) v.findViewById(R.id.bed4);
//        bed4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cnt == 0) {
//                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed4.obj?alt=media&token=aac4f370-8b9c-4222-93c1-afdc5b63a4a2";
//                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture5.png?alt=media&token=6219f056-eda6-4286-8ce0-3b8455ac0165";
//                    isObjectReplaced = true;
//                }
//            }
//        });
//        bed5 = (ImageButton) v.findViewById(R.id.bed5);
//        bed5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cnt == 0) {
//                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed1.obj?alt=media&token=306f9129-061b-40a9-bf2f-0341e8a02e28";
//                    png_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed_texture1.png?alt=media&token=5abc06df-f95d-4dbc-a6ea-e6524de703a0";
//                    isObjectReplaced = true;
//                }
//            }
//        });
//        bed6 = (ImageButton) v.findViewById(R.id.bed6);
//        bed6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cnt == 0) {
//                    obj_url = "https://firebasestorage.googleapis.com/v0/b/mobileappfinal-5d7c6.appspot.com/o/bed2.obj?alt=media&token=31393109-0840-4019-8cd6-8838e6643ddd";
//                    png_url = "models/table_texture2.png";
//                    isObjectReplaced = true;
//                }
//            }
//        });


        return v;
    }
}