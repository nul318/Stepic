package stepic.stepic;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;

public class SplashActivity extends Activity {
    Handler handler;

    static ArrayList<Image> images = new ArrayList<>();
    static ArrayList<Image> selected_images = new ArrayList<>();



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //누가일 경우 CAMERA BROADCAST RECIEVER 적용안댐
//        scheduleJob(this);


                handler = new Handler();

//        images.add(new Image(null, new LatLng(37.451600, 126.657949), "한단비", Calendar.getInstance(), 0, R.drawable.a, "고양이 1"));
//        images.add(new Image(null, new LatLng(37.450320, 126.657859), "한단비", Calendar.getInstance(), 0, R.drawable.b, "고양이 2"));
//        images.add(new Image(null, new LatLng(37.450280, 126.657119), "한단비", Calendar.getInstance(), 0, R.drawable.c, "고양이 3"));
//        images.add(new Image(null, new LatLng(37.450899, 126.657399), "한단비", Calendar.getInstance(), 0, R.drawable.d, "고양이 4"));
//        images.add(new Image(null, new LatLng(37.450198, 126.657641), "한단비", Calendar.getInstance(), 0, R.drawable.e, "고양이 5"));


        images.add(new Image(null,new LatLng(37.449499, 126.661851), "한단비", Calendar.getInstance(), 2,R.drawable.image_0010,"maz"));
        images.add(new Image(null,new LatLng(37.450649, 126.657388), "한단비", Calendar.getInstance(), 2,R.drawable.image_0020,"bottle"));

        images.add(new Image(null,new LatLng(37.451440, 126.656179), "장지은", Calendar.getInstance(), 1,R.drawable.image_0030,"peony_1"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "한단비", Calendar.getInstance(), 1,R.drawable.image_0031,"peony_2"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "장지은", Calendar.getInstance(), 1,R.drawable.image_0032,"peony_3"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "장지은", Calendar.getInstance(), 1,R.drawable.image_0033,"peony_4"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "한단비", Calendar.getInstance(), 1,R.drawable.image_0034,"peony_5"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "한단비", Calendar.getInstance(), 2,R.drawable.image_0035,"peony_6"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "한단비", Calendar.getInstance(), 2,R.drawable.image_0036,"peony_7"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "한단비", Calendar.getInstance(), 2,R.drawable.image_0037,"peony_8"));
        images.add(new Image(null,new LatLng(37.451440, 126.656179), "한단비", Calendar.getInstance(), 2,R.drawable.image_0038,"peony_9"));

        images.add(new Image(null,new LatLng(37.450358, 126.655718), "한단비", Calendar.getInstance(), 3,R.drawable.image_0040,"study_1"));
        images.add(new Image(null,new LatLng(37.450358, 126.655718), "한단비", Calendar.getInstance(), 3,R.drawable.image_0041,"study_2"));
        images.add(new Image(null,new LatLng(37.450358, 126.655718), "장지은", Calendar.getInstance(), 3,R.drawable.image_0042,"study_3"));
        images.add(new Image(null,new LatLng(37.450358, 126.655718), "장지은", Calendar.getInstance(), 3,R.drawable.image_0043,"study_4"));
        images.add(new Image(null,new LatLng(37.450358, 126.655718), "장지은", Calendar.getInstance(), 1,R.drawable.image_0044,"study_5"));
        images.add(new Image(null,new LatLng(37.450358, 126.655718), "한단비", Calendar.getInstance(), 1,R.drawable.image_0045,"study_6"));
        images.add(new Image(null,new LatLng(37.450358, 126.655718), "한단비", Calendar.getInstance(), 1,R.drawable.image_0046,"study_7"));
        images.add(new Image(null,new LatLng(37.450358, 126.655718), "장지은", Calendar.getInstance(), 1,R.drawable.image_0047,"study_8"));
        images.add(new Image(null,new LatLng(37.451392, 126.657259), "장지은", Calendar.getInstance(), 1,R.drawable.image_0050,"iceflake"));
        images.add(new Image(null,new LatLng(37.450554, 126.659377), "장지은", Calendar.getInstance(), 1,R.drawable.image_0060,"coffehole"));
        images.add(new Image(null,new LatLng(37.450554, 126.659377), "한단비", Calendar.getInstance(), 1,R.drawable.image_0070,"greentea"));

        images.add(new Image(null,new LatLng(35.858741, 128.561778), "장지은", Calendar.getInstance(), 2,R.drawable.image_0080,"daegu_1"));
        images.add(new Image(null,new LatLng(35.858741, 128.561778), "장지은", Calendar.getInstance(), 2,R.drawable.image_0090,"daegu_2"));
        images.add(new Image(null,new LatLng(35.858741, 128.561778), "한단비", Calendar.getInstance(), 2,R.drawable.image_0100,"daegu_3"));

        for(Image image : images)
            selected_images.add(image);

////
////        images.add(new Image(null,new LatLng(37.452089, 126.656079), "10", Calendar.getInstance(), 0,R.drawable.zzu,"zzu"));
////        images.add(new Image(null,new LatLng(37.451872, 126.657394), "11_1", Calendar.getInstance(), 0,R.drawable.noodle_1,"noodle_1"));
////        images.add(new Image(null,new LatLng(37.451872, 126.657394), "11_2", Calendar.getInstance(), 0,R.drawable.noodle_2,"noodle_2"));
////        images.add(new Image(null,new LatLng(37.452444, 126.668578), "12", Calendar.getInstance(), 0,R.drawable.bowling,"bowling"));
////        images.add(new Image(null,new LatLng(37.463813, 126.680893), "13", Calendar.getInstance(), 0,R.drawable.cheese,"cheese"));
////        images.add(new Image(null,new LatLng(37.463813, 126.680893), "14", Calendar.getInstance(), 0,R.drawable.ooh,"ooh"));
////        images.add(new Image(null,new LatLng(37.460860, 126.680715), "15", Calendar.getInstance(), 0,R.drawable.blood,"blood"));
////
////        images.add(new Image(null,new LatLng(37.452104, 126.660507), "16", Calendar.getInstance(), 0,R.drawable.bibimbap,"bibimbap"));
////        images.add(new Image(null,new LatLng(37.452104, 126.660507), "27", Calendar.getInstance(), 0,R.drawable.hanu,"hanu"));
////
////        images.add(new Image(null,new LatLng(37.451551, 126.657891), "18_1", Calendar.getInstance(), 0,R.drawable.sundaeguk_1,"sundaeguk_1"));
////        images.add(new Image(null,new LatLng(37.451551, 126.657891), "18_2", Calendar.getInstance(), 0,R.drawable.sundaeguk_2,"sundaeguk_2"));
////
////        images.add(new Image(null,new LatLng(37.451524, 126.657253), "19", Calendar.getInstance(), 0,R.drawable.iceflake_sul,"iceflake_sul"));
////        images.add(new Image(null,new LatLng(37.451524, 126.657253), "30", Calendar.getInstance(), 0,R.drawable.iceflakestra,"iceflakestra"));
////
////        images.add(new Image(null,new LatLng(37.451696, 126.656820), "20", Calendar.getInstance(), 0,R.drawable.demn,"demn"));
////        images.add(new Image(null,new LatLng(37.451838, 126.656414), "21_1", Calendar.getInstance(), 0,R.drawable.chicken_1,"chicken_1"));
////        images.add(new Image(null,new LatLng(37.451838, 126.656414), "21_2", Calendar.getInstance(), 0,R.drawable.chicken_2,"chicken_2"));
////
////        images.add(new Image(null,new LatLng(37.451101, 126.657425), "22", Calendar.getInstance(), 0,R.drawable.bokki,"bokki"));
////        images.add(new Image(null,new LatLng(37.452447, 126.656698), "23", Calendar.getInstance(), 0,R.drawable.hamba,"hamba"));
////
////        images.add(new Image(null,new LatLng(37.513201, 127.103017), "24_1", Calendar.getInstance(), 0,R.drawable.lotte_1,"lotte_1"));
////        images.add(new Image(null,new LatLng(37.513201, 127.103017), "24_2", Calendar.getInstance(), 0,R.drawable.lotte_2,"lotte_2"));
////        images.add(new Image(null,new LatLng(37.513201, 127.103017), "24_3", Calendar.getInstance(), 0,R.drawable.lotte_3,"lotte_3"));
////        images.add(new Image(null,new LatLng(37.513201, 127.103017), "24_4", Calendar.getInstance(), 0,R.drawable.lotte_4,"lotte_4"));
////
////        images.add(new Image(null,new LatLng(37.513276, 127.101789), "25", Calendar.getInstance(), 0,R.drawable.lottemall_1,"lottemall_1"));
////        images.add(new Image(null,new LatLng(37.513276, 127.101789), "25_1", Calendar.getInstance(), 0,R.drawable.lottemall_2,"lottemall_2"));
////
////        images.add(new Image(null,new LatLng(37.450869, 126.660035), "26", Calendar.getInstance(), 0,R.drawable.atom,"atom"));
////        images.add(new Image(null,new LatLng(37.451530, 126.657253), "28", Calendar.getInstance(), 0,R.drawable.baskin,"baskin"));
////        images.add(new Image(null,new LatLng(37.451595, 126.656460), "29", Calendar.getInstance(), 0,R.drawable.catseyes,"catseyes"));
////
////        images.add(new Image(null,new LatLng(37.499647, 126.774082), "31", Calendar.getInstance(), 0,R.drawable.flower,"flower"));
////        images.add(new Image(null,new LatLng(37.499647, 126.774082), "34", Calendar.getInstance(), 0,R.drawable.selfie,"selfie"));
////        images.add(new Image(null,new LatLng(37.499647, 126.774082), "44", Calendar.getInstance(), 0,R.drawable.dukujone,"dukujone"));
////
////        images.add(new Image(null,new LatLng(37.452011, 126.656241), "32", Calendar.getInstance(), 0,R.drawable.jjimdark,"jjimdark"));
////
////        images.add(new Image(null,new LatLng(37.513131, 127.057934), "33_1", Calendar.getInstance(), 0,R.drawable.calligraphy_1,"calligraphy_1"));
////        images.add(new Image(null,new LatLng(37.513131, 127.057934), "33_2", Calendar.getInstance(), 0,R.drawable.calligraphy_2,"calligraphy_2"));
////
////        images.add(new Image(null,new LatLng(37.447138, 126.650954), "35", Calendar.getInstance(), 0,R.drawable.buy,"buy"));
////        images.add(new Image(null,new LatLng(37.735142, 127.425387), "36", Calendar.getInstance(), 0,R.drawable.fun,"fun"));
////        images.add(new Image(null,new LatLng(37.450937, 126.658151), "37", Calendar.getInstance(), 0,R.drawable.nooodles,"nooodles"));
////        images.add(new Image(null,new LatLng(37.450937, 126.658151), "64", Calendar.getInstance(), 0,R.drawable.coolnoodles,"coolnoodles"));
////
////        images.add(new Image(null,new LatLng(37.451806, 126.656997), "38", Calendar.getInstance(), 0,R.drawable.cosmetic,"cosmetic"));
////        images.add(new Image(null,new LatLng(37.449402, 126.652506), "39", Calendar.getInstance(), 0,R.drawable.sky,"sky"));
////        images.add(new Image(null,new LatLng(37.452467, 126.656245), "40", Calendar.getInstance(), 0,R.drawable.yummy,"yummy"));
////
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "41_1", Calendar.getInstance(), 0,R.drawable.park_1,"park_1"));
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "41_2", Calendar.getInstance(), 0,R.drawable.park_2,"park_2"));
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "45_1", Calendar.getInstance(), 0,R.drawable.kyo_1,"kyo_1"));
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "45_2", Calendar.getInstance(), 0,R.drawable.kyo_2,"kyo_2"));
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "45_3", Calendar.getInstance(), 0,R.drawable.kyo_3,"kyo_3"));
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "45_4", Calendar.getInstance(), 0,R.drawable.kyo_4,"kyo_4"));
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "56_1", Calendar.getInstance(), 0,R.drawable.parkyo_1,"parkyo_1"));
////        images.add(new Image(null,new LatLng(37.516133, 127.076036), "56_2", Calendar.getInstance(), 0,R.drawable.parkyo_2,"parkyo_2"));
////
////        images.add(new Image(null,new LatLng(37.452456, 126.656833), "43", Calendar.getInstance(), 0,R.drawable.pasta,"pasta"));
////        images.add(new Image(null,new LatLng(37.452456, 126.656833), "55", Calendar.getInstance(), 0,R.drawable.ppasta,"ppasta"));
////
////        images.add(new Image(null,new LatLng(33.214288, 126.251810), "46", Calendar.getInstance(), 0,R.drawable.jeju_1,"jeju_1"));
////        images.add(new Image(null,new LatLng(33.394886, 126.240814), "47", Calendar.getInstance(), 0,R.drawable.jeju_2,"jeju_2"));
////        images.add(new Image(null,new LatLng(33.347809, 126.326420), "48", Calendar.getInstance(), 0,R.drawable.jeju_3,"jeju_3"));
////        images.add(new Image(null,new LatLng(33.436537, 126.627971), "49", Calendar.getInstance(), 0,R.drawable.jeju_4,"jeju_4"));
////
////        images.add(new Image(null,new LatLng(37.449741, 126.701593), "50", Calendar.getInstance(), 0,R.drawable.outback,"outback"));
////        images.add(new Image(null,new LatLng(37.451387, 126.657942), "51", Calendar.getInstance(), 0,R.drawable.shell,"shell"));
////        images.add(new Image(null,new LatLng(37.451574, 126.658073), "52", Calendar.getInstance(), 0,R.drawable.theku_1,"theku_1"));
////        images.add(new Image(null,new LatLng(37.451574, 126.658073), "53", Calendar.getInstance(), 0,R.drawable.theku_2,"theku_2"));
////        images.add(new Image(null,new LatLng(37.540863, 127.002327), "54", Calendar.getInstance(), 0,R.drawable.bluesquare,"bluesquare"));
////
////        images.add(new Image(null,new LatLng(37.451862, 126.656332), "57", Calendar.getInstance(), 0,R.drawable.ssamso,"ssamso"));
////        images.add(new Image(null,new LatLng(37.399475, 126.740434), "58", Calendar.getInstance(), 0,R.drawable.sore,"sore"));
////        images.add(new Image(null,new LatLng(37.244697, 126.749882), "59_1", Calendar.getInstance(), 0,R.drawable.home_1,"home_1"));
////        images.add(new Image(null,new LatLng(37.244697, 126.749882), "59_2", Calendar.getInstance(), 0,R.drawable.home_2,"home_2"));
////        images.add(new Image(null,new LatLng(37.451647, 126.657586), "60", Calendar.getInstance(), 0,R.drawable.sushi,"sushi"));
////
////        images.add(new Image(null,new LatLng(37.449921, 126.655749), "61", Calendar.getInstance(), 0,R.drawable.selca,"selca"));
////        images.add(new Image(null,new LatLng(37.449921, 126.655749), "65", Calendar.getInstance(), 0,R.drawable.zzz,"zzz"));
////
////        images.add(new Image(null,new LatLng(37.452057, 126.656684), "62", Calendar.getInstance(), 0,R.drawable.fufun,"fufun"));
////        images.add(new Image(null,new LatLng(37.450926, 126.658529), "63", Calendar.getInstance(), 0,R.drawable.meeting,"meeting"));
////
////        images.add(new Image(null,new LatLng(37.451847, 126.657401), "66", Calendar.getInstance(), 0,R.drawable.meeeeting,"meeeeting"));
////        images.add(new Image(null,new LatLng(37.449944, 126.653136), "67", Calendar.getInstance(), 0,R.drawable.bsky,"bsky"));
////        images.add(new Image(null,new LatLng(37.449944, 126.653136), "68", Calendar.getInstance(), 0,R.drawable.bsk_y,"bsk_y"));
////        images.add(new Image(null,new LatLng(37.565076, 126.987258), "69", Calendar.getInstance(), 0,R.drawable.beers,"beers"));
////        images.add(new Image(null,new LatLng(37.451123, 126.657334), "70", Calendar.getInstance(), 0,R.drawable.ejrqhrdl,"ejrqhrdl"));
////        images.add(new Image(null,new LatLng(37.451301, 126.658326), "71", Calendar.getInstance(), 0,R.drawable.rawfish_1,"rawfish_1"));
////        images.add(new Image(null,new LatLng(37.451301, 126.658326), "71_2", Calendar.getInstance(), 0,R.drawable.rawfish_2,"rawfish_2"));
//
//


        final DBManager dbManager = new DBManager(getApplicationContext(), "member.db", null, 1);

//		final SharedPreferences setting;
//		final SharedPreferences.Editor editor;
//		setting = getSharedPreferences("setting", 0);
//		editor= setting.edit();



        final Button resister = (Button) findViewById(R.id.join);
        final Button login_bt = (Button) findViewById(R.id.login); // 일반

        View.OnClickListener listener2 = new View.OnClickListener() { //회원가입
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(SplashActivity.this, JoinActivity.class));

//
//                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                LinearLayout addLayout = (LinearLayout) vi.inflate(R.layout.dialog_resister, null);
//                final EditText id = (EditText) addLayout.findViewById(R.id.alert_id);
//                final EditText pw = (EditText) addLayout.findViewById(R.id.alert_pw);
////         final EditText contents = (EditText) addLayout.findViewById(R.id.alert_contents);
//                new AlertDialog.Builder(SplashActivity.this).setTitle("정보를 입력하세요.").setView(addLayout)
//                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // TODO Auto-generated method stub
//                                String _id = id.getText().toString();
//                                String _pw = pw.getText().toString();
//                                if (!_id.equals("")) {
//                                    dbManager.insert("insert into member values(null, '" + _id + "', '" + _pw + "');");
////                           editor.putString("ID", _id);
////                           editor.putString("PW", _pw);
////                           editor.commit();
//                                } else {
//                                    Toast toast = Toast.makeText(getApplicationContext(), "id를 입력해주세요.", Toast.LENGTH_SHORT);
//                                    toast.show();
//                                }
//                            }
//                        }).show();
            }


        };


//        View.OnClickListener listener2 = new View.OnClickListener() { //회원가입
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                LinearLayout addLayout = (LinearLayout) vi.inflate(R.layout.resister_alert, null);
//                final EditText id = (EditText) addLayout.findViewById(R.id.alert_id);
//                final EditText pw = (EditText) addLayout.findViewById(R.id.alert_pw);
//			final EditText contents = (EditText) addLayout.findViewById(R.id.alert_contents);
//                new AlertDialog.Builder(SplashActivity.this).setTitle("정보를 입력하세요.").setView(addLayout)
//                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                // TODO Auto-generated method stub
//                String _id = id.getText().toString();
//                String _pw = pw.getText().toString();
//                if (!_id.equals("")) {
//                    dbManager.insert("insert into member values(null, '" + _id + "', '" + _pw + "');");
////									editor.putString("ID", _id);
////									editor.putString("PW", _pw);
////									editor.commit();
//                } else {
//                    Toast toast = Toast.makeText(getApplicationContext(), "id를 입력해주세요.", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//            }
//        };


        View.OnClickListener listener3 = new View.OnClickListener() { //로그인
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SplashActivity.this, MapsActivity.class));
                finish();

                // TODO Auto-generated method stub
//                final String _id = et1.getText().toString();
//                final String _pw = et2.getText().toString();
//                if (!_id.equals("")) {
//                    if (!dbManager.PrintData(_id, _pw).equals("null")) {
//                        Toast.makeText(getApplicationContext(), "로그인성공", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
//                        intent.putExtra("id", _id);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "로그인실패", Toast.LENGTH_SHORT).show();
//                     }
//
//
//                } else {
//                    Toast toast = Toast.makeText(getApplicationContext(), "id를 입력해주세요.", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
            }
        };


        resister.setOnClickListener(listener2);
        login_bt.setOnClickListener(listener3);


        final AnimationSet animSet = new AnimationSet(true);
        final AnimationSet animSet2 = new AnimationSet(true);
        Animation logo_anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.logo);
        Animation component_anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade);

        animSet.addAnimation(logo_anim);
        animSet2.addAnimation(component_anim);
        final Handler handler = new Handler();

    }

    public static final int MY_BACKGROUND_JOB = 0;

//    @TargetApi(Build.VERSION_CODES.N)
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public static void scheduleJob(Context context) {
//        JobScheduler js =
//                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        JobInfo.Builder builder = new JobInfo.Builder(MY_BACKGROUND_JOB,
//                new ComponentName(context, CaptureJobService.class));
//        builder.addTriggerContentUri(
//                new JobInfo.TriggerContentUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS));
//        js.schedule(builder.build());
//    }

}