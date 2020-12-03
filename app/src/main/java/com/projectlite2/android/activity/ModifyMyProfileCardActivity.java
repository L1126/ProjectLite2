package com.projectlite2.android.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joaquimley.faboptions.FabOptions;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;

public class ModifyMyProfileCardActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_profile_card);

        FabOptions fabOptions = (FabOptions) findViewById(R.id.fab_options);
        fabOptions.setButtonsMenu(R.menu.menu_node_in_project_tree);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDelNode:
                MyApplication.ToastyInfo("666");
                break;
            default:
                break;
        }
    }
}

