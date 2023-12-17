package edu.vassar.cmpu203.pomodorogame.view;

import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * Interface that defines the implementation of the <code>CentralView</code>
 */
public interface ICentralView {
    /**
     * Gets the root view of the current application state.
     * @return the screen's root view
     */
    View getRootView();

    /**
     * Displays various fragments passed in by the controller.
     *
     * @param fragment the fragment to be displayed
     * @param reversible true if you can go back to the last fragment, false otherwise
     * @param name the name of the fragment transaction
     */
    void displayFragment(Fragment fragment, boolean reversible, String name);
}
