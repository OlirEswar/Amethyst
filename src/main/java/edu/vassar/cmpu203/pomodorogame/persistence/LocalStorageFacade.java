package edu.vassar.cmpu203.pomodorogame.persistence;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.vassar.cmpu203.pomodorogame.model.match3.Match3Game;
import edu.vassar.cmpu203.pomodorogame.model.stats.Stats;
import edu.vassar.cmpu203.pomodorogame.model.todo.ToDoList;

public class LocalStorageFacade implements IPersistenceFacade {

    /** The pwd of the app. */
    private final File dir;
    private static final String GAME_FILE_NAME = "game";
    private static final String STATS_FILE_NAME = "stats";
    private static final String TODO_FILE_NAME = "todo";

    public LocalStorageFacade(@NonNull final File dir) {
        this.dir = dir;
    }

    @Override
    public void saveGame(@NonNull Match3Game game) {
        File out = new File(this.dir, GAME_FILE_NAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(out);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Match3Game retrieveGame() {
        Match3Game game = null;
        File in = new File(this.dir, GAME_FILE_NAME);

        if (in.isFile()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(in);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                game = (Match3Game) objectInputStream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                Log.e("facade", e.getMessage());
                e.printStackTrace();
            }
        }

        return game;
    }

    @Override
    public void saveStats(@NonNull Stats stats) {
        File out = new File(this.dir, STATS_FILE_NAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(out);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(stats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stats retrieveStats() {
        Stats stats = null;
        File in = new File(this.dir, STATS_FILE_NAME);

        if (in.isFile()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(in);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                stats = (Stats) objectInputStream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                Log.e("facade", e.getMessage());
                e.printStackTrace();
            }
        }

        return stats;
    }

    @Override
    public void saveToDos(@NonNull ToDoList toDoList) {
        File out = new File(this.dir, TODO_FILE_NAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(out);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(toDoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ToDoList retrieveToDos() {
        ToDoList toDoList = null;
        File in = new File(this.dir, TODO_FILE_NAME);

        if (in.isFile()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(in);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                toDoList = (ToDoList) objectInputStream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                Log.e("facade", e.getMessage());
                e.printStackTrace();
            }
        }

        return toDoList;
    }
}
