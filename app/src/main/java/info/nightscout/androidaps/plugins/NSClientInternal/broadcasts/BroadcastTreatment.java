package info.nightscout.androidaps.plugins.NSClientInternal.broadcasts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import info.nightscout.androidaps.MainApp;
import info.nightscout.androidaps.R;
import info.nightscout.androidaps.Services.Intents;
import info.nightscout.androidaps.plugins.NSClientInternal.data.NSTreatment;
import info.nightscout.utils.SP;
import info.nightscout.utils.ToastUtils;

/**
 * Created by mike on 20.02.2016.
 */
public class BroadcastTreatment {
    private static Logger log = LoggerFactory.getLogger(BroadcastTreatment.class);

    public static void handleNewTreatment(NSTreatment treatment, Context context, boolean isDelta) {

        Bundle bundle = new Bundle();
        bundle.putString("treatment", treatment.getData().toString());
        bundle.putBoolean("delta", isDelta);
        Intent intent = new Intent(Intents.ACTION_NEW_TREATMENT);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        LocalBroadcastManager.getInstance(MainApp.instance()).sendBroadcast(intent);

        if(SP.getBoolean(R.string.key_nsclient_localbroadcasts, true)) {
            bundle = new Bundle();
            bundle.putString("treatment", treatment.getData().toString());
            bundle.putBoolean("delta", isDelta);
            intent = new Intent(Intents.ACTION_NEW_TREATMENT);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.sendBroadcast(intent);
        }
    }

    public static void handleNewTreatment(JSONArray treatments, Context context, boolean isDelta) {

        List<JSONArray> splitted = splitArray(treatments);
        for (JSONArray part: splitted) {
            Bundle bundle = new Bundle();
            bundle.putString("treatments", part.toString());
            bundle.putBoolean("delta", isDelta);
            Intent intent = new Intent(Intents.ACTION_NEW_TREATMENT);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            LocalBroadcastManager.getInstance(MainApp.instance()).sendBroadcast(intent);
        }

        if(SP.getBoolean(R.string.key_nsclient_localbroadcasts, true)){
            splitted = splitArray(treatments);
            for (JSONArray part: splitted) {
                Bundle bundle = new Bundle();
                bundle.putString("treatments", part.toString());
                bundle.putBoolean("delta", isDelta);
                Intent intent = new Intent(Intents.ACTION_NEW_TREATMENT);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                context.sendBroadcast(intent);
            }
        }
    }

    public void handleChangedTreatment(JSONObject treatment, Context context, boolean isDelta) {

        Bundle bundle = new Bundle();
        bundle.putString("treatment", treatment.toString());
        bundle.putBoolean("delta", isDelta);
        Intent intent = new Intent(Intents.ACTION_CHANGED_TREATMENT);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        LocalBroadcastManager.getInstance(MainApp.instance()).sendBroadcast(intent);


        if(SP.getBoolean(R.string.key_nsclient_localbroadcasts, true)) {
            bundle = new Bundle();
            bundle.putString("treatment", treatment.toString());
            bundle.putBoolean("delta", isDelta);
            intent = new Intent(Intents.ACTION_CHANGED_TREATMENT);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.sendBroadcast(intent);
        }
    }

    public static void handleChangedTreatment(JSONArray treatments, Context context, boolean isDelta) {

        List<JSONArray> splitted = splitArray(treatments);
        for (JSONArray part : splitted) {
            Bundle bundle = new Bundle();
            bundle.putString("treatments", part.toString());
            bundle.putBoolean("delta", isDelta);
            Intent intent = new Intent(Intents.ACTION_CHANGED_TREATMENT);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            LocalBroadcastManager.getInstance(MainApp.instance()).sendBroadcast(intent);
        }

        if(SP.getBoolean(R.string.key_nsclient_localbroadcasts, true)) {
            splitted = splitArray(treatments);
            for (JSONArray part : splitted) {
                Bundle bundle = new Bundle();
                bundle.putString("treatments", part.toString());
                bundle.putBoolean("delta", isDelta);
                Intent intent = new Intent(Intents.ACTION_CHANGED_TREATMENT);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                context.sendBroadcast(intent);
            }
        }
    }

    public static void handleRemovedTreatment(JSONObject treatment, Context context, boolean isDelta) {

        Bundle bundle = new Bundle();
        bundle.putString("treatment", treatment.toString());
        bundle.putBoolean("delta", isDelta);
        Intent intent = new Intent(Intents.ACTION_REMOVED_TREATMENT);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        LocalBroadcastManager.getInstance(MainApp.instance()).sendBroadcast(intent);


        if(SP.getBoolean(R.string.key_nsclient_localbroadcasts, true)) {
            bundle = new Bundle();
            bundle.putString("treatment", treatment.toString());
            bundle.putBoolean("delta", isDelta);
            intent = new Intent(Intents.ACTION_REMOVED_TREATMENT);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.sendBroadcast(intent);
        }
    }

    public static void handleRemovedTreatment(JSONArray treatments, Context context, boolean isDelta) {

        Bundle bundle = new Bundle();
        bundle.putString("treatments", treatments.toString());
        bundle.putBoolean("delta", isDelta);
        Intent intent = new Intent(Intents.ACTION_REMOVED_TREATMENT);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        LocalBroadcastManager.getInstance(MainApp.instance()).sendBroadcast(intent);


        if(SP.getBoolean(R.string.key_nsclient_localbroadcasts, true)) {
            bundle = new Bundle();
            bundle.putString("treatments", treatments.toString());
            bundle.putBoolean("delta", isDelta);
            intent = new Intent(Intents.ACTION_REMOVED_TREATMENT);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.sendBroadcast(intent);
        }
    }


    public static List<JSONArray> splitArray(JSONArray array) {
        List<JSONArray> ret = new ArrayList<>();
        try {
            int size = array.length();
            int count = 0;
            JSONArray newarr = null;
            for (int i = 0; i < size; i++) {
                if (count == 0) {
                    if (newarr != null) {
                        ret.add(newarr);
                    }
                    newarr = new JSONArray();
                    count = 200;
                }
                newarr.put(array.get(i));
                --count;
            }
            if (newarr != null && newarr.length() > 0) {
                ret.add(newarr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ret = new ArrayList<>();
            ret.add(array);
        }
        return ret;
    }
}
