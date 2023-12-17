package edu.vassar.cmpu203.pomodorogame.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.pomodorogame.R;
import edu.vassar.cmpu203.pomodorogame.databinding.FragmentBreakPeriodBinding;
import edu.vassar.cmpu203.pomodorogame.model.match3.Gem;
import edu.vassar.cmpu203.pomodorogame.model.match3.GemColor;
import edu.vassar.cmpu203.pomodorogame.model.match3.Match3Game;
import edu.vassar.cmpu203.pomodorogame.model.utils.Coordinate;

/**
 * The fragment that displays the BreakPeriod, along with the BreakPeriod game.
 */
public class BreakPeriodFragment extends Fragment implements IBreakPeriodView {

    FragmentBreakPeriodBinding binding;
    Listener listener;
    /** The current game state of the <code>Match3Game</code> */
    Match3Game game;
    boolean isTutorialVisible = false;

    //TODO: maybe factor this out into model/own thing?
    /** Minutes set by the user. */
    private int minutes;
    /** The current amount of time remaining, in milliseconds. */
    private long currentMillis;
    /** If the counter is currently paused. */
    private boolean isPaused;
    /** The timer that handles the WorkPeriod. */
    private CountDownTimer timer;

    /** Required empty public constructor.
     * @deprecated */
    public BreakPeriodFragment() {}

    /** Default constructor. */
    public BreakPeriodFragment(Listener listener, Match3Game game, int minutes) {
        this.listener = listener;
        this.game = game;
        this.minutes = minutes;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentBreakPeriodBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // generate timer for the break period
        long millis = (minutes * 60L) * 1000;
        createTimer(millis);
        timer.start();

        // pause button logic
        this.binding.breakPause.setOnClickListener(pauseView -> {
            if (!isPaused) {
                timer.cancel();
                ((Button) pauseView).setText(R.string.unpause_button);
            } else {
                createTimer(currentMillis);
                timer.start();
                ((Button) pauseView).setText(R.string.pause_button);
            }
            isPaused = !isPaused;
        });

        // show tutorial button logic
        this.binding.tutorialButton.setOnClickListener(tutorialButton -> {
            if (isTutorialVisible) {
                this.binding.tutorialLayout.setVisibility(View.GONE);
                this.binding.tutorialButton.setText(R.string.show_tutorial);
                isTutorialVisible = false;
            } else {
                this.binding.tutorialLayout.setVisibility(View.VISIBLE);
                this.binding.tutorialButton.setText(R.string.hide_tutorial);
                isTutorialVisible = true;
            }
        });

        this.binding.endButtonBreak.setOnClickListener(button -> {
            timer.cancel();
            listener.endSession();
        });

        Gem[][] board = game.getBoard();

        // gather each row
        TableRow[] rows = {
                this.binding.row0, this.binding.row1, this.binding.row2,
                this.binding.row3, this.binding.row4, this.binding.row5,
                this.binding.row6, this.binding.row7,
        };

        // button layout params
        final TableRow.LayoutParams buttonLayoutParams =
                new TableRow.LayoutParams(128, 128);

        // generate buttons based on board
        for (int y = 0; y < 8; y++) {
            TableRow currentRow = rows[y];
            for (int x = 0; x < 8; x++) {
                ImageButton gemButton = new ImageButton(this.getContext());
                int finalX = x;
                int finalY = y;
                gemButton.setOnClickListener(view1 ->
                        BreakPeriodFragment.this.listener.selectGem(
                                finalX,
                                finalY,
                                BreakPeriodFragment.this
                        )
                );
                gemButton.setTag(new Coordinate(x, y)); // just in case unexpected behavior
                gemButton.setLayoutParams(buttonLayoutParams); // rui: set button's layout parameters
                setButtonImage(gemButton, board[x][y].getColor());

                if ((y % 2 + x % 2) % 2 == 0) // generate chessboard background
                    gemButton.setBackgroundResource(R.color.light_board);
                else
                    gemButton.setBackgroundResource(R.color.dark_board);

                gemButton.setScaleType(ImageView.ScaleType.FIT_START); // force image to take all space up
                gemButton.setAdjustViewBounds(true);
                gemButton.setPadding(0,0,0,0);

                currentRow.addView(gemButton);
            }
        }

        this.drawScoreUI();
    }

    @Override
    public void update(Match3Game game) {
        Log.d("BPF", "board updated");
        this.game = game;
        Gem[][] board = game.getBoard();
        Coordinate selectGemPos = game.getSelectedGem();

        // gather each row
        TableRow[] rows = {
                this.binding.row0, this.binding.row1, this.binding.row2,
                this.binding.row3, this.binding.row4, this.binding.row5,
                this.binding.row6, this.binding.row7,
        };

        // reassign button text
        for (int y = 0; y < 8; y++) {
            TableRow currentRow = rows[y];
            for (int x = 0; x < 8; x++) {
                ImageButton currentButton = (ImageButton) currentRow.getChildAt(x);
                Coordinate c = (Coordinate) currentButton.getTag();
                Gem currentGem = board[c.x][c.y];
                setButtonImage(currentButton, currentGem.getColor());
            }
        }

        // set selected gem's border
        if (selectGemPos.isValid()) {
            ImageButton selectButton = (ImageButton) rows[selectGemPos.y].getChildAt(selectGemPos.x);
            Gem selectGem = board[selectGemPos.x][selectGemPos.y];
            setSelectedButtonImage(selectButton, selectGem.getColor());
        }

        this.drawScoreUI();
    }

    /**
     * Sets the button provided with the corresponding image.
     * @param button the button that will contain the image resource
     * @param color the corresponding color of the gem image
     */
    private static void setButtonImage(ImageButton button, GemColor color) {
        int imageId;
        switch (color) {
            case RED:
                imageId = R.drawable.redgem;
                break;
            case ORANGE:
                imageId = R.drawable.orangegem;
                break;
            case YELLOW:
                imageId = R.drawable.yellowgem;
                break;
            case GREEN:
                imageId = R.drawable.greengem;
                break;
            case BLUE:
                imageId = R.drawable.bluegem;
                break;
            case PURPLE:
                imageId = R.drawable.purplegem;
                break;
            case WHITE:
                imageId = R.drawable.whitegem;
                break;
            default:
                imageId = R.drawable.none;
                break;
        }
        button.setImageResource(imageId);
    }

    /**
     * Sets the button provided with the corresponding image, but selected.
     * @param button the button that will contain the image resource
     * @param color the corresponding color of the gem image
     */
    private static void setSelectedButtonImage(ImageButton button, GemColor color) {
        int imageId;
        switch (color) {
            case RED:
                imageId = R.drawable.redgemselect;
                break;
            case ORANGE:
                imageId = R.drawable.orangegemselect;
                break;
            case YELLOW:
                imageId = R.drawable.yellowgemselect;
                break;
            case GREEN:
                imageId = R.drawable.greengemselect;
                break;
            case BLUE:
                imageId = R.drawable.bluegemselect;
                break;
            case PURPLE:
                imageId = R.drawable.purplegemselect;
                break;
            case WHITE:
                imageId = R.drawable.whitegemselect;
                break;
            default:
                imageId = R.drawable.none;
                break;
        }
        button.setImageResource(imageId);
    }

    /**
     * Handles the drawing of the score, combo, and level.
     */
    private void drawScoreUI() {
        final LinearLayout comboRow = this.binding.comboBlocks;
        comboRow.removeAllViews();

        final LinearLayout.LayoutParams blockLayoutParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
        blockLayoutParams.weight = 1f;

        int combo = game.getComboProgress();
        for (int i = 0; i < game.getComboCap(); i++) {
            int resId;
            if (combo > 0) {
                combo--;
                resId = R.drawable.combo_red;
            } else {
                resId = R.drawable.combo_none;
            }
            ImageView comboBlock = new ImageView(this.getContext());
            comboBlock.setImageResource(resId);
            comboBlock.setLayoutParams(blockLayoutParams);
            comboBlock.setScaleType(ImageView.ScaleType.FIT_XY);
            comboRow.addView(comboBlock);
        }

        String scoreString = "Score: " + game.getScore();
        String levelString = "Level: " + game.getLevel();
        String comboString = "Combo: " + game.getComboLevel() + "x";

        this.binding.scoreText.setText(scoreString);
        this.binding.levelText.setText(levelString);
        this.binding.comboText.setText(comboString);
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
                listener.endBreakPeriod();
            }
        };
    }

}