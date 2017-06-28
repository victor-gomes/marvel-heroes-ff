package vgomes.marvel_heroes_client.comms.interceptors;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import vgomes.marvel_heroes_client.utils.Utils;

/**
 * Created by victorgomes on 27/06/17.
 */

public class ApiKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        long ts = System.currentTimeMillis();
        String hash = Utils.md5(String.format(Locale.getDefault(), "%d%s%s", ts, "6f7c2b9f28f943eb7605715917526803b9574186", "760ac0149bdec6266a8dce47e52148aa"));
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("ts", String.valueOf(ts))
                .addQueryParameter("apikey", "760ac0149bdec6266a8dce47e52148aa")
                .addQueryParameter("hash", hash)
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }


}
