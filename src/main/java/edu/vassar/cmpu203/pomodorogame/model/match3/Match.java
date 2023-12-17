package edu.vassar.cmpu203.pomodorogame.model.match3;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.pomodorogame.model.utils.Coordinate;

/**
 * A representation of a match in the game using a <code>List</code> of
 * <code>Coordinate</code>s.
 */
public class Match {
    /** The list of coordinates which correspond to gems on the Match3Game board. */
    List<Coordinate> slots;
    /** The color of all gems in this match. */
    GemColor color;

    /** Instantiates a new <code>Match</code>.
     * @param color the current color of the <code>Match</code> */
    public Match(GemColor color) {
        slots = new ArrayList<>(8);
        this.color = color;
    }

    /**
     * Adds a coordinate to the <code>Match</code> and checks if it is valid.
     * (3 or more coordinates)
     * @param c the coordinate to be added to the current <code>Match</code>
     * @return a boolean on whether the <code>Match</code> is valid or not
     */
    public boolean addAndCheck(@NonNull Coordinate c) {
        slots.add(c);
        return slots.size() >= 3;
    }

    public int getScore() {
        return (50 * slots.size());
    }

    public List<Coordinate> getSlots() {
        return slots;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.valueOf(color));
        sb.append(" MATCH:");
        for (Coordinate c : slots) {
            sb.append(" ").append(c.toString());
        }
        return sb.toString();
    }
}
