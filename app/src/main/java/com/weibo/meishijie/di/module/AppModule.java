package com.weibo.meishijie.di.module;

import com.weibo.meishijie.mvp.model.api.MeiShiJieApiService;
import com.weibo.meishijie.mvp.model.api.MeiShiJieCacheApiService;
import com.weibo.meishijie.app.MeishijieApplication;
import com.weibo.meishijie.util.Constant;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/1.
 */

@Module
public class AppModule {

    @Singleton
    @Provides
    public MeiShiJieApiService providesMeiShiJieApiService(Retrofit retrofit) {
        return retrofit.create(MeiShiJieApiService.class);
    }

    @Singleton
    @Provides
    public MeiShiJieCacheApiService providesMeiShiJieCacheApiService(RxCache rxCache) {
        return rxCache.using(MeiShiJieCacheApiService.class);
    }

    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    public RxCache providesRxCache() {
        String cacheDirPath = MeishijieApplication.getContext().getFilesDir().getPath() + "/meishijie";
        File cacheDir = new File(cacheDirPath);
        cacheDir.mkdirs();
        return new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker());
    }
}
