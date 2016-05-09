package com.hs.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by work_tl on 2016/4/24.
 */
public class NumberSequenceUtil {
    public static final String CUSTOM_TITLE = "CU";

    public static final String RECORD_TITLE = "RE";

    public static final String ATTCH_TITLE = "AT";

    public static Random random = new Random();

    public static String getCustomNum() {
        return new StringBuilder(CUSTOM_TITLE).append(getTimeString()).append(getRadomNum()).toString();
    }

    public static String getRecordNum() {
        return new StringBuilder(RECORD_TITLE).append(getTimeString()).append(getRadomNum()).toString();
    }

    public static String getAttchNum() {
        return new StringBuilder(ATTCH_TITLE).append(getTimeString()).append(getRadomNum()).toString();
    }

    public static String getDateString() {
        SimpleDateFormat dt = new SimpleDateFormat("YYYYMMdd");
        return dt.format(new Date());
    }

    public static String getTimeString() {
        SimpleDateFormat dt = new SimpleDateFormat("YYYYMMddHHmmss");
        return dt.format(new Date());
    }

    public static String getRadomNum() {
        Random randomN = new Random(random.nextInt());
        int num = randomN.nextInt(1000);
        return StringUtil.right("000" + String.valueOf(num), 3);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {

            System.out.println(getCustomNum());
        }
    }
}
