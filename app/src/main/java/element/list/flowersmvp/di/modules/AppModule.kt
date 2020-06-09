package element.list.flowersmvp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import element.list.flowersmvp.FlowerApp
import javax.inject.Singleton

@Module
class AppModule(private val application: FlowerApp) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun getApplication(): Application {
        return application
    }
}