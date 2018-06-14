package ru.ilgiz.matica.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Locale;

import ru.ilgiz.matica.AppConfig;
import ru.ilgiz.matica.R;
import ru.ilgiz.matica.events.MessageEvent;

public class LearnFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LearnFragment:";

    private ImageView imageView;

    private Integer index = 0;

    public LearnFragment() {
    }

    private boolean isEnglish() {
        String lang = Locale.getDefault().getDisplayLanguage();
        return lang.endsWith("English");
    }

    private void setImageResource(int en_resource, int ru_resource) {
        if (isEnglish()) {
            imageView.setImageResource(en_resource);
        } else {
            imageView.setImageResource(ru_resource);
        }
    }

    @Override
    public void onClick(View v) {
        index++;

        switch (index) {
            case 1:
                setImageResource(R.mipmap.en_learn_2, R.mipmap.ru_learn_2);
                break;
            case 2:
                setImageResource(R.mipmap.en_learn_3, R.mipmap.ru_learn_3);
                break;
            case 3:
                setImageResource(R.mipmap.en_learn_4, R.mipmap.ru_learn_4);
                break;
            case 4:
                setImageResource(R.mipmap.en_learn_5, R.mipmap.ru_learn_5);
                break;
            case 5:
                setImageResource(R.mipmap.en_learn_6, R.mipmap.ru_learn_6);
                break;
            case 6:
                setImageResource(R.mipmap.en_learn_7, R.mipmap.ru_learn_7);
                break;
            case 7:
                setImageResource(R.mipmap.en_learn_8, R.mipmap.ru_learn_8);
                break;
            case 8:
                setImageResource(R.mipmap.en_learn_9, R.mipmap.ru_learn_9);
                break;
            case 9:
                AppConfig.getEventBus().post(new MessageEvent(AppConfig.MES_RESTART));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        imageView = (ImageView)view.findViewById(R.id.imageView);
        setImageResource(R.mipmap.en_learn_1, R.mipmap.ru_learn_1);
        index = 0;
        imageView.setOnClickListener(this);

        return view;
    }
}
