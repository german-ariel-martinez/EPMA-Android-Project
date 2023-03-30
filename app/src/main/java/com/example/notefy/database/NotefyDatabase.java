package com.example.notefy.database;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notefy.daos.PostDao;
import com.example.notefy.models.Post;

@Database(entities = {Post.class}, version = 1)
public abstract class NotefyDatabase extends RoomDatabase {

    // below line is to create instance
    // for our database class.
    private static NotefyDatabase instance;

    // below line is to create
    // abstract variable for dao.
    public abstract PostDao Dao();

    // on below line we are getting instance for our database.
    public static synchronized NotefyDatabase getInstance(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                                    NotefyDatabase.class, "notefy_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(NotefyDatabase instance) {
            PostDao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
