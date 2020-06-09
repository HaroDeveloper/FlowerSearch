package element.list.flowersmvp.home

import dagger.Module
import dagger.Provides
import element.list.flowersmvp.FlowerService

@Module
class HomeModule {
    @Provides
    fun providePresenter(homeInteractor: HomeInteractor): HomePresenter {
        return HomePresenter(homeInteractor)
    }

    @Provides
    fun provideInteractor(flowerService: FlowerService): HomeInteractor {
        return HomeInteractor(flowerService)
    }
}