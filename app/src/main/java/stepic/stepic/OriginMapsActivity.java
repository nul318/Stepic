//package stepic.stepic;
//
//import android.app.Activity;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.maps.android.clustering.ClusterManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
//
//    private GoogleMap mMap;
//    List<ResolveInfo> info;
//    List<Intent> yourIntentsList;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        setContentView(R.layout.activity_maps);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        Intent camIntent = new Intent("android.media.action.IMAGE_CAPTURE");
//        Intent gallIntent=new Intent(Intent.ACTION_GET_CONTENT);
//        gallIntent.setType("image/*");
//        info=new ArrayList<ResolveInfo>();
//        yourIntentsList = new ArrayList<Intent>();
//        PackageManager packageManager = MapsActivity.this.getPackageManager();
//        List<ResolveInfo> listCam = packageManager.queryIntentActivities(camIntent, 0);
//        for (ResolveInfo res : listCam) {
//            final Intent finalIntent = new Intent(camIntent);
//            finalIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//            yourIntentsList.add(finalIntent);
//            info.add(res);
//        }
//        List<ResolveInfo> listGall = packageManager.queryIntentActivities(gallIntent, 0);
//        for (ResolveInfo res : listGall) {
//            final Intent finalIntent = new Intent(gallIntent);
//            finalIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//            yourIntentsList.add(finalIntent);
//            info.add(res);
//        }
//
////        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
////        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#330000ff")));
////        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));
//
//
//        //Navigation Drawer
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(MapsActivity.this, "Floating Action", Toast.LENGTH_SHORT).show();
////                startActivity(new Intent(MapsActivity.this, UploadActivity.class));
//
//
//
//
//                openDialog(MapsActivity.this ,yourIntentsList,info);
////                startActivityForResult(camIntent, 0);
//
//
////                final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
////                startActivityForResult(intent, CAPTURE_IMAGE);
//
//            }
//        });
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        //Navigation Drawer
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        ClusterManager<Image> mClusterManager = new ClusterManager<Image>(MapsActivity.this, mMap);
//        mMap.setOnCameraIdleListener(mClusterManager);
//        LatLng INHA = new LatLng(37.450600, 126.657249);
////        MarkerOptions markerOptions = new MarkerOptions();
////                .position(INHA)
////                .title("서울")
////                .snippet("한국의 수도");
////        mMap.addMarker(markerOptions);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(INHA));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
//
//        for (Image image : SplashActivity.images) {
////            markerOptions = new MarkerOptions()
////                    .position(image.location);
////                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(image.url_memory, 200, 150)));
////            mMap.addMarker(markerOptions);
//            mClusterManager.addItem(image);
//        }
//    }
//    public Bitmap resizeMapIcons(int iconName, int width, int height){
//        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), iconName);
//        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
//        return resizedBitmap;
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    private static void openDialog(final Activity context, final List<Intent> intents,
//                                   List<ResolveInfo> activitiesInfo) {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        dialog.setTitle("선택");
//        dialog.setAdapter(buildAdapter(context, activitiesInfo),
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent intent = intents.get(id);
//                        context.startActivityForResult(intent,0);
//
//                    }
//                });
//        dialog.setNeutralButton("취소",
//                new android.content.DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        dialog.show();
//
//    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == 0){
////            Log.i("IntentTester", data.getData().toString());
//            startActivity(new Intent(MapsActivity.this, UploadActivity.class));
//        }
//    }
//    /**
//     * Build the list of items to show using the intent_listview_row layout.
//     * @param context
//     * @param activitiesInfo
//     * @return
//     */
//    private static ArrayAdapter<ResolveInfo> buildAdapter(final Context context, final List<ResolveInfo> activitiesInfo) {
//        return new ArrayAdapter<ResolveInfo>(context, R.layout.intent_listview_row,R.id.title,activitiesInfo){
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                ResolveInfo res=activitiesInfo.get(position);
//                ImageView image=(ImageView) view.findViewById(R.id.icon);
//                image.setImageDrawable(res.loadIcon(context.getPackageManager()));
//                TextView textview=(TextView)view.findViewById(R.id.title);
//                textview.setText(res.loadLabel(context.getPackageManager()).toString());
//                return view;
//            }
//        };
//    }
//
//
//
//}
