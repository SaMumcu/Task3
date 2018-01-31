package mumcu.sabiha.task3.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sabis on 1/30/2018.
 */

public class ApiRequest {

    public static String BASEURL="https://getir-bitaksi-hackathon.herokuapp.com/";


    // Get Retrofit instance
    private static Retrofit getRefrofitInstance(){
        Retrofit rf = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return rf;
    }

    // Return Api Service
    public static ApiInterface getApiService(){
        ApiInterface api = getRefrofitInstance().create(ApiInterface.class);
        return api;
    }

}
