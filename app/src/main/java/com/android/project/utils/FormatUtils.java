package com.android.project.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Finn
 * @date 2020
 */
public class FormatUtils {

    public static int getInt(Object object) {
        try {
            if (object == null) {
                return 0;
            } else if (object instanceof String) {
                return Integer.parseInt((String) object);
            } else if (object instanceof Boolean) {
                if ((boolean) object) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (object instanceof Integer) {
                return (int) object;
            } else if (object instanceof Double) {
                return (int) object;
            } else if (object instanceof Float) {
                return (int) object;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static float getFloat(Object object) {
        try {
            if (object == null) {
                return 0f;
            } else if (object instanceof String) {
                return Float.parseFloat((String) object);
            } else if (object instanceof Boolean) {
                if ((boolean) object) {
                    return 1f;
                } else {
                    return 0f;
                }
            } else if (object instanceof Integer) {
                return (float) object;
            } else if (object instanceof Double) {
                return (float) object;
            } else if (object instanceof Float) {
                return (float) object;
            } else {
                return 0f;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0f;
    }


    public static long getLong(Object object) {
        try {
            if (object == null) {
                return 0L;
            } else if (object instanceof String) {
                return Long.parseLong(((String) object));
            } else if (object instanceof Boolean) {
                if ((boolean) object) {
                    return 1L;
                } else {
                    return 0L;
                }
            } else if (object instanceof Integer) {
                return (long) object;
            } else if (object instanceof Double) {
                return (long) object;
            } else if (object instanceof Float) {
                return (long) object;
            } else {
                return 0L;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static String getOnePointsStr(float value) {
        DecimalFormat numberFormat = new DecimalFormat("0.0");
        return numberFormat.format(value);
    }

    public static String getOnePointsStr(double value) {
        DecimalFormat numberFormat = new DecimalFormat("0.0");
        return numberFormat.format(value);
    }

    public static String getThreePointsStr(float value) {
        DecimalFormat numberFormat = new DecimalFormat("0.000");
        return numberFormat.format(value);
    }

    public static String getPointNoZeroStr(float value) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(value);
    }

}
