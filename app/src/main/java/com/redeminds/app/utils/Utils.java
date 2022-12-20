package com.redeminds.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.redeminds.app.DashboardActivity;

public class Utils {

    public static void showBackPressDialog(Activity activity){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setMessage("Switch To Dashboard?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.startActivity(new Intent(activity, DashboardActivity.class));
            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogBuilder.show();
    }

    public static void showToast(Context context, String message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void DEBUG(String sb) {
        if (sb.length() > 4000) {
            int chunkCount = sb.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    Log.d("Redeminds >> ", "Redeminds >> " + sb.substring(4000 * i));
                } else {
                    Log.d("Redeminds >> ", "Redeminds >> " + sb.substring(4000 * i, max));
                }
            }
        } else {
            Log.d("Redeminds >> ", "Redeminds >> " + sb.toString());
        }
    }

    public static void ERROR(String message) {
        Log.e("Redeminds >> ", "Redeminds >> " + message);
    }

}
