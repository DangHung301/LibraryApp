package com.dh8c2.library;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dh8c2.library.model.LoaiSach;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PRF_NAME = "shared_preferences";
    public static final String FIRST_INSTALL = "first_install";

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private int temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.nav_view);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        Fragment fragmenta = new GiaoTrinhFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragmenta).commit();
        mNavigationView.getMenu().getItem(0).setChecked(true);


        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Fragment fragment = null;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_giao_trinh:
                                fragment = new GiaoTrinhFragment();
                                mNavigationView.getMenu().getItem(0).setChecked(true);
                                mNavigationView.getMenu().getItem(1).setChecked(false);
                                mNavigationView.getMenu().getItem(2).setChecked(false);
                                break;
                            case R.id.nav_tai_lieu:
                                fragment = new TaiLieuFragment();
                                mNavigationView.getMenu().getItem(0).setChecked(false);
                                mNavigationView.getMenu().getItem(1).setChecked(true);
                                mNavigationView.getMenu().getItem(2).setChecked(false);
                                break;
                            default:
                                fragment = new AdminFragment();
                                mNavigationView.getMenu().getItem(0).setChecked(false);
                                mNavigationView.getMenu().getItem(1).setChecked(false);
                                mNavigationView.getMenu().getItem(2).setChecked(true);
                        }
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mNavigationView.getMenu().getItem(temp).setChecked(false);

                        return true;
                    }
                });
        initDatabase();
    }


    private void initDatabase() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SharedPreferences preferences = getSharedPreferences(SHARED_PRF_NAME, Context.MODE_PRIVATE);
        boolean isFirstInstall = preferences.getInt(FIRST_INSTALL, 0) < 1;

        if (isFirstInstall) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(FIRST_INSTALL, 1);
            editor.apply();

            LoaiSach giaoTrinh = new LoaiSach("Sách giáo trình");
            LoaiSach taiLieu = new LoaiSach("Tài liệu tham khảo");
            databaseHelper.addLoaiSach(giaoTrinh);
            databaseHelper.addLoaiSach(taiLieu);
        }
    }
}