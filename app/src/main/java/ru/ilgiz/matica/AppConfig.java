package ru.ilgiz.matica;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class AppConfig extends MultiDexApplication {
    public static AppConfig instance;
    public static Context context;

    private static EventBus eventBus;

    private static String firstNumber;
    private static String secondNumber;
    private static String resultNumber;

    private static int iFirstNumber = 0;
    private static int iSecondNumber = 0;

    private static int MIN = 1;
    private static int MAX = 9;

    public static final int SCREEN_DELAY_LEVEL1 = 300;
    public static final int ANSWER_DELAY_LEVEL1 = 750;
    public static final int SCREEN_DELAY_LEVEL2 = 225;
    public static final int ANSWER_DELAY_LEVEL2 = 675;
    public static final int SCREEN_DELAY_LEVEL3 = 150;
    public static final int ANSWER_DELAY_LEVEL3 = 600;
    public static final int SCREEN_DELAY_LEVEL4 = 120;
    public static final int ANSWER_DELAY_LEVEL4 = 525;

    private static int SCREEN_DELAY = SCREEN_DELAY_LEVEL1;
    private static int ANSWER_DELAY = ANSWER_DELAY_LEVEL1;

    public static int statSuccessCount = 0;
    public static final int LEVEL1 = 1;
    public static final int LEVEL2 = 2;
    public static final int LEVEL3 = 3;
    public static final int LEVEL4 = 4;

    private static int level = LEVEL1;
    public static boolean firstStart = true;

    public static final int MES_RESTART = 0;
    public static final int MES_SCREEN_DIFICULT = 1;
    public static final int MES_SCREEN_ONE = 2;
    public static final int MES_SCREEN_TWO = 3;
    public static final int MES_SCREEN_STAT = 4;

    private static boolean success = false;

    private static SharedPreferences sharedPreference = null;

    private static final String PREF_FILE = "MAIN";
    private static final String PREF_LEVEL = "LEVEL";
    private static final String PREF_SUCCESS_INDEX = "SUCCESS_INDEX";
    private static final String PREF_ONCE = "ONCE";

    static {
        instance = null;
        eventBus = null;
    }


    public AppConfig() {
        nextNumbers();
    }

    public static String getSuccessCount() {
        return String.valueOf(statSuccessCount);
    }

    public static int getSuccessIntCount() {
        return statSuccessCount;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(AppConfig.this);
    }


    public static void hideKeyboard(final View v, int delay) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (context != null) {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }, delay);
    }

    public static void save() {
        sharedPreference = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(PREF_LEVEL, level);
        editor.putInt(PREF_SUCCESS_INDEX, statSuccessCount);
        editor.putBoolean(PREF_ONCE, firstStart);
        editor.commit();
    }

    public static void load() {
        sharedPreference = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        level = sharedPreference.getInt(PREF_LEVEL, LEVEL1);
        statSuccessCount = sharedPreference.getInt(PREF_SUCCESS_INDEX, 0);
        firstStart = sharedPreference.getBoolean(PREF_ONCE, true);
    }


    public static void nextNumbers() {
        iFirstNumber = getRandNumber();
        firstNumber = String.valueOf(iFirstNumber);

        iSecondNumber = getRandNumber();
        secondNumber = String.valueOf(iSecondNumber);

        if ((SystemClock.elapsedRealtime() % 2) == 0) {
            resultNumber = String.valueOf(iFirstNumber * iSecondNumber);
            success = true;
        } else {
            MAX = 99;
            resultNumber = getRandString();
            MAX = 9;
            success = false;
        }
    }

    public static EventBus getEventBus() {
        if (eventBus == null) {
            synchronized (AppConfig.class) {
                if (eventBus == null) {
                    eventBus = new EventBus();
                }
            }
        }
        return eventBus;
    }

    public static void sharingInActivity(Activity activity, String text) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, activity.getString(R.string.share_title));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        activity.startActivity(Intent.createChooser(sharingIntent, activity.getString(R.string.share_choose_app)));
    }

    public static int getRandNumber() {
        return new Random().nextInt(MAX - MIN + 1) + MIN;
    }

    public static String getRandString() {
        return String.valueOf(getRandNumber());
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    public static String getFirstNumber() {
        return firstNumber;
    }

    public static String getSecondNumber() {
        return  secondNumber;
    }

    public static String getResultNumber() {
        return resultNumber;
    }


    public static int getMinRandom() {
        return MIN;
    }

    public static int getMaxRandom() {
        return MAX;
    }

    public static void setMinRandom(int min) {
        MIN = min;
    }

    public static void setMaxRandom(int max) {
        MAX = max;
    }

    public static int getScreenDelay() {
        return SCREEN_DELAY;
    }

    public static int getAnswerDelay() {
        return ANSWER_DELAY;
    }

    public static void setScreenDelay(int screenDelay) {
        SCREEN_DELAY = screenDelay;
    }

    public static void setAnswerDelay(int answerDelay) { ANSWER_DELAY = answerDelay; }

    public static boolean getSuccess() {
        return success;
    }

    public static int getLevel() { return level; }

    public static void setLevel(int lv) { level = lv; }
}
