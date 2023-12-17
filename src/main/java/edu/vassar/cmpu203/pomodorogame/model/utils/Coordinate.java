package edu.vassar.cmpu203.pomodorogame.model.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * A utility class allowing for the creation of Points in space, analogous to
 * Point2D in Java, but using <code>int</code>s, and with a vastly decreased
 * size. For board logic, the top left corner is (0, 0).
 */
public class Coordinate implements Serializable {
    /** The x coordinate of the point. */
    public int x;
    /** The y coordinate of the point. */
    public int y;

    /**
     * Creates a 2D point with negative values, meant as a placeholder.
     */
    public Coordinate() {
        this.x = -1;
        this.y = -1;
    }

    /**
     * Creates a 2D point with the specified coordinates.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the Coordinate to the specified coordinates.
     *
     * @param x the new x coordinate of the point
     * @param y the new y coordinate of the point
     */
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Turns the Coordinate into a placeholder value.
     */
    public void reset() {
        this.x = -1;
        this.y = -1;
    }

    /**
     * Determines if a Coordinate is adjacent to the current coordinate.
     *
     * @param other the other Coordinate to be tested
     * @return true if the two Coordinates are adjacent.
     */
    public boolean isAdjacent(@NonNull Coordinate other) {
        int xDiff = Math.abs(this.x - other.x);
        int yDiff = Math.abs(this.y - other.y);
        return (xDiff + yDiff) == 1;
    }

    /**
     * Checks if the point is a non-placeholder point.
     * @return true if both coordinates are positive
     */
    public boolean isValid() {
        return (x >= 0) && (y >= 0);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Coordinate))
            return false;
        return (((Coordinate) obj).x == this.x) && (((Coordinate) obj).y == this.y);
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
