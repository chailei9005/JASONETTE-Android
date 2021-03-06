package com.jasonette.seed.Action;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;
import com.jasonette.seed.Helper.JasonHelper;
import com.jasonette.seed.Core.JasonViewActivity;
import org.json.JSONObject;

public class JasonUtilAction {
    public void banner(final JSONObject action, final JSONObject data, final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject options = action.getJSONObject("options");
                    Snackbar snackbar = Snackbar.make(((JasonViewActivity)context).rootLayout, options.getString("title") + "\n" + options.getString("description"), Snackbar.LENGTH_LONG);
                    snackbar.show();
                } catch (Exception e){
                    Log.d("Error", e.toString());
                }
            }
        });
        try {
            JasonHelper.next("success", action, new JSONObject(), context);
        } catch (Exception e) {
            Log.d("Error", e.toString());
        }
    }
    public void toast(final JSONObject action, final JSONObject data, final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject options = action.getJSONObject("options");
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, (CharSequence)options.getString("text"), duration);
                    toast.show();
                } catch (Exception e){
                    Log.d("Error", e.toString());
                }
            }
        });
        try {
            JasonHelper.next("success", action, new JSONObject(), context);
        } catch (Exception err) {
            Log.d("Error", err.toString());
        }
    }
    public void alert(final JSONObject action, final JSONObject data, final Context context){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                try {
                    JSONObject options = new JSONObject();
                    if (action.has("options")) {
                        options = action.getJSONObject("options");
                        String title = options.getString("title");
                        String description = options.getString("description");
                        builder.setTitle(title);
                        builder.setMessage(description);
                    }
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    try {
                                        if (action.has("success")) {
                                            JasonHelper.next("success", action, new JSONObject(), context);
                                        }
                                    } catch (Exception err) {

                                    }
                                }
                            });
                    builder.setNeutralButton("CANCEL",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    //
                                }
                            });
                    builder.show();
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                }
            }
        });
    }
}
