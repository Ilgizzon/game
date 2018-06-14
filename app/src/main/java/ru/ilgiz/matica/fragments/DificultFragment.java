package ru.ilgiz.matica.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.ilgiz.matica.AppConfig;
import ru.ilgiz.matica.R;
import ru.ilgiz.matica.events.MessageEvent;

public class DificultFragment extends Fragment implements View.OnClickListener {

    private Button btn8;
    private Button btn11;
    private Button btn14;
    private Button btn18;

    public DificultFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dificult, container, false);

        btn8 = (Button) v.findViewById(R.id.btn8);
        btn11 = (Button) v.findViewById(R.id.btn11);
        btn14 = (Button) v.findViewById(R.id.btn14);
        btn18 = (Button) v.findViewById(R.id.btn18);

        btn8.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn18.setOnClickListener(this);

        switch (AppConfig.getLevel()) {
            case AppConfig.LEVEL1:
                AppConfig.setScreenDelay(AppConfig.SCREEN_DELAY_LEVEL1);
                AppConfig.setAnswerDelay(AppConfig.ANSWER_DELAY_LEVEL1);
                btn8.setEnabled(true);
                btn11.setEnabled(false);
                btn14.setEnabled(false);
                btn18.setEnabled(false);
                btn8.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn11.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorDisable));
                btn14.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorDisable));
                btn18.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorDisable));
                break;
            case AppConfig.LEVEL2:
                AppConfig.setScreenDelay(AppConfig.SCREEN_DELAY_LEVEL2);
                AppConfig.setAnswerDelay(AppConfig.ANSWER_DELAY_LEVEL2);
                btn8.setEnabled(true);
                btn11.setEnabled(true);
                btn14.setEnabled(false);
                btn18.setEnabled(false);
                btn8.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn11.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn14.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorDisable));
                btn18.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorDisable));
                break;
            case AppConfig.LEVEL3:
                AppConfig.setScreenDelay(AppConfig.SCREEN_DELAY_LEVEL3);
                AppConfig.setAnswerDelay(AppConfig.ANSWER_DELAY_LEVEL3);
                btn8.setEnabled(true);
                btn11.setEnabled(true);
                btn14.setEnabled(true);
                btn18.setEnabled(false);
                btn8.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn11.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn14.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn18.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorDisable));
                break;
            case AppConfig.LEVEL4:
                AppConfig.setScreenDelay(AppConfig.SCREEN_DELAY_LEVEL4);
                AppConfig.setAnswerDelay(AppConfig.ANSWER_DELAY_LEVEL4);
                btn8.setEnabled(true);
                btn11.setEnabled(true);
                btn14.setEnabled(true);
                btn18.setEnabled(true);
                btn8.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn11.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn14.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                btn18.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
                break;
        }


        return v;
    }

    @Override
    public void onClick(View v) {
        AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_SCREEN_DIFICULT));
    }
}
