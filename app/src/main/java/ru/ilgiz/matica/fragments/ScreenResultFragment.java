package ru.ilgiz.matica.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import ru.ilgiz.matica.AppConfig;
import ru.ilgiz.matica.R;
import ru.ilgiz.matica.events.MessageEvent;
import ru.ilgiz.matica.helpers.GoogleAnalyticHelper;

public class ScreenResultFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ScreenResultFragment:";
    private TextView tvResultNumber;
    private ImageButton ibYes;
    private ImageButton ibNo;

    private GoogleAnalyticHelper googleAnalytic;
    private boolean bCatch = false;


    public ScreenResultFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_result, container, false);

        googleAnalytic = new GoogleAnalyticHelper(AppConfig.context);
        googleAnalytic.sendCategoryAction(TAG, "onCreate");

        tvResultNumber = (TextView)view.findViewById(R.id.tvResultNumber);
        tvResultNumber.setText(AppConfig.getResultNumber());

        ibYes = (ImageButton)view.findViewById(R.id.ibYes);
        ibYes.setOnClickListener(this);

        ibNo = (ImageButton)view.findViewById(R.id.ibNo);
        ibNo.setOnClickListener(this);

        bCatch = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!bCatch) {
                    AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_SCREEN_STAT));
                }
            }
        }, AppConfig.getAnswerDelay());

        return view;
    }

    private void nextLevel() {
        if (AppConfig.statSuccessCount >= 50) {
            AppConfig.statSuccessCount = 0;
            if (AppConfig.getLevel()== AppConfig.LEVEL1) {
                AppConfig.setLevel(AppConfig.LEVEL2);
                AppConfig.save();
                return;
            }
            if (AppConfig.getLevel()== AppConfig.LEVEL2) {
                AppConfig.setLevel(AppConfig.LEVEL3);
                AppConfig.save();
                return;
            }
            if (AppConfig.getLevel()== AppConfig.LEVEL3) {
                AppConfig.setLevel(AppConfig.LEVEL4);
                AppConfig.save();
                return;
            }
            if (AppConfig.getLevel()== AppConfig.LEVEL4) {
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibYes:
                if (AppConfig.getSuccess()) {
                    bCatch = true;
                    AppConfig.statSuccessCount++;
                    nextLevel();
                    AppConfig.nextNumbers();
                    AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_SCREEN_DIFICULT));

                } else {
                    bCatch = true;
                    AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_SCREEN_STAT));
                }
                break;
            case R.id.ibNo:
                if (AppConfig.getSuccess()) {
                    bCatch = true;
                    AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_SCREEN_STAT));
                } else {
                    bCatch = true;
                    AppConfig.statSuccessCount++;
                    nextLevel();
                    AppConfig.nextNumbers();
                    AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_SCREEN_DIFICULT));
                }
                break;
        }
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
