package com.example.notefy.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notefy.R;
import com.example.notefy.models.Post;

public class PostRVAdapter extends ListAdapter<Post, PostRVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    public PostRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Post> DIFF_CALLBACK = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(Post oldItem, Post newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Post oldItem, Post newItem) {
            return (oldItem.getId() == newItem.getId()) &&
                    oldItem.getText().equals(newItem.getText()) &&
                    oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post model = getPostAt(position);
        holder.postTitle.setText(model.getTitle());
        holder.postText.setText(model.getText());
    }
    public Post getPostAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView postTitle, postText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.idPostTitle);
            postText = itemView.findViewById(R.id.idPostText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Post model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
