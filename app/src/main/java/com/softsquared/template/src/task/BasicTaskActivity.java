package com.softsquared.template.src.task;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;
import com.softsquared.template.src.task.interfaces.BasicTaskActivityView;
import com.softsquared.template.src.task.models.SocarzoneInfo;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.api.MapReverseGeoCoder;

import java.util.ArrayList;

import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;

public class BasicTaskActivity extends BaseActivity implements MapView.CurrentLocationEventListener,
MapReverseGeoCoder.ReverseGeoCodingResultListener, BasicTaskActivityView {

    private MapView mMapView;

    private static final String LOG_TAG = "BasicTaskActivity_Log";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};

    private boolean currentLocationTrackable = true;

    private ArrayList<SocarzoneInfo> socarzoneInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_task);

        //카카오맵을 띄움
        mMapView = new MapView(this);
        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mMapView);

        //현재위치를 디폴트로 띄워주기 위한 메써드
        mMapView.setCurrentLocationEventListener(this);

        if(!checkLocationServicesStatus()){
            showDialogForLocationServiceSetting();
        }else{
            checkRunTimePermission();
        }

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

        //쏘카존을 표시
        tryGetSocarzone();
    }

    //쏘카존 정보를 얻어오기 위한 함수
    private void tryGetSocarzone(){
        showProgressDialog();

        final TaskService mainService = new TaskService(this);
        mainService.getSocarzone();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mMapView.setShowCurrentLocationMarker(false);
    }

    //권한 없으면 띄울 다이얼로그 -> 설정으로 넘어갈 수 있게 함
    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasicTaskActivity.this);
        builder.setTitle(R.string.basic_task_activity_location_service_not_activate);
        builder.setMessage(R.string.basic_task_activity_location_service_asking);
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()){
                        Log.d(LOG_TAG, "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }
                break;
        }
    }

    //GPS에 대한 권한을 체크하는 파트
    public boolean checkLocationServicesStatus(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //런타임 퍼미션 처리
    void checkRunTimePermission(){
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(BasicTaskActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED ) {
            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식.)
            // 3.  위치 값을 가져올 수 있음
            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(BasicTaskActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(BasicTaskActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(BasicTaskActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(BasicTaskActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    public void setCurrentLocationAsCenter(MapView mapView, MapPoint currentLocation){
        if(currentLocationTrackable){
            MapPoint.GeoCoordinate mapPointGeo = currentLocation.getMapPointGeoCoord();
            Log.i(LOG_TAG, String.format("MapView onCurrentLocationUpdate (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
            currentLocationTrackable=false;
        }
    }

    //implement 때문에 생기는 함수들 - 위치가 업데이터 될 때 사용 다만 일회용으로 사용할 것이니까 따로 사용은 안하겠다
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {
        setCurrentLocationAsCenter(mapView, currentLocation);
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    //주소를 표시하기 위한 역 GEOCODING - 아직 안씀
    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        mapReverseGeoCoder.toString();
        onFinishReverseGeoCoding(s);
    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReverseGeoCoding("Fail");
    }

    private void onFinishReverseGeoCoding(String result) {
        Log.d(LOG_TAG,"Reversed geoCoding Result is : "+result);
    }

    //네트워크통신
    @Override
    public void validateSuccess(ArrayList<SocarzoneInfo> socarzoneInfo) {
        socarzoneInfos = socarzoneInfo;
        markOnMap();
    }

    @Override
    public void validateFailure(String message) {

    }

    //지도에 쏘카존 표시
    public void markOnMap(){
        for(int i=0; i<socarzoneInfos.size(); i++){
            MapPOIItem marker = new MapPOIItem();
            marker.setTag(i);
            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(socarzoneInfos.get(i).getLatitude(),socarzoneInfos.get(i).getLongitude()));
            marker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            marker.setCustomImageResourceId(R.drawable.socar_zone);
            marker.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
            marker.setCustomSelectedImageResourceId(R.drawable.socar_zone_selected);
            mMapView.addPOIItem(marker);
        }
    }
}
