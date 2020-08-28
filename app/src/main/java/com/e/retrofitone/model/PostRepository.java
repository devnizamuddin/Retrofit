package com.e.retrofitone.model;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.e.retrofitone.service.PostService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //List Object
    List<Post> posts = new ArrayList<>();
    List<Comment>comments = new ArrayList<>();
    //LiveData Object
    MutableLiveData<List<Post>> postsLiveData;
    MutableLiveData<Post>postLiveData;
    MutableLiveData<List<Comment>> commentsLiveData;
    private Application application;

    PostService postService;

    public PostRepository(Application application) {

        this.application = application;
    }

    private PostService getPostService() {

        if (postService == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            postService = retrofit.create(PostService.class);
        }

        return postService;
    }

    public MutableLiveData<List<Post>> getPosts(String header) {

        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }


        Call<List<Post>> posts_call = getPostService().getPosts(header);

        posts_call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (response.code() == 200) {
                    posts = response.body();
                    postsLiveData.setValue(posts);
                    Toast.makeText(application.getApplicationContext(), "Data got", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(), "Error : " + response.message(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(application.getApplicationContext(), "Failure : " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return postsLiveData;

    }
    public MutableLiveData<List<Comment>> getComments(){

        if (commentsLiveData==null){
            commentsLiveData = new MutableLiveData<>();

        }


        getPostService().getCommentsByPostId(2).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.code()==200){
                    comments = response.body();
                    Toast.makeText(application.getApplicationContext(), "Comments : "+comments.get(0).getPostId(), Toast.LENGTH_LONG).show();
                    commentsLiveData.setValue(comments);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });

        return commentsLiveData;
    }

    public MutableLiveData<Post> postPost(Post post){

        if (postLiveData==null){
            postLiveData = new MutableLiveData<>();
        }

        getPostService().postPostForm(post.getUserId(),post.getTitle(),post.getBody()).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()){

                    Post postPost = response.body();
                    postLiveData.setValue(postPost);
                    Toast.makeText(application.getApplicationContext(), "Successful : "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });


        return postLiveData;

    }

    public MutableLiveData<Post> putPost(int id,Post post){

        if (postLiveData==null){
            postLiveData = new MutableLiveData<>();
        }

        getPostService().putPost(id,post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()){
                    Post putPost = response.body();
                    postLiveData.setValue(putPost);
                }
                else {
                    Toast.makeText(application.getApplicationContext(), "Response : "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Failure : "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return postLiveData;

    }

    public MutableLiveData<Post> patchPost(String header ,int id,Post post){

        if (postLiveData==null){
            postLiveData = new MutableLiveData<>();
        }

        getPostService().patchPost(header,id,post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()){
                    Post patchPost = response.body();
                    postLiveData.setValue(patchPost);
                }
                else {
                    Toast.makeText(application.getApplicationContext(), "Response  : "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                Toast.makeText(application.getApplicationContext(), "Failure : "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return postLiveData;

    }

    public void deletePost(int id){

      getPostService().deletePost(id).enqueue(new Callback<Void>() {
          @Override
          public void onResponse(Call<Void> call, Response<Void> response) {

              if (response.isSuccessful()){
                  Toast.makeText(application.getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
              }
          }

          @Override
          public void onFailure(Call<Void> call, Throwable t) {

          }
      });

    }
}
