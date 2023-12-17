package edu.vassar.cmpu203.pomodorogame.model.todo;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * A wrapper class for a LinkedList that describes a collection
 * of To Do items.
 */
public class ToDoList implements Serializable {
    /** The structure that holds the to do list objects. */
    LinkedList<String> toDoLinkedList;

    /** Default constructor: instantiates an empty ToDoList. */
    public ToDoList() {
        this.toDoLinkedList = new LinkedList<String>();
    }

    /** Adds a created to do item to the list. */
    public void create(String toDo) {
        toDoLinkedList.push(toDo);
    }

    /** Gets the to do item at the specified index.
     * @param index the item to be retrieved index */
    public String get(int index) {
        return toDoLinkedList.get(index);
    }

    /** Deletes the to do item at the specified index.
     * @param index the item to be deleted index */
    public void delete(int index) {
        toDoLinkedList.remove(index);
    }

    /** Returns the size of the ToDoList.
     * @return the size of the ToDoList */
    public int size() {
        return toDoLinkedList.size();
    }

}
