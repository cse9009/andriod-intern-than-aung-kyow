package com.takfirm.than.androidintern.api;

import com.takfirm.than.androidintern.models.Search;
import com.takfirm.than.androidintern.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("15baeq")
    Call<Search> getSearchResults();


}
