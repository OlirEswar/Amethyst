package edu.vassar.cmpu203.pomodorogame.model.match3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * The main game object of the <code>Match3Game</code>.
 */
public class Gem implements Serializable {
    /** The color of the gem. */
    GemColor color;
    /** A list of all valid <code>GemColor</code>s, for use in randomly
     * picking a color. */
    private final static GemColor[] GEM_COLORS = {
            GemColor.RED, GemColor.ORANGE, GemColor.YELLOW,
            GemColor.GREEN, GemColor.BLUE, GemColor.PURPLE, GemColor.WHITE
    };

    /**
     * Creates a gem with the specified color.
     * @param color the desired color of the gem
     */
    public Gem(GemColor color) {
        this.color = color;
    }

    /**
     * Factory method used to generate a gem with a random color.
     * @return a gem with a random playable gem color
     */
    public static Gem getRandomGem() {
        return new Gem(GEM_COLORS[(int) (Math.random() * 7)]);
    }

    /**
     * @return a gem with the exact color as the current gem
     */
    @NonNull
    public Gem clone() {
        return new Gem(this.color);
    }

    public GemColor getColor() {
        return color;
    }

    /**
     * @return the <code>GemColor</code> of the Gem as a String
     */
    @NonNull
    @Override
    public String toString() {
        return String.valueOf(color);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Gem))
            return false;
        return ((Gem) obj).getColor() == color;
    }
}
