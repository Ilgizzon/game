package ru.ilgiz.matica.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;

import ru.ilgiz.matica.AppConfig;
import ru.ilgiz.matica.R;
import ru.ilgiz.matica.events.MessageEvent;

public class StatisticFragment extends Fragment implements View.OnClickListener{

    private TextView tvCount;
    private Button btnRestart;
    private ImageView ivShare;
    private TextView textViewMsg;
    public StatisticFragment() {
        super();
    }


    private Context cntx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);
        tvCount = (TextView) v.findViewById(R.id.tvCount);
        tvCount.setText(AppConfig.getSuccessCount());
        textViewMsg = (TextView) v.findViewById(R.id.textViewMsg);
        btnRestart = (Button) v.findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(this);
        setMsg(AppConfig.getSuccessIntCount());

        final Activity activity = getActivity();
        ivShare = (ImageView) v.findViewById(R.id.ivShare);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.format(getString(R.string.share_title), AppConfig.getSuccessCount());
                AppConfig.sharingInActivity(activity, text);
            }
        });
        return v;
    }

    private void setMsg(int count){
        if(count<=10){
            textViewMsg.setText(R.string.value_1_10);
        }else if(count>10 && count <=20){
            textViewMsg.setText(R.string.value_11_20);
        }else if(count>20 && count <=24){
            textViewMsg.setText(R.string.value_21_24);
        }else if(count==25){
            textViewMsg.setText(R.string.value_25);
        }else if(count>25 && count <=35){
            textViewMsg.setText(R.string.value_26_35);
        }else if(count>35 && count <=45){
            textViewMsg.setText(R.string.value_36_45);
        }else if(count>45 && count <=49){
            textViewMsg.setText(R.string.value_46_49);
        }else if(count==50){
            textViewMsg.setText(R.string.value_50);
        }else if(count>50 && count <=60){
            textViewMsg.setText(R.string.value_51_60);
        }else if(count>60 && count <=70){
            textViewMsg.setText(R.string.value_61_70);
        }else if(count>70 && count <=74){
            textViewMsg.setText(R.string.value_71_74);
        }else if(count==75){
            textViewMsg.setText(R.string.value_51_60);
        }else if(count>75 && count <=85){
            textViewMsg.setText(R.string.value_76_85);
        }else if(count>85 && count <=95){
            textViewMsg.setText(R.string.value_86_95);
        }else if(count>95 && count <=99){
            textViewMsg.setText(R.string.value_96_99);
        }else if(count>99){
            textViewMsg.setText(R.string.value_100);
        }
    }

    @Override
    public void onClick(View v) {

        Appodeal.show(getActivity(), Appodeal.INTERSTITIAL);
        AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_RESTART));
    }
}
