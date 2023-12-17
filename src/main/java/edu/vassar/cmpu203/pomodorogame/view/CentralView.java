package edu.vassar.cmpu203.pomodorogame.view;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.vassar.cmpu203.pomodorogame.databinding.ActivityMainBinding;

/**
 * The main view, which is used for handling the swapping of various fragments.
 */
public class CentralView implements ICentralView {

    FragmentManager fm;
    ActivityMainBinding binding;

    /**
     * Default constructor.
     * @param activity the activity the view is associated with
     */
    public CentralView(FragmentActivity activity) {
        this.fm = activity.getSupportFragmentManager();
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
    }

    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    @Override
    public void displayFragment(Fragment fragment, boolean reversible, String name) {
        FragmentTransaction ft = fm.beginTransaction()
                .replace(this.binding.fragmentContainerView.getId(), fragment);

        if (reversible) ft.addToBackStack(name);

        ft.commit();
    }
}
