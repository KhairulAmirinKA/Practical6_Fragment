package com.khairul.practical6_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //make the toolbar visible
        Toolbar toolbar= findViewById(R.id.TBMainAct);
        setSupportActionBar(toolbar);

        //bind NavHostFragment with NavController
        NavHostFragment host= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.NHFMain);
        NavController navController = host.getNavController();

        //bind toolbar with navController
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //bottom nav
        setupBottomNavMenu(navController);

        //side nav drawer
        drawerLayout= findViewById(R.id.DLMain);

        toggle= new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try {
            Navigation.findNavController(this, R.id.NHFMain)
                    .navigate(item.getItemId() );
            return true;
        }

        catch (Exception e){
            return super.onOptionsItemSelected(item);
        }
    }


    //back button in the appbar
    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.NHFMain).navigateUp();
    }

    //setup bottom nav menu
    private void setupBottomNavMenu(NavController navController){

        BottomNavigationView bottomNav= findViewById(R.id.bottom_nav_view);

        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    //setup nav menu
    private void setupNavMenu(NavController navController){
        NavigationView sideNav= findViewById(R.id.sideNav);

        NavigationUI.setupWithNavController(sideNav, navController);
        
        
    }
}