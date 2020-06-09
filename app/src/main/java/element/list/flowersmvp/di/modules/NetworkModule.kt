package element.list.flowersmvp.di.modules

import dagger.Module
import dagger.Provides
import element.list.flowersmvp.FlowerService
import element.list.flowersmvp.constants.Constants
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitBuilder(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder.connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .cookieJar(CookieJar.NO_COOKIES)
        return httpBuilder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideFlowerServiceApi(retrofit: Retrofit): FlowerService {
        return retrofit.create(FlowerService::class.java)
    }

}