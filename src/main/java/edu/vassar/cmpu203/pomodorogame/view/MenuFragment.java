package edu.vassar.cmpu203.pomodorogame.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.pomodorogame.databinding.FragmentMenuBinding;

/**
 * The Main menu screen of the app.
 */
public class MenuFragment extends Fragment implements IMenuView {

    FragmentMenuBinding binding;
    Listener listener;

    /** Required empty constructor.
     * @deprecated */
    public MenuFragment() {}

    public MenuFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentMenuBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.binding.startButton.setOnClickListener(
                v -> listener.onSelectStart()
        );
        this.binding.settingsButton.setOnClickListener(
                v -> listener.onSelectSettings()
        );
    }
}