package edu.vassar.cmpu203.pomodorogame.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.pomodorogame.databinding.FragmentStatsBinding;
import edu.vassar.cmpu203.pomodorogame.model.stats.Stats;

/**
 * A fragment used to display the total work and break time done after a pomodoro session.
 */
public class StatsFragment extends Fragment implements IStatsView {

    private FragmentStatsBinding binding;

    private Listener listener;

    /** The current cumulative stats object. */
    Stats stats;

    /** Required default constructor.
     * @deprecated */
    public StatsFragment() {}

    public StatsFragment(Listener listener, Stats stats) {
        this.listener = listener;
        this.stats = stats;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentStatsBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String completedWP = "" + stats.completedWorkPeriods;
        String completedBP = "" + stats.completedBreakPeriods;
        String remainingTD = "" + stats.remainingToDos;
        String completedTD = "" + stats.completedToDos;
        String deletedTD = "" + stats.deletedToDos;
        String workTime = stats.totalWorkTime + " minutes";
        String breakTime = stats.totalBreakTime + " minutes";

        this.binding.numberOfWorkPeriods.setText(completedWP);
        this.binding.numberOfBreakPeriods.setText(completedBP);
        this.binding.remainingToDosValue.setText(remainingTD);
        this.binding.completedToDosValue.setText(completedTD);
        this.binding.deletedToDosValue.setText(deletedTD);
        this.binding.totalWorkTimeValue.setText(workTime);
        this.binding.totalBreakTimeValue.setText(breakTime);
        this.binding.finishButton.setOnClickListener(
                button -> this.listener.onFinishedViewing()
        );
    }
}