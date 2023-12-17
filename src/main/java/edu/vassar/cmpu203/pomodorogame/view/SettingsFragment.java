package edu.vassar.cmpu203.pomodorogame.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.pomodorogame.databinding.FragmentSettingsBinding;
import edu.vassar.cmpu203.pomodorogame.model.stats.Stats;

/**
 * The settings fragment for displaying the cumulative settings of all pomodoro sessions.
 */
public class SettingsFragment extends Fragment implements ISettingsView {
    FragmentSettingsBinding binding;
    Listener listener;

    Stats stats;

    /** Required empty constructor.
     * @deprecated */
    public SettingsFragment() {}

    public SettingsFragment(Listener listener, Stats stats) {
        this.listener = listener;
        this.stats = stats;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentSettingsBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.binding.menuButton.setOnClickListener(
                v -> listener.returnToMenu()
        );
        this.binding.wipeGameButton.setOnClickListener(
                v -> listener.resetGameData(this)
        );
        this.binding.wipeStatsButton.setOnClickListener(
                v -> listener.resetStatsData(this)
        );

        this.renderStats(stats);
    }

    /**
     * Renders the stats into the ScrollView.
     * @param stats the stats object to be rendered
     */
    @Override
    public void renderStats(Stats stats) {
        this.stats = stats;
        String completedToDos = "Completed To-dos: " + stats.completedToDos;
        this.binding.completeToDoText.setText(completedToDos);

        String removedToDos = "Removed To-dos: " + stats.deletedToDos;
        this.binding.deleteToDoText.setText(removedToDos);

        String remainingToDos = "Remaining To-dos: " + stats.remainingToDos;
        this.binding.remainToDoText.setText(remainingToDos);

        String completedBPs = "Completed break periods: " + stats.completedBreakPeriods;
        this.binding.completeBPText.setText(completedBPs);

        String completedWPs = "Completed work periods: " + stats.completedWorkPeriods;
        this.binding.completeWPText.setText(completedWPs);

        String timeBPs = "Time spent in break period: " + stats.totalBreakTime + " minutes";
        this.binding.timeBPText.setText(timeBPs);

        String workBPs = "Time spent in break period: " + stats.totalWorkTime + " minutes";
        this.binding.timeWPText.setText(workBPs);
    }
}