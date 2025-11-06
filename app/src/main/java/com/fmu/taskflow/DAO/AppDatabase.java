package com.fmu.taskflow.DAO;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;

import com.fmu.taskflow.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase{
    public abstract TarefaDAO tarefaDAO();

    private static final String DB_NAME = "db_tarefas";
    private static AppDatabase AppDatabase;

    public static AppDatabase getAppDatabase(Context context){
        if (AppDatabase == null){
            AppDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return AppDatabase;
    }

}
