package com.e.retrofitone.service;

import com.e.retrofitone.model.Comment;
import com.e.retrofitone.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {


    @GET("posts/")
    Call<List<Post>> getPosts(@Header("date-time") String header);

    @GET("posts/")
    Call<List<Post>> getPostByUserId(@Query("userId") int userId);

    @GET("posts/")
    Call<List<Post>> getPostByShorting(@Query("userId") int userId, @Query("userId") int userIdTWo,
                                       @Query("_sort") String id, @Query("_order") String order);

    @GET("posts/{id}/comments/")
    Call<List<Comment>> getCommentsByPostId(@Path("id") int postId);

    @POST("posts/")
    Call<Post> postPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts/")
    Call<Post>postPostFieldMap(@FieldMap Map<String,String>postMap);

    @FormUrlEncoded
    @POST("posts/")
    Call<Post> postPostForm(@Field("userId") int userId, @Field("title") String title,
                            @Field("body") String body);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Header("date-time") String header, @Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
