package stepic.stepic;


import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.Calendar;


public class Image implements ClusterItem{
    public Uri url;    //이미지 주소
    public int url_memory;    //이미지 주소
    //    public double lat = 0.0;		// 위도
//    public double lng = 0.0;		// 경도
    public LatLng location;
    public String writer = ""; // 작성자
    public Calendar reg_date;    // 날짜, 시간정보
    public int category = 0;    // 카테고리 0:여행, 1:엔터테이먼트, 2:쇼핑, 3:일상, 4:먹방
    String name;

    public Image(Uri url, LatLng location, String writer, Calendar date, int category, int url_memory, String name) {

        this.url = url;
        this.location = location;
        this.url_memory = url_memory;
//        this.location = location;
        this.writer = writer;
        this.reg_date = date;
        this.category = category;
        this.name = name;
    }

    @Override
    public LatLng getPosition() {
        return location;
    }


}

