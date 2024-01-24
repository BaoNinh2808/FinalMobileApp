package com.example.mobileappfinal.GUI_layer.Categories;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappfinal.R;

public class MainCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        // Obtain a reference to the CategoriesFragment
        CategoriesFragment categoriesFragment = (CategoriesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_place);

        // Check if the fragment is not null (it exists in the layout)
        if (categoriesFragment != null) {
            // Create a Bundle to hold your arguments
            Bundle args = new Bundle();
            args.putString("isCallByAR", "false");  // Example argument, replace with your actual data

            // Set the arguments on the fragment
            categoriesFragment.setArguments(args);
        }
    }
}
