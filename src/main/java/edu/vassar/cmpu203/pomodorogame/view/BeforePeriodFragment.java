package edu.vassar.cmpu203.pomodorogame.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vassar.cmpu203.pomodorogame.R;
import edu.vassar.cmpu203.pomodorogame.databinding.FragmentBeforePeriodBinding;

/**
 * The fragment that displays before the Break and Work periods.
 */
public class BeforePeriodFragment extends Fragment implements IBeforePeriodView {

    FragmentBeforePeriodBinding binding;
    Listener listener;
    /** Describes if this fragment is before the break period, or before the work period. */
    boolean isBeforeBreak;

    /** Required empty public constructor.
     * @deprecated */
    public BeforePeriodFragment() {}

    /** Default constructor. */
    public BeforePeriodFragment(Listener listener, boolean isBeforeBreak) {
        this.listener = listener;
        this.isBeforeBreak = isBeforeBreak;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentBeforePeriodBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        View.OnClickListener l = isBeforeBreak ?
                view1 -> BeforePeriodFragment.this.listener.startBreakPeriod() :
                view1 -> BeforePeriodFragment.this.listener.startWorkPeriod();
        this.binding.bpButton.setOnClickListener(l);

        int titleTextId = isBeforeBreak ?
                R.string.work_finished_title :
                R.string.break_finished_title;
        this.binding.bpTitle.setText(titleTextId);

        int textId = isBeforeBreak ?
                R.string.work_finished_text :
                R.string.break_finished_text;
        this.binding.bpText.setText(textId);
    }

}