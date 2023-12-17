package edu.vassar.cmpu203.pomodorogame.model.match3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.vassar.cmpu203.pomodorogame.model.utils.Coordinate;

/**
 * Testing for the <code>Coordinate</code>'s helper logic.
 */
class CoordinateTest {

    /**
     * Tests for validity of Coordinates set with a variety of means.
     */
    @Test
    void isValid() {
        // instantiation of tests work?
        Coordinate valid = new Coordinate(3, 4);
        Coordinate invalid = new Coordinate();

        assertTrue(valid.isValid());
        assertFalse(invalid.isValid());

        // reset() and set(x, y) work?
        valid.reset();
        invalid.set(3, 4);

        assertFalse(valid.isValid());
        assertTrue(invalid.isValid());

        // using public coordinates to set works?
        valid.x = 2;
        valid.y = 9;
        assertTrue(valid.isValid());
    }

    /**
     * Tests if Coordinates that are adjacent to one another can be deteced.
     */
    @Test
    void isAdjacent() {
        Coordinate a = new Coordinate(4, 4);

        // test North, South, East, West, and NE diagonal
        assertTrue(a.isAdjacent(new Coordinate(4, 5)));
        assertTrue(a.isAdjacent(new Coordinate(4, 3)));
        assertTrue(a.isAdjacent(new Coordinate(5, 4)));
        assertTrue(a.isAdjacent(new Coordinate(3, 4)));
        assertFalse(a.isAdjacent(new Coordinate(5, 5)));

        // test same coordinate, invalid coordinate, and others
        assertFalse(a.isAdjacent(a));
        assertFalse(a.isAdjacent(new Coordinate(4, 4)));

        assertFalse(a.isAdjacent(new Coordinate()));

        assertFalse(a.isAdjacent(new Coordinate(2, 9)));
        assertFalse(a.isAdjacent(new Coordinate(0, 0)));
        assertFalse(a.isAdjacent(new Coordinate(7, 5)));
        assertFalse(a.isAdjacent(new Coordinate(12, 1)));
    }
}