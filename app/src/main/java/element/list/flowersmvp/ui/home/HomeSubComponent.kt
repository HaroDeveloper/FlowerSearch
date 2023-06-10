package element.list.flowersmvp.ui.home

import dagger.Subcomponent

@Subcomponent(modules = [(HomeModule::class)])
interface HomeSubComponent {
    fun inject(homeFragment: HomeFragment): HomeFragment
}