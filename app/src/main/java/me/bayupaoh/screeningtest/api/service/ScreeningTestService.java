package me.bayupaoh.screeningtest.api.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import me.bayupaoh.screeningtest.api.dao.GuestDao;
import me.bayupaoh.screeningtest.util.AppConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by dsantren on 09/08/2017.
 */

public interface ScreeningTestService {
    @GET("people")
    Observable<List<GuestDao>> getListPeople();

    class factory {
        public static ScreeningTestService create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ScreeningTestService.class);
        }
    }
}
