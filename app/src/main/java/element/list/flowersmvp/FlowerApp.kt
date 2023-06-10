package element.list.flowersmvp

import android.app.Application
import element.list.flowersmvp.di.AppComponent
import element.list.flowersmvp.di.DaggerAppComponent
import element.list.flowersmvp.di.modules.AppModule
import element.list.flowersmvp.ui.home.HomeFragment
import element.list.flowersmvp.ui.home.HomeModule

class FlowerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}