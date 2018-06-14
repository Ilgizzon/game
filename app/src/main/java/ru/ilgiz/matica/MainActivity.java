package ru.ilgiz.matica;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.utils.Log;

import org.greenrobot.eventbus.Subscribe;

import ru.ilgiz.matica.events.MessageEvent;

import ru.ilgiz.matica.fragments.DificultFragment;
import ru.ilgiz.matica.fragments.LearnFragment;
import ru.ilgiz.matica.fragments.ScreenOneFragment;
import ru.ilgiz.matica.fragments.ScreenResultFragment;
import ru.ilgiz.matica.fragments.ScreenTwoFragment;
import ru.ilgiz.matica.fragments.StatisticFragment;
import ru.ilgiz.matica.helpers.GoogleAnalyticHelper;
import ru.ilgiz.matica.services.FlurryService;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity:";

    private GoogleAnalyticHelper googleAnalytic;
    private DificultFragment fragmentDificult;
    private ScreenOneFragment fragmentOne;
    private ScreenTwoFragment fragmentTwo;
    private ScreenResultFragment fragmentResult;
    private StatisticFragment fragmentStatistic;
    private LearnFragment fragmentLearn;

    private static final int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppConfig.getInstance();
        AppConfig.context = getApplicationContext();
        AppConfig.load();

        googleAnalytic = new GoogleAnalyticHelper(AppConfig.context);
        googleAnalytic.sendCategoryAction(TAG, "onCreate");

  //      Appodeal.setTesting(true);
        Appodeal.setLogLevel(Log.LogLevel.debug);

        String appKey = "";
        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(this, appKey, Appodeal.INTERSTITIAL);
        Appodeal.disableNetwork(this, "cheetah");
        setContentView(R.layout.activity_main);

        AppConfig.getEventBus().register(this);
        fragmentLearn = new LearnFragment();
        fragmentDificult = new DificultFragment();
        fragmentOne = new ScreenOneFragment();
        fragmentTwo = new ScreenTwoFragment();
        fragmentResult = new ScreenResultFragment();
        fragmentStatistic = new StatisticFragment();

        if (AppConfig.firstStart) {
            AppConfig.firstStart = false;
            AppConfig.save();
            getFragmentManager().beginTransaction().add(R.id.fragment_id, fragmentLearn).commit();
        } else {
            getFragmentManager().beginTransaction().add(R.id.fragment_id, fragmentDificult).commit();
        }

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, PERMISSION_ALL);
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Subscribe
    public void onEvent(MessageEvent event) {
        switch (event.getMessage()){
            case AppConfig.MES_RESTART:
                AppConfig.statSuccessCount = 0;
                AppConfig.nextNumbers();
                getFragmentManager().beginTransaction().replace(R.id.fragment_id, fragmentDificult).commit();
                break;
            case AppConfig.MES_SCREEN_DIFICULT:
                getFragmentManager().beginTransaction().replace(R.id.fragment_id, fragmentOne).commit();
                break;
            case AppConfig.MES_SCREEN_ONE:
                getFragmentManager().beginTransaction().replace(R.id.fragment_id, fragmentTwo).commit();
                break;
            case AppConfig.MES_SCREEN_TWO:
                getFragmentManager().beginTransaction().replace(R.id.fragment_id, fragmentResult).commit();
                break;
            case AppConfig.MES_SCREEN_STAT:
                getFragmentManager().beginTransaction().replace(R.id.fragment_id, fragmentStatistic).commit();
                break;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        startService(new Intent(this, FlurryService.class));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, FlurryService.class));
        super.onDestroy();
    }

}
