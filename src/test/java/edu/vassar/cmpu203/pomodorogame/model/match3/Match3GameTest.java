package edu.vassar.cmpu203.pomodorogame.model.match3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing class for the Match-3 game's logic.
 */
class Match3GameTest {

    /** The game class that will be used in each testing function. */
    Match3Game game;

    /**
     * Resets the board for each test.
     */
    @BeforeEach
    void init() {
        game = new Match3Game();
    }

    /**
     * Tests if vertical match logic and point generation works.
     * A ">=" is used in the rare chance that a second match is generated
     * due to placement of gems, or the generation of new gems.
     * This specifically tests for 3-in-a-row matches.
     */
    @Test
    void test3VerticalValidMatch() {
        createVertMatchBoard(game.board, 3);
        game.selectGem(0, 2);
        game.selectGem(1, 2);

        assertTrue(game.getScore() >= 150);
    }

    /** Tests if vertical match logic and point generation works for
     * a 4-in-a-row match. */
    @Test
    void test4VerticalValidMatch() {
        createVertMatchBoard(game.board, 4);
        game.selectGem(0, 2);
        game.selectGem(1, 2);

        assertTrue(game.getScore() >= 200);
    }

    /** Tests if vertical match logic and point generation works for
     * a 5-in-a-row match. */
    @Test
    void test5VerticalValidMatch() {
        createVertMatchBoard(game.board, 5);
        game.selectGem(0, 2);
        game.selectGem(1, 2);

        assertTrue(game.getScore() >= 250);
    }

    /**
     * Tests if horizontal match logic and point generation works.
     * A ">=" is used in the rare chance that a second match is generated
     * due to placement of gems, or the generation of new gems.
     * This specifically tests for 3-in-a-row matches.
     */
    @Test
    void test3HorizontalValidMatch() {
        createHorzMatchBoard(game.board, 3);
        game.selectGem(2, 1);
        game.selectGem(2, 0);

        assertTrue(game.getScore() >= 150);
    }

    /** Tests if horizontal match logic and point generation works for
     * a 4-in-a-row match. */
    @Test
    void test4HorizontalValidMatch() {
        createHorzMatchBoard(game.board, 4);
        game.selectGem(2, 1);
        game.selectGem(2, 0);

        assertTrue(game.getScore() >= 200);
    }

    /** Tests if horizontal match logic and point generation works for
     * a 5-in-a-row match. */
    @Test
    void test5HorizontalValidMatch() {
        createHorzMatchBoard(game.board, 3);
        game.selectGem(2, 1);
        game.selectGem(2, 0);

        assertTrue(game.getScore() >= 250);
    }

    /**
     * Tests if gem fall and generation of new gems from the ceiling
     * works. This uses a board with an empty top and bottom row, along
     * with a pre-generated match in order to trigger gem fall and
     * gem generation.
     */
    @Test
    void testGemFallAndGeneration() {
        createGemFallBoard(game.board);
        game.selectGem(0, 2);
        game.selectGem(1, 2);

        for (int y = 0; y < 8; y++) {
            assertNotEquals(game.board[0][y], new Gem(GemColor.NONE));
            assertNotEquals(game.board[7][y], new Gem(GemColor.NONE));
        }
    }

    /**
     * Creates a board with an empty top row and bottom row,
     * for testing gem generation and gravity. To engage
     * gem fall, swap (1, 2) -> (0, 2).
     * @param b the board to be remade
     */
    void createGemFallBoard(Gem[][] b) {
        for (int y = 0; y < 8; y++) {
            b[0][y] = new Gem(GemColor.NONE);
            b[7][y] = new Gem(GemColor.NONE);
        }

        b[1][2] = new Gem(GemColor.RED);
        b[0][1] = new Gem(GemColor.RED);
        b[0][0] = new Gem(GemColor.RED);
    }

    /**
     * Creates a gem board that allows for vertical n-match.
     * The swap will be made between (1, 2) -> (0, 2).
     * @param b the board to be remade
     * @param num the length of the match to be made (3 <= n <= 5)
     */
    void createVertMatchBoard(Gem[][] b, int num) {
        switch (num) {
            case 5:
                b[0][4] = new Gem(GemColor.RED);
            case 4:
                b[0][3] = new Gem(GemColor.RED);
            default:
                b[1][2] = new Gem(GemColor.RED);
                b[0][1] = new Gem(GemColor.RED);
                b[0][0] = new Gem(GemColor.RED);
        }
    }

    /**
     * Creates a gem board that allows for horizontal n-match.
     * The swap will be made between (2, 1) -> (2, 0)
     * @param b the board to be remade
     * @param num the length of the match to be made (3 <= n <= 5)
     */
    void createHorzMatchBoard(Gem[][] b, int num) {
        switch (num) {
            case 5:
                b[4][0] = new Gem(GemColor.RED);
            case 4:
                b[3][0] = new Gem(GemColor.RED);
            default:
                b[2][1] = new Gem(GemColor.RED);
                b[1][0] = new Gem(GemColor.RED);
                b[0][0] = new Gem(GemColor.RED);
        }
    }
}