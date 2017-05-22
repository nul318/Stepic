package stepic.stepic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

public class CaptureReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ComponentName getAlertJobComponent = new ComponentName(context.getPackageName(), CaptureJobService.class.getName());
//            JobInfo.Builder getAlertbuilder = new JobInfo.Builder(SyncStateContract.Constants.getAlertJobid, getAlertJobComponent);
//            getAlertbuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // require unmetered network
//            getAlertbuilder.setRequiresDeviceIdle(true); // device should be idle
//            getAlertbuilder.setPeriodic(10 * 1000);
//            getAlertbuilder.setPersisted(true);
//            getAlertbuilder.setRequiresCharging(false); // we don't care if the device is charging or not
//            JobScheduler getAlertjobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//            getAlertjobScheduler.schedule(getAlertbuilder.build());
//        }
        Log.d("Test", "START OF NewPhotoReceiver");
        Uri uri = intent.getData();
        Toast.makeText(context, "Stepic New Picture : " + uri, Toast.LENGTH_SHORT).show();

        SplashActivity.images.add(new Image(uri, new LatLng(37.450609, 126.657246), "한단비", Calendar.getInstance(), 1, -1, String.valueOf(Calendar.getInstance())));
        Log.d("Test", "[onReceive] URI - " + uri);

    }


}


