package com.star.workout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkoutDetailFragment extends Fragment {

    public static final String WORKOUT_ID = "workoutId";

    private long workoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);

        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong(WORKOUT_ID);
        } else {
            View stopwatchContainer = view.findViewById(R.id.stopwatch_container);

            if (stopwatchContainer != null) {
                StopwatchFragment stopwatchFragment = new StopwatchFragment();

                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.stopwatch_container, stopwatchFragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();
            }
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        if (view != null) {
            Workout workout = Workout.workouts[((int) workoutId)];

            TextView title = (TextView) view.findViewById(R.id.text_title);
            title.setText(workout.getName());

            TextView description = (TextView) view.findViewById(R.id.text_description);
            description.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(WORKOUT_ID, workoutId);
        super.onSaveInstanceState(outState);
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }
}
