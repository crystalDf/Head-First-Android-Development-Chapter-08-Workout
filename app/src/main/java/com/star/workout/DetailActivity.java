package com.star.workout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment)
                getFragmentManager().findFragmentById(R.id.detail_frag);

        int workoutId = (int) getIntent().getExtras().getLong(EXTRA_WORKOUT_ID);

        workoutDetailFragment.setWorkoutId(workoutId);
    }

}