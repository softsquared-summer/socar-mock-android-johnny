package com.softsquared.template.src.task;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.drawerlayout.widget.DrawerLayout;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;

import net.daum.mf.map.api.MapView;

import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;

public class BasicTaskActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_task);

        //카카오맵을 띄움
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //옆에서 열리는 drawer의 스와이프 기능을 방지
        DrawerLayout drawerMenu = findViewById(R.id.basic_task_drawer_layout);
        drawerMenu.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED);

        //리스너들 등록
        ImageButton btnOpenMenu = findViewById(R.id.basic_task_btn_drawer_open);
        btnOpenMenu.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                DrawerLayout drawerMenu = (DrawerLayout) findViewById(R.id.basic_task_drawer_layout);
                if(!drawerMenu.isDrawerOpen(Gravity.LEFT)){
                    drawerMenu.openDrawer(Gravity.LEFT);
                }
            }
        });

    }
}
