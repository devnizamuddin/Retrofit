package com.e.retrofitone.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.e.retrofitone.model.Post;
import com.e.retrofitone.model.PostRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {

    PostRepository repository;

    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = new PostRepository(application);

    }

    public MutableLiveData<List<Post>> getPosts(String header) {

        return repository.getPosts(header);
    }

    public MutableLiveData<Post> postPost(Post post) {

        return repository.postPost(post);

    }
    public MutableLiveData<Post> putPost(int id,Post post) {

        return repository.putPost(id,post);

    }
    public MutableLiveData<Post> patchPost(String header,int id,Post post) {

        return repository.patchPost(header,id,post);

    }
    public void deletePost(int id) {

        repository.deletePost(id);
    }



}
