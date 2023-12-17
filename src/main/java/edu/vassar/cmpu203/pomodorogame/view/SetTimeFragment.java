package edu.vassar.cmpu203.pomodorogame.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.pomodorogame.R;
import edu.vassar.cmpu203.pomodorogame.databinding.FragmentSetTimeBinding;

/**
 * The fragment that handles the setting of the pomodoro work and break
 * period times.
 */
public class SetTimeFragment extends Fragment implements ISetTimeView {

    private FragmentSetTimeBinding binding;
    private Listener listener;

    /** Required empty public constructor.
     * @deprecated */
    public SetTimeFragment() {}

    public SetTimeFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentSetTimeBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // add listener to button
        this.binding.confirmButton.setOnClickListener(button -> {
            String workString = SetTimeFragment.this.binding.workPeriodInput.getText().toString();
            String breakString = SetTimeFragment.this.binding.breakPeriodInput.getText().toString();

            if (workString.equals("")
                    || breakString.equals("")
                    || workString.equals("0")
                    || breakString.equals("0")) {
                Snackbar sb = Snackbar.make(button, R.string.err_zero_detected, Snackbar.LENGTH_LONG);
                sb.show();
                return;
            }

            if (breakString.startsWith("1337") && breakString.length() >= 5) {
                int workTime = Integer.parseInt(workString);
                int breakTime = Integer.parseInt(breakString.substring(4));
                String sbString = getString(R.string.debug_jump);

                Snackbar sb = Snackbar.make(
                        button,
                        sbString + " " + breakTime + " minutes.",
                        Snackbar.LENGTH_SHORT
                );
                sb.show();
                listener.debugJumpToBreak(workTime, breakTime);
                return;
            }

            int workTime = Integer.parseInt(workString);
            int breakTime = Integer.parseInt(breakString);
            listener.onSubmitSettings(workTime, breakTime);
        });
    }
}