package com.star.workout;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchFragment extends Fragment {

    public static final String SECONDS = "seconds";
    public static final String RUNNING = "running";
    public static final String WAS_RUNNING = "wasRunning";

    private int mSeconds = 0;
    private boolean mRunning = false;
    private boolean mWasRunning = false;

    private Button mStartButton;
    private Button mStopButton;
    private Button mResetButton;

    private TextView mTimeTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mSeconds = savedInstanceState.getInt(SECONDS);
            mRunning = savedInstanceState.getBoolean(RUNNING);
            mWasRunning = savedInstanceState.getBoolean(WAS_RUNNING);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        mStartButton = (Button) view.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRunning = true;
            }
        });

        mStopButton = (Button) view.findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRunning = false;
            }
        });

        mResetButton = (Button) view.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRunning = false;
                mSeconds = 0;
            }
        });

        mTimeTextView = (TextView) view.findViewById(R.id.time_text_view);

        runTimer();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mWasRunning) {
            mRunning = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mWasRunning = mRunning;
        mRunning = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SECONDS, mSeconds);
        outState.putBoolean(RUNNING, mRunning);
        outState.putBoolean(WAS_RUNNING, mWasRunning);
    }

    public void runTimer() {
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = mSeconds / 3600;
                int minutes = mSeconds % 3600 / 60;
                int seconds = mSeconds % 60;

                String time = String.format("%d:%02d:%02d", hours, minutes, seconds);

                mTimeTextView.setText(time);

                if (mRunning) {
                    mSeconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

}
