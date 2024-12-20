package uz.devmi.bookland.core.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class NavigationModule {

    @Single
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @Single
    fun provideRouter(cicerone: Cicerone<Router>): Router {
        return cicerone.router
    }

    @Single
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}
