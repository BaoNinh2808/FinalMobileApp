package com.example.mobileappfinal.GUI_layer.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileappfinal.DTO.User;
import com.example.mobileappfinal.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UserActivity extends AppCompatActivity {
    public static final int TAB_POSITION = 1;

    private User user;

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


