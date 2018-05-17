package pract.rsys.com.paginationrecyclerview;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by akash.sharma on 5/16/2018.
 */

public class RetrofitApiClient {

   private static Retrofit retrofit;
   private static String baseUrl="https://api.androidhive.info/";

    public static Retrofit getApiClient(){

        if(retrofit==null)
        {
           retrofit = new Retrofit.Builder()
                   .baseUrl(baseUrl)
                   .client(new OkHttpClient())
                   .addConverterFactory(SimpleXmlConverterFactory.create())
                   .build();

        }
        return retrofit;
    }
}
