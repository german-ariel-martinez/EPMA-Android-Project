package com.example.notefy.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.notefy.daos.PostDao;
import com.example.notefy.database.NotefyDatabase;
import com.example.notefy.models.Post;

import java.util.List;

public class PostsRepository {
    private PostDao dao;
    private LiveData<List<Post>> allCourses;

    public PostsRepository(Application application) {
        NotefyDatabase database = NotefyDatabase.getInstance(application);
        dao = database.Dao();
        allCourses = dao.getAllPosts();
    }

    public void insert(Post model) {
        new InsertPostAsyncTask(dao).execute(model);
    }

    public LiveData<List<Post>> getAllPosts() {
        return allCourses;
    }

    private static class InsertPostAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao dao;

        private InsertPostAsyncTask(PostDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Post... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

}
