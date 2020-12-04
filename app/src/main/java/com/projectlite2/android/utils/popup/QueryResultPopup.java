package com.projectlite2.android.utils.popup;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.MultiItemTypeAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.lxj.xpopup.widget.VerticalRecyclerView;
import com.projectlite2.android.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class QueryResultPopup extends BottomPopupView {

    VerticalRecyclerView recyclerView;

    public QueryResultPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        recyclerView = findViewById(R.id.recyclerView);

    }

    // 最大高度为Window的0.85
    @Override
    protected int getMaxHeight() {

        return (int) (XPopupUtils.getAppHeight(getContext()) * .85f);
    }
}
