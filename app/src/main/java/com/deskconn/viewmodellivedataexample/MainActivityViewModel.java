package com.deskconn.viewmodellivedataexample;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private CountDownTimer timer;

    MutableLiveData<Long> timerValue = new MutableLiveData<>();
    MutableLiveData<Boolean> finished = new MutableLiveData<>();
    private MutableLiveData<Integer> seconds = new MutableLiveData<>();

    LiveData<Integer> seconds() {
        return seconds;
    }

    void startTimer() {
        timer = new CountDownTimer(timerValue.getValue(), 1000) {
            @Override
            public void onTick(long l) {
                String value = String.valueOf((l / 1000));
                seconds.setValue(Integer.valueOf(value));
            }

            @Override
            public void onFinish() {
                finished.setValue(true);
            }
        }.start();
    }

    void stopTimer() {
        timer.cancel();
    }
}
