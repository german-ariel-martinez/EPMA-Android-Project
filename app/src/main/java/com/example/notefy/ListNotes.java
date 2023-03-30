package com.example.notefy;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.notefy.models.Post;
import com.example.notefy.recyclerview.PostRVAdapter;
import com.example.notefy.viewmodels.PostsViewModels;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import androidx.lifecycle.Observer;

public class ListNotes extends Fragment {

    private RecyclerView postRV;
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;
    private PostsViewModels post;

    public ListNotes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postRV = getActivity().findViewById(R.id.recyclerView);
        postRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        postRV.setHasFixedSize(true);
        final PostRVAdapter adapter = new PostRVAdapter();
        postRV.setAdapter(adapter);
        post = ViewModelProviders.of(this).get(PostsViewModels.class);
        post.getAllCourses().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> models) {
                adapter.submitList(models);
            }
        });
    }
}