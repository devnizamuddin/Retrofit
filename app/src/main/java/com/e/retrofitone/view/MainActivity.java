package com.e.retrofitone.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.e.retrofitone.Adapter.PostAdapter;
import com.e.retrofitone.R;
import com.e.retrofitone.databinding.ActivityMainBinding;
import com.e.retrofitone.model.Post;
import com.e.retrofitone.viewmodel.PostViewModel;
import com.e.retrofitone.viewmodel.PostViewModelFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    PostAdapter postAdapter;
    List<Post> posts = new ArrayList<>();

    PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.postRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        postAdapter = new PostAdapter(MainActivity.this, posts);
        mainBinding.postRv.setAdapter(postAdapter);


        postViewModel = new ViewModelProvider(this,
                new PostViewModelFactory(getApplication(), null)).get(PostViewModel.class);


        postViewModel.getPosts(getTimestamp()).observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {

                postAdapter.updatePosts(posts);

            }
        });

        //========================== Comments ==================================//

       /* repository.getComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                Toast.makeText(MainActivity.this, ""+comments.size(), Toast.LENGTH_LONG).show();
            }
        });*/


      // postViewModel.postPost(new Post(100,"Tittle 100","Body 200"));

       /* postViewModel.patchPost(getTimestamp(),2,new Post(25,null,"Hello")).observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                Toast.makeText(MainActivity.this, ""+post.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(new Date());
    }
}