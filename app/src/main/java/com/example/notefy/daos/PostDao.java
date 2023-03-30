package com.example.notefy.daos;
import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notefy.database.NotefyDatabase;
import com.example.notefy.models.Post;
import java.util.List;

@androidx.room.Dao
public interface PostDao {

    @Insert
    void insert(Post model);
    @Query("SELECT * FROM posts")
    LiveData<List<Post>> getAllPosts();

}
