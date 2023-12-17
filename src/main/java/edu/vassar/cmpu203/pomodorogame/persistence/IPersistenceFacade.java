package edu.vassar.cmpu203.pomodorogame.persistence;

import androidx.annotation.NonNull;

import edu.vassar.cmpu203.pomodorogame.model.match3.Match3Game;
import edu.vassar.cmpu203.pomodorogame.model.stats.Stats;
import edu.vassar.cmpu203.pomodorogame.model.todo.ToDoList;

/**
 * Interface that defines methods to be implemented by storage facades
 */
public interface IPersistenceFacade {

    /**
     * Issues a game save operation.
     * @param game the game to be saved
     */
    void saveGame(@NonNull Match3Game game);

    /**
     * Issues a game retrieval operation.
     * @return the game, null if one hasn't been started yet
     */
    Match3Game retrieveGame();

    /**
     * Issues a stats save operation.
     * @param stats the statistics object to be saved
     */
    void saveStats(@NonNull Stats stats);

    /**
     * Issues a stats retrieval operation.
     * @return the statistics object, null if one hasn't been made yet
     */
    Stats retrieveStats();

    /**
     * Issues a to do list save operation.
     * @param toDoList the to do list to be saved
     */
    void saveToDos(@NonNull ToDoList toDoList);

    /**
     * Issues a to do list retrieval operation.
     * @return the to do list, null if one hasn't been made yet
     */
    ToDoList retrieveToDos();
}
