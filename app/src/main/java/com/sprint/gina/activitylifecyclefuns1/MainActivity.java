package com.sprint.gina.activitylifecyclefuns1;

/*
 * This code is adapted from source code written by Griffiths and Griffiths
 * https://dogriffiths.github.io/HeadFirstAndroid/#/
 */

import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivityTag";
    
    // count the number of seconds that have passed
    private int seconds = 0;
    // accumulate seconds?
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we can extract our seconds and running values from the Bundle
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        // BE AWARE OF THE ACTIVITY LIFECYCLE METHODS
        // these are methods that are called as your app moves
        // through various life cycle states
        // see the notes on google drive for a table of the states/methods
        // and their descriptions
        // example: onCreate() is a lifecycle method
        Log.d(TAG, "onCreate: ");

        // on some device configuration changes, the app is destroyed and recreated
        // example: on orientation change (e.g. portrait -> landscape)
        // define a layout-land resource directory for landscape layouts
        // example: on locale change (e.g. USA -> France)
        // define a values-fr resource directory for French strings and the like
        // https://developer.android.com/guide/topics/resources/runtime-changes
        
        // start the timer. we only update seconds if running is true though
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save your view state and instance state here
        // android will save *some* view state for you
        // text in a textview
        // any view state for views without ids

        // lets save our seconds and running values in the Bundle outState
        // we can restore our seconds and running values in onCreate()
        // like intents, bundles store key value pairs
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
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