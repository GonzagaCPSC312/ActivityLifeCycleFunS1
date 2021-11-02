package com.sprint.gina.activitylifecyclefuns1;

/*
 * This code is adapted from source code written by Griffiths and Griffiths
 * https://dogriffiths.github.io/HeadFirstAndroid/#/
 */

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // count the number of seconds that have passed
    private int seconds = 0;
    // accumulate seconds?
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start the timer. we only update seconds if running is true though
        runTimer();
    }

    // startButton click handler
    public void onClickStart(View view) {
        running = true;
    }

    // stopButton click handler
    public void onClickStop(View view) {
        running = false;
    }

    // resetButton click handler
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    // method to start the timer using a Handler
    // read more about Handlers here: https://developer.android.com/reference/android/os/Handler.html
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.timeTextView);
        final Handler handler = new Handler();
        // the Runnable interface contains a single method, run()
        // post means run this code immediately
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // format the seconds in H:mm:ss format
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);

                // update the textview
                timeView.setText(time);
                // update seconds if we are in a running state
                if (running) {
                    seconds++;
                }
                // postDelayed means run this code after a delay of 1000 milliseconds
                // 1 second = 1000 milliseconds
                handler.postDelayed(this, 1000);
                // this method will keep getting called while this Activity is running
            }
        });

    }
}