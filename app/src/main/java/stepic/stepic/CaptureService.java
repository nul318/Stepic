package stepic.stepic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class CaptureService extends Service {

    // 서비스를 생성할 때 호출
    public void onCreate() {
        super.onCreate();
        Log.i("CaptureService", "CaptureService 시작");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


}


