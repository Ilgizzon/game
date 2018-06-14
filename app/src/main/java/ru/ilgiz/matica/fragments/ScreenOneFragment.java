package ru.ilgiz.matica.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import ru.ilgiz.matica.AppConfig;
import ru.ilgiz.matica.R;
import ru.ilgiz.matica.events.MessageEvent;
import ru.ilgiz.matica.helpers.GoogleAnalyticHelper;

public class ScreenOneFragment extends Fragment {
    private static final String TAG = "ScreenOneFragment:";
    private TextView tvFirstNumber;

    private GoogleAnalyticHelper googleAnalytic;

    public ScreenOneFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_one, container, false);

        googleAnalytic = new GoogleAnalyticHelper(AppConfig.context);
        googleAnalytic.sendCategoryAction(TAG, "onCreate");

        tvFirstNumber = (TextView)view.findViewById(R.id.tvFirstNumber);
        tvFirstNumber.setText(AppConfig.getFirstNumber());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_SCREEN_ONE));
            }
        }, AppConfig.getScreenDelay());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        tvFirstNumber.setText(AppConfig.getFirstNumber());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
