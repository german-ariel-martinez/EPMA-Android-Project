package com.example.notefy.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notefy.models.Post;
import com.example.notefy.repository.PostsRepository;

import java.util.List;

public class PostsViewModels extends AndroidViewModel {

    // creating a new variable for course repository.
    private PostsRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<Post>> allCourses;

    // constructor for our view modal.
    public PostsViewModels(@NonNull Application application) {
        super(application);
        repository = new PostsRepository(application);
        allCourses = repository.getAllPosts();
    }

    // below method is use to insert the data to our repository.
    public void insert(Post model) {
        repository.insert(model);
    }

    // below method is to get all the courses in our list.
    public LiveData<List<Post>> getAllCourses() {
        return allCourses;
    }

}
