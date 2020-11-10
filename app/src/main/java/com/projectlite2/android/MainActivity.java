package com.projectlite2.android;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.navigation.Navigation.findNavController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        //  导航栏跳转配置
        BottomNavigationView bottomNav=findViewById(R.id.navBottom);
        NavController navController=findNavController(this,R.id.navCtrlFragment);
        AppBarConfiguration appBarConfig=new AppBarConfiguration.Builder(bottomNav.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfig);
        NavigationUI.setupWithNavController(bottomNav,navController);
        //  导航栏跳转配置

    }
}