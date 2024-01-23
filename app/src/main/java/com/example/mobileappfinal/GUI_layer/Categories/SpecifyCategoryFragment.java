package com.example.mobileappfinal.GUI_layer.Categories;

import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.cnt;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.isObjectReplaced;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.obj_url;
import static com.example.mobileappfinal.GUI_layer.AR.ARMainActivity.png_url;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.Business_layer.Object3D.Object3DListManager;
import com.example.mobileappfinal.DTO.Object3D;
import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.Data_layer.Product.ProductDatabase;
import com.example.mobileappfinal.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecifyCategoryFragment extends Fragment {
    private static final String CATEGORY = "Bed";

    private ImageButton back_button;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    public SpecifyCategoryFragment() {
        // Required empty public constructor
    }

    public static SpecifyCategoryFragment newInstance(String category) {
        SpecifyCategoryFragment fragment = new SpecifyCategoryFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    public static SpecifyCategoryFragment newInstance() {
        return new SpecifyCategoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_specify_category, container, false);
        back_button = (ImageButton)v.findViewById(R.id.back_button1);
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new CategoriesFragment());
                fragmentTransaction.commit();
            }
        });

        recyclerView = v.findViewById(R.id.recyclerView);
        ProductDatabase productDatabase = ProductDatabase.getInstance();

        String category = getArguments().getString(CATEGORY);
        List<Product> products = productDatabase.getProductByCategory(category);
        categoryAdapter = new CategoryAdapter(getContext(), products);

        Object3DListManager object3DListManager = new Object3DListManager();

        Map<String, List<String>> map = new HashMap<>();
        for (Product product : products) {
            Object3D object3D = object3DListManager.getObject3DById(product.getId());
            map.put(product.getId(), Arrays.asList( object3D.getObjUrl(), object3D.getTextureUrl()));
        }

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                if (cnt == 0) {
                    obj_url = map.get(product.getId()).get(0);
                    png_url = map.get(product.getId()).get(1);
                    Log.d("obj_url", obj_url);
                    Log.d("png_url", png_url);
                    isObjectReplaced = true;
                }
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        recyclerView.setAdapter(categoryAdapter);
        return v;
    }
}