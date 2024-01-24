package com.example.mobileappfinal.GUI_layer.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileappfinal.DTO.User;
import com.example.mobileappfinal.GUI_layer.Cart.CartActivity;
import com.example.mobileappfinal.GUI_layer.Categories.MainCategoriesActivity;
import com.example.mobileappfinal.GUI_layer.Home.HomeActivity;
import com.example.mobileappfinal.GUI_layer.SignIn.SignInActivity;
import com.example.mobileappfinal.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {
    public static final int TAB_POSITION = 1;

    private User user;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageView closeDrawer;
    private ImageView iconNav;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        FragmentStateAdapter pagerAdapter = new UserFragmentPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // Kết nối TabLayout với ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("History");
                    } else {
                        tab.setText("Favorite");
                    }
                }).attach();

        menuNavigation();
        setNavIconClick();
    }

    private void setNavIconClick() {
        iconNav = findViewById(R.id.iconNavPageUser);
        iconNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void menuNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout_user);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Initialize NavigationView and set its item click listener
        navigationView = findViewById(R.id.navigation_view_user);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_user) {
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_category) {
                    Intent intent = new Intent(getApplicationContext(), MainCategoriesActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_logout) {
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

    private static class UserFragmentPagerAdapter extends FragmentStateAdapter {

        public UserFragmentPagerAdapter(AppCompatActivity activity) {
            super(activity);
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Tạo và trả về fragment tương ứng với vị trí
            if (position == 0) {
                return new HistoryFragment();
            } else {
                return new FavoriteFragment();
            }
        }
    }
}


