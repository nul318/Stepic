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

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates heavy customisation of the look of rendered clusters.
 */
 public  class MapsActivity extends BaseMapsActivity implements ClusterManager.OnClusterClickListener<Image>, ClusterManager.OnClusterInfoWindowClickListener<Image>, ClusterManager.OnClusterItemClickListener<Image>, ClusterManager.OnClusterItemInfoWindowClickListener<Image> {
    private ClusterManager<Image> mClusterManager;
    static ArrayList<Image> selected_data_list = new ArrayList<>();;
    FloatingActionButton fab;
    int current_category=-1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SplashActivity.selected_images.clear();
                if(!isMine){
                    isMine=true;
                    fab.setImageDrawable(ContextCompat.getDrawable(MapsActivity.this, R.drawable.all));
                    for(Image image : SplashActivity.images) {
                        if(current_category==-1 && image.writer.equals("한단비")){
                            SplashActivity.selected_images.add(image);
                        }
                        else if(image.writer.equals("한단비") && image.category==current_category)
                            SplashActivity.selected_images.add(image);
                    }
                }else{
                    isMine=false;
                    fab.setImageDrawable(ContextCompat.getDrawable(MapsActivity.this, R.drawable.my));
                    for(Image image : SplashActivity.images) {
                        if(current_category==-1){
                            SplashActivity.selected_images.add(image);
                        }
                        else if(image.category==current_category)
                            SplashActivity.selected_images.add(image);
                    }
                }



                if(mClusterManager!=null){
                    Log.e("진입", "진입");
                    getMap().clear();
                    mClusterManager.clearItems();
                    mClusterManager=null;
                    startMaps();
                }


                Toast.makeText(MapsActivity.this, "Floating Action", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Draws profile photos inside markers (using IconGenerator).
     * When there are multiple people in the cluster, draw multiple photos (using MultiDrawable).
     */
    private class PersonRenderer extends DefaultClusterRenderer<Image> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;




        public PersonRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = 100;
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = 2;
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(Image image, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
//            mImageView.setImageResource(image.url_memory);
            if(image.url_memory!=-1){
                mImageView.setImageResource(image.url_memory);
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                Glide.with(MapsActivity.this).load("").placeholder(image.url_memory).centerCrop().into(mImageView);
            }else{
                String real_path = getPath(image.url);
                Log.e("Real_Path", real_path);


//                Glide.with(MapsActivity.this).load(real_path)
//                        .centerCrop()
//                        .into(mImageView);
                mImageView.setImageURI(Uri.parse(real_path));
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            Bitmap icon = mIconGenerator.makeIcon();

            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(image.name);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<Image> cluster, MarkerOptions markerOptions) {
            // Draw multiple people.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
            List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;
            Drawable drawable = null;
//            int drawable = -1;
            for (Image p : cluster.getItems()) {
                // Draw 4 at most.
//                if (profilePhotos.size() == 4) break;
//                if (profilePhotos.size() == 1) break;

                if(p.url_memory!=-1){
                    drawable = getResources().getDrawable(p.url_memory);
                    drawable.setBounds(0, 0, 600, 600);
                }else{
                    String real_path = getPath(p.url);
                    Log.e("Real_Path", real_path);
                    drawable = Drawable.createFromPath(real_path);
                }

                break;
//                profilePhotos.add(drawable);
            }
//            MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
//            multiDrawable.setBounds(0, 0, 600, 600);

            mClusterImageView.setImageDrawable(drawable);


            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }

    @Override
    public boolean onClusterClick(Cluster<Image> cluster) {
        // Show a toast with some info when the cluster is clicked.
        Intent intent = new Intent(MapsActivity.this, GalleryActivity.class);
        selected_data_list.clear();
        for (Image image : cluster.getItems()){
            selected_data_list.add(image);
            Log.e("data", image.name);
        }

        startActivity(intent);




//        String firstName = cluster.getItems().iterator().next().name;
//        Toast.makeText(this, cluster.getSize() + " (including " + firstName + ")", Toast.LENGTH_SHORT).show();
//
//        // Zoom in the cluster. Need to create LatLngBounds and including all the cluster items
//        // inside of bounds, then animate to center of the bounds.
//
//        // Create the builder to collect all essential cluster items for the bounds.
//        LatLngBounds.Builder builder = LatLngBounds.builder();
//        for (ClusterItem item : cluster.getItems()) {
//            builder.include(item.getPosition());
//        }
//        // Get the LatLngBounds
//        final LatLngBounds bounds = builder.build();
//
//        // Animate camera to the bounds
//        try {
//            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<Image> cluster) {
        // Does nothing, but you could go to a list of the users.
    }

    @Override
    public boolean onClusterItemClick(Image item) {
        // Does nothing, but you could go into the user's profile page, for example.
        Intent intent = new Intent(MapsActivity.this, GalleryActivity.class);
        selected_data_list.clear();
        selected_data_list.add(item);
        startActivity(intent);
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(Image item) {
        // Does nothing, but you could go into the user's profile page, for example.
    }
    @Override
    protected void onResume() {
        super.onResume();
        SplashActivity.selected_images.clear();
        for(Image image : SplashActivity.images) {
            SplashActivity.selected_images.add(image);
        }
        if(mClusterManager!=null){
            Log.e("진입", "진입");
            getMap().clear();
            mClusterManager.clearItems();
            mClusterManager=null;
            startMaps();
        }


    }

    @Override
    protected void startMaps() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.450600, 126.657249), 9.5f));
        mClusterManager = new ClusterManager<Image>(this, getMap());
        mClusterManager.setRenderer(new PersonRenderer());
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        getMap().animateCamera(CameraUpdateFactory.zoomTo(17));
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);
        addItems();
        mClusterManager.cluster();
    }

    void addItems(){
        for(Image image : SplashActivity.selected_images) {
            mClusterManager.addItem(image);
        }
    }





    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();




        SplashActivity.selected_images.clear();
        if (id == R.id.all) {
            current_category=-1;
            for(Image image : SplashActivity.images) {
                    SplashActivity.selected_images.add(image);
            }
        } else if (id == R.id.food) {
            current_category=1;
            for(Image image : SplashActivity.images) {
                if(image.writer.equals("한단비") && image.category==1)
                    SplashActivity.selected_images.add(image);
            }

        } else if (id == R.id.trip) {
            current_category=2;
            for(Image image : SplashActivity.images) {
                if(image.category==2)
                    SplashActivity.selected_images.add(image);
            }
            current_category=-1;
        } else if (id == R.id.daily) {
            current_category=3;
            for(Image image : SplashActivity.images) {
                if(image.category==3)
                    SplashActivity.selected_images.add(image);
            }
        }

        if(mClusterManager!=null){
            Log.e("진입", "진입");
            getMap().clear();
            mClusterManager.clearItems();
            mClusterManager=null;
            startMaps();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    //---------------------------

    public String getDataColumn(Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    @TargetApi(19)
    private String getForApi19(Uri uri) {
        if (DocumentsContract.isDocumentUri(this, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private String getPath(final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKitKat) {
            // MediaStore (and general)
            return getForApi19(uri);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
    //----------------------------


    public static Bitmap cropCenterBitmap(Bitmap src, int w, int h) {
        if(src == null)
            return null;
        int width = src.getWidth();
        int height = src.getHeight();
        if(width < w && height < h)
            return src;
        int x = 0;
        int y = 0;
        if(width > w)
            x = (width - w)/2;
        if(height > h)
            y = (height - h)/2;
        int cw = w; // crop width
        int ch = h; // crop height
        if(w > width)
            cw = width;
        if(h > height)
            ch = height;
        return Bitmap.createBitmap(src, x, y, cw, ch);
    }

}
