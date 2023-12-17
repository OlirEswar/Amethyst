package edu.vassar.cmpu203.pomodorogame.model.match3;

import java.io.Serializable;
import java.util.ArrayList;

import edu.vassar.cmpu203.pomodorogame.model.utils.Coordinate;

/**
 * Implementation of a Match-3 game, including board logic, moving <code>Gem</code>s,
 * and score logic.
 */
public class Match3Game implements Serializable {
    /** The current level of the game. */
    int level;
    /** The current aggregate score of the game. */
    int score;
    /** The score needed to advance to the next level. */
    int scoreCap;
    /** The amount of score accumulated in one level. */
    int progressToCap;
    /** The current combo level. The maximum is x10. */
    int comboLevel;
    /** The progress to the next combo level. */
    int comboProgress;
    /** The combo required to advance to the next level. */
    private final static int[] COMBO_CAPS = {
            3, 3, 5, 5, 7, 7, 9, 9, 10, 10
    };

    /** The current positions of <code>Gem</code>s on the board. */
    Gem[][] board;

    /** The position of the current <code>Gem</code> selected. */
    Coordinate selectedGem;
    /** The position of the second <code>Gem</code> gem,
     *  to be swapped with the first.*/
    Coordinate swapGem;

    /** Default constructor */
    public Match3Game() {
        level = 1;
        score = 0;
        scoreCap = 5000;
        progressToCap = 0;
        comboLevel = 1;
        comboProgress = 0;
        do {
            Gem[][] test = new Gem[8][8];
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    test[x][y] = Gem.getRandomGem();
                }
            }
            board = test.clone();
        } while (this.calculateMatches().size() != 0);
        selectedGem = new Coordinate();
        swapGem = new Coordinate();
    }

    /**
     * Selects a gem on the board, executing game logic on whether
     * or not a gem is already selected, and must be swapped.
     * @param x the x coordinate of the selection
     * @param y the y coordinate of the selection
     */
    public void selectGem(int x, int y) {
        if (selectedGem.isValid()) {
            swapGem.set(x,y);

            boolean isValidSwap = selectedGem.isAdjacent(swapGem);

            if (isValidSwap) {
                boolean didMatch = this.swapGems();
                calculateCombo(didMatch);
            }
            selectedGem.reset();
            swapGem.reset();
        } else {
            selectedGem.set(x, y);
        }
    }

    /**
     * Swaps two gems, executing game logic if the swap
     * creates a valid match between 3+ gems in a row.
     *
     * @return a boolean if matches were generated!
     */
    private boolean swapGems() {
        boolean createdMatch = false;
        Gem select = board[selectedGem.x][selectedGem.y].clone();
        board[selectedGem.x][selectedGem.y] = board[swapGem.x][swapGem.y].clone();
        board[swapGem.x][swapGem.y] = select;

        ArrayList<Match> matches = this.calculateMatches();
        while (matches.size() > 0) {
            createdMatch = true;
            this.removeGems(matches);
            for (Match m : matches) {
                int finalScore = comboLevel * m.getScore();
                progressToCap += finalScore;
                score += finalScore;
            }
            this.updateScore();
            matches = this.calculateMatches();
        }
        return createdMatch;
    }

    //TODO: either here, or in separate method, cobble together potential T-shaped matches
    /**
     * Calculates every single match that is valid on the board currently.
     * @return an <code>ArrayList</code> containing all valid matches
     */
    private ArrayList<Match> calculateMatches() {
        ArrayList<Match> matches = new ArrayList<>(8);
        Gem currentGem;
        Match potentialMatch = new Match(board[0][0].getColor());

        for (int x = 0; x < 8; x++) {
            currentGem = board[x][0];
            for (int y = 0; y < 8; y++) {
                Gem checkGem = board[x][y];
                if (currentGem.equals(checkGem)) {
                    if (potentialMatch.addAndCheck(new Coordinate(x, y))) {
                        matches.add(potentialMatch);
                    }
                } else {
                    potentialMatch = new Match(checkGem.getColor());
                    potentialMatch.addAndCheck(new Coordinate(x, y));
                    currentGem = checkGem;
                }
            }
            if (x != 7)
                potentialMatch = new Match(board[x + 1][0].getColor());
        }

        potentialMatch = new Match(board[0][0].getColor());
        for (int y = 0; y < 8; y++) {
            currentGem = board[0][y];
            for (int x = 0; x < 8; x++) {
                Gem checkGem = board[x][y];
                if (currentGem.equals(checkGem)) {
                    if (potentialMatch.addAndCheck(new Coordinate(x, y))) {
                        matches.add(potentialMatch);
                    }
                } else {
                    potentialMatch = new Match(checkGem.getColor());
                    potentialMatch.addAndCheck(new Coordinate(x, y));
                    currentGem = checkGem;
                }
            }
            if (y != 7)
                potentialMatch = new Match(board[0][y + 1].getColor());
        }

        return matches;
    }

    /**
     * Removes the gems according to the list of positions per valid match found
     * on the board, replacing them with a placeholder NONE gem.
     * @param matches a list of valid matches to be removed from the board.
     */
    private void removeGems(ArrayList<Match> matches) {
        for (Match m : matches) {
            for (Coordinate c : m.getSlots()) {
                board[c.x][c.y] = new Gem(GemColor.NONE);
            }
        }
        doGemFall();
    }

    /** Bubbles every NONE gem to the top, eventually replacing them with
     * new gems which replace them on the board. */
    private void doGemFall() {
        int replaced = 1;
        int generated = 1;

        while (replaced > 0 && generated > 0) {
            replaced = 0;
            generated = 0;
            // rise NONE gems to the top
            for (int y = 8 - 1; y > 0; y--) {
                for (int x = 0; x < 8; x++) {
                    Gem current = board[x][y];
                    if (current.color == GemColor.NONE) {
                        Gem above = board[x][y-1];
                        board[x][y] = above;
                        board[x][y-1] = current;
                        replaced++;
                    }
                }
            }
            // replace NONE gems at top with random gems
            for (int x = 0; x < 8; x++) {
                if (board[x][0].getColor() == GemColor.NONE) {
                    board[x][0] = Gem.getRandomGem();
                    generated++;
                }
            }
        }
    }

    /** Checks if the current score is above the next level threshold.
     * If so, increase the level and reset the score + score cap. */
    private void updateScore() {
        if (progressToCap >= scoreCap) {
            progressToCap -= scoreCap;
            level++;
            scoreCap *= 1.5;
        }
    }

    /**
     * Handles the levelling up of the combo.
     * @param validSwap if the swap given was valid
     */
    private void calculateCombo(boolean validSwap) {
        if (validSwap) {
            if (comboLevel == 10) {
                comboProgress = COMBO_CAPS[comboLevel - 1];
                return;
            }
            if (++comboProgress > this.getComboCap()) {
                comboProgress = 0;
                comboLevel++;
            }
        } else {
            if (comboProgress > 0)
                comboProgress = 0;
            else {
                if (comboLevel == 1)
                    return;
                comboLevel--;
            }
        }
    }

    public Coordinate getSelectedGem() {
        return selectedGem;
    }

    public Gem[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getComboLevel() {
        return comboLevel;
    }

    public int getComboProgress() {
        return comboProgress;
    }

    public int getComboCap() {
        return COMBO_CAPS[comboLevel - 1];
    }
}
