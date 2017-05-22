package stepic.stepic;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

public class UploadActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;

    ImageView img;

    Marker my_position;
    Button success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpMap();



        final String uri = getIntent().getStringExtra("Uri");
//        img = (ImageView) findViewById(R.id.test);
//        img.setImageURI(Uri.parse(uri));

        success = (Button) findViewById(R.id.success);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SplashActivity.images.add(new Image(Uri.parse(uri), my_position.getPosition(), "한단비", Calendar.getInstance(), 1, -1, "new"));
                finish();

            }
        });


    }


    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        startMaps();
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    protected void startMaps() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.450600, 126.657249), 9.5f));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                my_position.setPosition(marker.getPosition());
            }
        });
        my_position = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.450600, 126.657249))
                .draggable(true)
                .title("내 위치"));
    }



}
