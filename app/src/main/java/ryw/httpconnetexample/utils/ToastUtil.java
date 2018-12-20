package ryw.httpconnetexample.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int id) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }
}
