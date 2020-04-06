package com.ahmed.newsfeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ahmed.newsfeed.databinding.ActivityMainBinding;
import com.ahmed.newsfeed.databinding.NavigationHeaderBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener, NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;

    private Fragment navHostFragment;
    private NavController navController;
    private ActionBarDrawerToggle drawerToggle;

    private boolean back = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_drawer,  R.string.close_drawer);
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        drawerToggle.syncState();

        NavigationHeaderBinding headerBinding = NavigationHeaderBinding.inflate(LayoutInflater.from(this));
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(binding.getRoot()).load(R.drawable.profile)
                .apply(options)
                .into(headerBinding.imageViewUserAvatar);
        headerBinding.textViewUserName.setText(getString(R.string.tony_roshdy));
        binding.drawerNavigationView.addHeaderView(headerBinding.getRoot());

        binding.drawerLayout.addDrawerListener(drawerToggle);
        binding.drawerNavigationView.setNavigationItemSelectedListener(this);
        drawerToggle.setToolbarNavigationClickListener(view -> { if(back) onBackPressed(); });

        navController = findNavController(MainActivity.this, R.id.navHostFragment);

        navHostFragment = getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        navController.addOnDestinationChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(!back)
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.item_search:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        if(destination.getId() == R.id.homeFragment) {
            binding.drawerNavigationView.setCheckedItem(R.id.item_explore);
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            drawerToggle.setDrawerIndicatorEnabled(true);
            back = false;
        }else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            drawerToggle.setDrawerIndicatorEnabled(false);
            back = true;
        }
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
