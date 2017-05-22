package stepic.stepic;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CaptureJobService extends JobService {

    JobParameters params;
    NewPictureDetectTask doIt;
    @Override
    public boolean onStartJob(JobParameters params) {
        this.params = params;
        doIt = new NewPictureDetectTask();
        doIt.execute();
        Log.d("TestService", "Work to be called from here");
        return false;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("TestService", "System calling to stop the job here");
        if (doIt != null)
            doIt.cancel(true);
        return false;
    }
    private class NewPictureDetectTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("TestService", "Clean up the task here and call jobFinished...");
            jobFinished(params, false);
            super.onPostExecute(aVoid);
        }
        @Override
        protected Void doInBackground(Void... params) {
//            Log.d("TestService", "Working here...");

            publishProgress();

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Toast.makeText(CaptureJobService.this, "Stepic : 새로운 사진 감지", Toast.LENGTH_SHORT).show();

        }
    }
}


