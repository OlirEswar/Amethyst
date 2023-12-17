package edu.vassar.cmpu203.pomodorogame.view;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.pomodorogame.R;
import edu.vassar.cmpu203.pomodorogame.databinding.FragmentWorkPeriodBinding;
import edu.vassar.cmpu203.pomodorogame.model.todo.ToDoList;

/**
 * The fragment that displays the WorkPeriod, with the corresponding timer.
 */
public class WorkPeriodFragment extends Fragment implements IWorkPeriodView {

    private FragmentWorkPeriodBinding binding;
    private Listener listener;

    /** Minutes set by the user. */
    private int minutes;
    /** The current amount of time remaining, in milliseconds. */
    private long currentMillis;
    /** If the counter is currently paused. */
    private boolean isPaused;
    /** The timer that handles the WorkPeriod. */
    private CountDownTimer timer;

    private ToDoList toDoList;

    /** Required empty public constructor.
     * @deprecated */
    public WorkPeriodFragment() {}

    /** Default constructor. */
    public WorkPeriodFragment(Listener listener, int minutes, ToDoList toDoList) {
        this.listener = listener;
        this.minutes = minutes;
        this.isPaused = false;
        this.currentMillis = 0;
        this.toDoList = toDoList;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentWorkPeriodBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        long millis = (minutes * 60L) * 1000;
        createTimer(millis);
        timer.start();
        this.binding.pauseButton.setOnClickListener(view1 -> {
            if (!isPaused) {
                timer.cancel();
                ((Button) view1).setText(R.string.unpause_button);
            } else {
                createTimer(currentMillis);
                timer.start();
                ((Button) view1).setText(R.string.pause_button);
            }
            isPaused = !isPaused;
        });

        this.binding.endButtonWork.setOnClickListener(button -> {
            timer.cancel();
            listener.endSession();
        });

        /* Add item to toDoList model, then dynamically create text fields from to do items in list*/
        this.binding.submitToDoButton.setOnClickListener(button -> {
            String input = this.binding.editToDo.getText().toString();

            if (input.equals("")) {
                Snackbar sb = Snackbar.make(button, R.string.error_no_to_do, Snackbar.LENGTH_LONG);
                sb.show();
            } else
                listener.addToDoItem(input, this);

            this.renderToDos();
        });
    }

    /**
     * Creates a new timer with the specified time value.
     * @param millis - the time remaining in milliseconds
     */
    private void createTimer(long millis) {
        timer = new CountDownTimer(millis, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                currentMillis = millisUntilFinished;
                int currentSecs = (int) (millisUntilFinished / 1000);
                int currentMins = currentSecs / 60;
                currentSecs %= 60;

                binding.timerText.setText(String.format("%d:%02d", currentMins, currentSecs));
            }

            @Override
            public void onFinish() {
                listener.endWorkPeriod();
            }
        };
    }

    @Override
    public void update(ToDoList toDoList) {
        this.toDoList = toDoList;
        this.renderToDos();
    }

    /**
     * Places all the to-dos in the row for to-dos.
     * To-dos are tagged in the following manner:
     * <p>
     * Finish buttons - Finish + index
     * <p>
     * Delete buttons - Delete + index
     * <p>
     * Text fields - just index as int
     * BUG: does not render on start, only on update. not sure why
     */
    private void renderToDos() {
        // layout params for elements
        final LinearLayout.LayoutParams horizontalLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textLayoutParams.weight = 1f;

        final Typeface lato = ResourcesCompat.getFont(this.getContext(), R.font.lato);

        // get and clear the container
        final LinearLayout toDoRow = this.binding.toDoListLinearLayout;
        toDoRow.removeAllViews();

        for (int i = 0; i < toDoList.size(); i++) {
            // instantiate container
            LinearLayout container = new LinearLayout(this.getContext());
            container.setOrientation(LinearLayout.HORIZONTAL);
            container.setLayoutParams(horizontalLayoutParams);

            // text view + formatting
            TextView textView = new TextView(this.getContext());
            textView.setText(toDoList.get(i));
            textView.setLayoutParams(textLayoutParams);
            textView.setTypeface(lato);
            textView.setTextSize(18f);
            textView.setTag(i);

            // finish button + formatting and listener
            Button finishButton = new Button(this.getContext());
            finishButton.setLayoutParams(buttonLayoutParams);
            finishButton.setText(R.string.finish_item);
            finishButton.setTypeface(lato, Typeface.BOLD);
            finishButton.setBackgroundTintList(
                    ContextCompat.getColorStateList(this.getContext(), R.color.green)
            );
            finishButton.setTextColor(ContextCompat.getColor(this.getContext(), R.color.black));
            finishButton.setTag("Finish" + i);
            int finalI = i;
            finishButton.setOnClickListener(
                    deleteButtonRow -> listener.finishToDoItem(finalI, this)
            );

            // delete button + formatting and listener
            Button deleteButton = new Button(this.getContext());
            deleteButton.setLayoutParams(buttonLayoutParams);
            deleteButton.setText(R.string.delete_item);
            deleteButton.setTypeface(lato, Typeface.BOLD);
            deleteButton.setBackgroundTintList(
                    ContextCompat.getColorStateList(this.getContext(), R.color.red)
            );
            deleteButton.setTextColor(ContextCompat.getColor(this.getContext(), R.color.black));
            deleteButton.setTag("Delete" + i);
            deleteButton.setOnClickListener(
                    deleteButtonRow -> listener.deleteToDoItem(finalI, this)
            );

            // add children to main container, then append to the scroll view container
            container.addView(deleteButton);
            container.addView(textView);
            container.addView(finishButton);
            toDoRow.addView(container);
        }
    }
}