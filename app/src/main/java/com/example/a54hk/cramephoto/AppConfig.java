package com.example.a54hk.cramephoto;

import android.net.Uri;

/**
 * Created by eric on 2017/9/11.
 */

public class AppConfig {

    public static final int PHOTO_REQUEST_TAKE_PHOTO = 1;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 3;// 结果
    public static Uri mUri;//图片临时信息

    public static int RESULT_SEARCH_MAP = 101;
    public static int RESULT_LOGIN = 102;
    public static int RESULT_CITY = 103;
    public static int RESULT_USER_INFO = 104;
    public static int RESULT_PAY = 105;
    public static int RESULT_OUT_LOGIN = 106;

    public static final String BAIDU_STATIC_MAP_KEY = "4Wpj4VX330RpssqCdOGugWiajPRVNMZj";
    public static String WEI_CHAT_APP_ID = "874186731035824128";

    public static String GY = "gy";
    public static String IS_LOGIN = "isLogin";
    public static String CHANNEL = "UMENG_CHANNEL";
    public static String calendarDelayDays = "calendarDelayDays";
    public static String calendarSpaceDays = "calendarDelayDays";
    public static String IS_FIRST = "isFirst";
//    public static String AGENT = "ZBJ/" + AppUtils.getVersionName() + " Android/" + DeviceUtils.getBuildVersion();
    public static String URL = "url";
    public static String TITLE = "title";
    public static String PIC_LIST = "picList";
    public static String IS_TITLE_TRANSPARENT = "isTitleTransparent";
    public static String RECOMMEND = "recommend";
    public static String ACCESS_TOKEN = "accessToken";
    public static String LAT = "lat";
    public static String LON = "lon";
    public static String CITY_NAME = "cityName";
    public static String CODE = "code";
    public static String USER_INFO = "userInfo";


    public static String HOUSE_PRICE = "¥%s/月起";

    public static String USER_ID = "userId";
    public static String ORDER_INFO = "orderInfo";
    public static String LAST_CITY_NAME="lastCityName";
    public static String ID="id";
}
