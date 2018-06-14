package ru.ilgiz.matica.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.flurry.android.FlurryAgent;

import java.util.Map;

import ru.ilgiz.matica.interfaces.IAnalytics;


public class FlurryService extends Service implements IAnalytics {
    private static final String TAG = "FlurryService:";
    private static final String API_KEY = "";

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        onStart();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        onStop();
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public void onStart() {
      /*  FlurryAgent.setLogEnabled(true);
        FlurryAgent.setLogEvents(true);
        FlurryAgent.setLogLevel(Log.VERBOSE);
        FlurryAgent.init(this, API_KEY);*/
    }

    @Override
    public void onStop() {
        FlurryAgent.onEndSession(this);
    }

    public void logEvent(String eventId, Map<String, String> params) {
        FlurryAgent.logEvent(eventId, params);
    }
}