package io.xmacedo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601 {

    private static final SimpleDateFormat ISO_8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

    static {
        ISO_8601.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static String fromDate(Date date) {
        return ISO_8601.format(date);
    }

    private ISO8601() {

    }

}
