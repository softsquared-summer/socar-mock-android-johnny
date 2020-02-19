package com.softsquared.template.src.task;

import android.os.Bundle;
import android.view.ViewGroup;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;

import net.daum.mf.map.api.MapView;

public class BasicTaskActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_task);

        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

    }
}
