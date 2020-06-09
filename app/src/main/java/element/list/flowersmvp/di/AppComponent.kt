package element.list.flowersmvp.di

import dagger.Component
import element.list.flowersmvp.FlowerApp
import element.list.flowersmvp.di.modules.AppModule
import element.list.flowersmvp.di.modules.NetworkModule
import element.list.flowersmvp.home.HomeModule
import element.list.flowersmvp.home.HomeSubComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AppModule::class),
        (NetworkModule::class)
    ]
)

interface AppComponent {
    fun inject(application: FlowerApp)
    fun plus(homeModule: HomeModule): HomeSubComponent
}