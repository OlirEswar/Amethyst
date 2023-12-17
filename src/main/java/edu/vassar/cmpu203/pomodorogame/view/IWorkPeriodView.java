package edu.vassar.cmpu203.pomodorogame.view;

import edu.vassar.cmpu203.pomodorogame.model.todo.ToDoList;

/**
 * Interface that defines functions to be used by the WorkPeriod.
 */
public interface IWorkPeriodView {
    /**
     * The listener that defines and calls methods to the model, through the controller.
     */
    interface Listener {
        /** Ends the work period and initiates transition to the break period. */
        void endWorkPeriod();

        /** Ends the work period and initiates transition to the stats screen. */
        void endSession();

        /**
         * Adds a to do item to the ToDoList.
         * @param toDoItem the String of the item to be added
         * @param view the Work period view to be updated
         */
        void addToDoItem(String toDoItem, IWorkPeriodView view);

        /**
         * Deletes a to do item in the ToDoList.
         * @param index the index of the item to be deleted
         * @param view the Work period view to be updated
         */
        void deleteToDoItem(int index, IWorkPeriodView view);

        /**
         * Finishes a to do item in the ToDoList
         * @param index the index of the item to be completed
         * @param view the Work period view to be updated
         */
        void finishToDoItem(int index, IWorkPeriodView view);
    }

    /**
     * Re-renders the ToDoList ScrollView with new ToDoList elements.
     * @param toDoList the ToDoList to update from
     */
    void update(ToDoList toDoList);
}
