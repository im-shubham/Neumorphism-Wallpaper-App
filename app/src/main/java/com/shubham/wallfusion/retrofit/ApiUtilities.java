package com.shubham.wallfusion.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

       public   static  ApiUtilities apiUtilities;

        private static Retrofit retrofit;
        public static final String API_KEY = "563492ad6f91700001000001f308c6b885204d668de41a7f8d8cba04";

        public ApiUtilities() {
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        public static synchronized ApiUtilities getInstance(){
            if(apiUtilities == null){
                apiUtilities =  new ApiUtilities();
            }
            return apiUtilities;
        }
        public ApiInterface getApi(){
            return  retrofit.create(ApiInterface.class);
        }
    }


