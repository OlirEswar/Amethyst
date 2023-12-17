package edu.vassar.cmpu203.pomodorogame.model.match3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.vassar.cmpu203.pomodorogame.model.match3.GemColor;
import edu.vassar.cmpu203.pomodorogame.model.match3.Match;
import edu.vassar.cmpu203.pomodorogame.model.utils.Coordinate;

class MatchTest {

    /**
     * Tests if <code>addAndCheck()</code> by confirming that adding 3+ points
     * will return a valid match.
     */
    @Test
    void testAddAndCheck() {
        Match testMatch = new Match(GemColor.BLUE);
        Coordinate c = new Coordinate(0, 0);
        assertFalse(testMatch.addAndCheck(c));
        assertFalse(testMatch.addAndCheck(c));
        assertTrue(testMatch.addAndCheck(c)); // 3 matches = valid match
        assertTrue(testMatch.addAndCheck(c));
    }
}