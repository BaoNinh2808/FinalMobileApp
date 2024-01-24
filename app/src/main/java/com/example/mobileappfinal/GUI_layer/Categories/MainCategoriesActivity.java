package com.example.mobileappfinal.GUI_layer.Categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobileappfinal.GUI_layer.Cart.CartActivity;
import com.example.mobileappfinal.GUI_layer.Home.HomeActivity;
import com.example.mobileappfinal.GUI_layer.SignIn.SignInActivity;
import com.example.mobileappfinal.GUI_layer.User.UserActivity;
import com.example.mobileappfinal.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainCategoriesActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageView closeDrawer;
    private ImageView iconNav;
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

        menuNavigation();
        setNavIconClick();
    }

    private void setNavIconClick() {
        iconNav = findViewById(R.id.iconNavPage_category);
        iconNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void menuNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout_category);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Initialize NavigationView and set its item click listener
        navigationView = findViewById(R.id.navigation_view_category);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_user) {
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (item.getItemId() == R.id.menu_category){
                    return true;
                }
                else if (item.getItemId() == R.id.menu_logout) {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Accessing the button in the header and setting a click listener
        View headerView = navigationView.getHeaderView(0);
        closeDrawer = headerView.findViewById(R.id.closeDrawer);
        closeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click in the header
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

}
