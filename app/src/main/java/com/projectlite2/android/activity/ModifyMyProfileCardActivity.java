package com.projectlite2.android.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joaquimley.faboptions.FabOptions;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;

public class ModifyMyProfileCardActivity extends AppCompatActivity implements View.OnClickListener {
    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_profile_card);

//        FabOptions fabOptions = (FabOptions) findViewById(R.id.fab_options);
//        fabOptions.setButtonsMenu(R.menu.menu_node_in_project_tree);

        NavController controller = Navigation.findNavController(this, R.id.myprofile_fragment);
        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
    public boolean onSupportNavigateUp(){
        NavController controller = Navigation.findNavController(this,R.id.myprofile_fragment);
        return controller.navigateUp();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}

