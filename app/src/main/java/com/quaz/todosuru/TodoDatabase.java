package com.quaz.todosuru;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Todo.class, version = 2)
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase instance;

    public abstract TodoDao todoDao();

    public static synchronized TodoDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class, "todo_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private TodoDao todoDao;
        private PopulateDbAsyncTask(TodoDatabase db){
            todoDao = db.todoDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.insert(new Todo("Title 1", "Description 1", 1));
            todoDao.insert(new Todo("Title 2", "Description 2", 2));
            todoDao.insert(new Todo("Title 3", "Description 3", 3));
            todoDao.insert(new Todo("Title 4", "Description 4", 4));

            return null;
        }
    }
}
