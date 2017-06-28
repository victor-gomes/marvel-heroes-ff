package vgomes.marvel_heroes_client.comms.components;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vgomes.marvel_heroes_client.comms.consts.ApiConstants;
import vgomes.marvel_heroes_client.comms.interceptors.ApiKeyInterceptor;

/**
 * Created by victorgomes on 27/06/17.
 */

public class RetrofitComponent implements IRetrofitComponent {

    @Override
    public Retrofit getAdapter() {
        return new Retrofit.Builder().client(getHttpClient()).addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(ApiConstants.BASE_URL).build();
    }

    private OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder =
                new OkHttpClient.Builder();
        builder.addInterceptor(new ApiKeyInterceptor());
        return builder.build();
    }


}
