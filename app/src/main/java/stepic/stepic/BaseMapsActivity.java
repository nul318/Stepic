/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stepic.stepic;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    private GoogleMap mMap;
    List<ResolveInfo> info;
    List<Intent> yourIntentsList;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;

    Uri mImageCaptureUri;
    private String absoultePath;


    boolean isMine=false;

    protected int getLayoutId() {
        return R.layout.activity_maps;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setUpMap();


        Intent camIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        Intent gallIntent = new Intent(Intent.ACTION_GET_CONTENT);
        gallIntent.setType("image/*");
        info = new ArrayList<ResolveInfo>();
        yourIntentsList = new ArrayList<Intent>();
        PackageManager packageManager = BaseMapsActivity.this.getPackageManager();
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(camIntent, 0);
        for (ResolveInfo res : listCam) {
            final Intent finalIntent = new Intent(camIntent);
            finalIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            yourIntentsList.add(finalIntent);
            info.add(res);
        }
        List<ResolveInfo> listGall = packageManager.queryIntentActivities(gallIntent, 0);
        for (ResolveInfo res : listGall) {
            final Intent finalIntent = new Intent(gallIntent);
            finalIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            yourIntentsList.add(finalIntent);
            info.add(res);
        }

//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#330000ff")));
//        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));


        //Navigation Drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.add);

        toolbar.setOverflowIcon(drawable);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mHandler = new Handler();




        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp = new Intent(Intent.ACTION_PICK);
                tmp.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(tmp, PICK_FROM_ALBUM);

            }
        });


//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mProgressDialog = ProgressDialog.show(BaseMapsActivity.this, "", "로딩 중입니다.", true);
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            if (mProgressDialog != null && mProgressDialog.isShowing()) {
//                                mProgressDialog.dismiss();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, 1000);
//            }
//
//        });

    }


//    private void openDialog(final Activity context, final List<Intent> intents,
//                            List<ResolveInfo> activitiesInfo) {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        dialog.setTitle("선택");
//        dialog.setAdapter(buildAdapter(context, activitiesInfo),
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        Intent intent = intents.get(id);
//                        Log.e("ACTION", intent.getAction());
//                        if (intent.getAction().equals("android.media.action.IMAGE_CAPTURE")) {
//                            //카메라
//                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
//                            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
//                            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//                            startActivityForResult(intent, PICK_FROM_CAMERA);
//
//                        }
//                        if (intent.getAction().equals("android.intent.action.GET_CONTENT")) {
//                            //갤러리
//                            Intent tmp = new Intent(Intent.ACTION_PICK);
//                            tmp.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
//                            startActivityForResult(tmp, PICK_FROM_ALBUM);
//                        }
//
////                        context.startActivityForResult(intent,0);
//
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

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
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

    /**
     * Run the demo-specific code.
     */
    protected abstract void startMaps();

    protected GoogleMap getMap() {
        return mMap;
    }


    /**
     * Build the list of items to show using the intent_listview_row layout.
     *
     * @param context
     * @param activitiesInfo
     * @return
     */
    private static ArrayAdapter<ResolveInfo> buildAdapter(final Context context, final List<ResolveInfo> activitiesInfo) {
        return new ArrayAdapter<ResolveInfo>(context, R.layout.intent_listview_row, R.id.title, activitiesInfo) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ResolveInfo res = activitiesInfo.get(position);
                ImageView image = (ImageView) view.findViewById(R.id.icon);
                image.setImageDrawable(res.loadIcon(context.getPackageManager()));
                TextView textview = (TextView) view.findViewById(R.id.title);
                textview.setText(res.loadLabel(context.getPackageManager()).toString());

                return view;
            }
        };
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ID", String.valueOf(requestCode));
//        if(requestCode == 0){
//            if(data!=null)
//                Log.e("IntentTester", data.getData().toString());
//            startActivity(new Intent(BaseMapsActivity.this, UploadActivity.class));
//        }

        switch (requestCode) {
            case PICK_FROM_ALBUM:
                if (data == null)
                    return;
                mImageCaptureUri = data.getData();
//                Log.e("Stepic", getRealPathFromURI(this,mImageCaptureUri));
                Intent intent_album = new Intent(BaseMapsActivity.this, UploadActivity.class);
//                intent_album.putExtra("Uri", getRealPathFromURI(this, mImageCaptureUri));
                intent_album.putExtra("Uri", mImageCaptureUri.toString());
                startActivity(intent_album);




                break;
            case PICK_FROM_CAMERA:
                Log.e("PICK_FROM_CAMERA", "진입");
                Intent intent_camera = new Intent("com.android.camera.action.CROP");
                intent_camera.setDataAndType(mImageCaptureUri, "image/*");
                // CROP할 이미지를 200*200 크기로 저장
                intent_camera.putExtra("outputX", 200); // CROP한 이미지의 x축 크기
                intent_camera.putExtra("outputY", 200); // CROP한 이미지의 y축 크기
                intent_camera.putExtra("aspectX", 1); // CROP 박스의 X축 비율
                intent_camera.putExtra("aspectY", 1); // CROP 박스의 Y축 비율
                intent_camera.putExtra("scale", true);
                intent_camera.putExtra("return-data", true);
                startActivityForResult(intent_camera, CROP_FROM_IMAGE); // CROP_FROM_CAMERA case문 이동
                break;
            case CROP_FROM_IMAGE:
                if (resultCode != RESULT_OK) {
                    return;
                }
                final Bundle extras = data.getExtras();
                // CROP된 이미지를 저장하기 위한 FILE 경로
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Stepic/" + System.currentTimeMillis() + ".jpg";
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data"); // CROP된 BITMAP
                    absoultePath = filePath;
                    break;
                }
                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }
                Intent intent_crop = new Intent(BaseMapsActivity.this, UploadActivity.class);
                intent_crop.putExtra("Uri", absoultePath);
                startActivity(intent_crop);
                break;
//            default:
//
//                break;

        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
