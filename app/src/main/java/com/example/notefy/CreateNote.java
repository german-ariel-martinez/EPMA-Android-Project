package com.example.notefy;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notefy.models.Post;
import com.example.notefy.viewmodels.PostsViewModels;

public class CreateNote extends Fragment {

    private EditText titleEdt, textEdt;
    private Button postBtn;

    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_COURSE_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_NAME";
    public static final String EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION";

    private PostsViewModels post;

    public CreateNote() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initializing our variables for each view.
        titleEdt = view.findViewById(R.id.idTitle);
        textEdt = view.findViewById(R.id.idText);
        postBtn = view.findViewById(R.id.createPost);
        post = ViewModelProviders.of(this).get(PostsViewModels.class);
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            titleEdt.setText(intent.getStringExtra(EXTRA_COURSE_NAME));
            textEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdt.getText().toString();
                String text = textEdt.getText().toString();
                if (title.isEmpty() || text.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter the valid note details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveCourse(title, text);
            }
        });
    }

    private void saveCourse(String title, String text) {
        Intent data = new Intent();

        data.putExtra(EXTRA_COURSE_NAME, title);
        data.putExtra(EXTRA_DESCRIPTION, text);
        int id = getActivity().getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        Post model = new Post(title, text);
        post.insert(model);

        getActivity().setResult(RESULT_OK, data);

        Toast.makeText(getActivity(), "Note has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }

}