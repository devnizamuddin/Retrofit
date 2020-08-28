package com.e.retrofitone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.e.retrofitone.R;
import com.e.retrofitone.databinding.PostSingleLayoutBinding;
import com.e.retrofitone.model.Post;

import java.util.ArrayList;
import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PostSingleLayoutBinding postSingleLayoutBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.post_single_layout, parent, false);


        return new PostViewHolder(postSingleLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {

        Post post = posts.get(position);
        holder.postSingleLayoutBinding.setPost(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        PostSingleLayoutBinding postSingleLayoutBinding;

        public PostViewHolder(@NonNull PostSingleLayoutBinding postSingleLayoutBinding) {
            super(postSingleLayoutBinding.getRoot());
            this.postSingleLayoutBinding = postSingleLayoutBinding;

        }
    }
    public void updatePosts(List<Post>posts){

        this.posts=posts;
        notifyDataSetChanged();

    }
}
