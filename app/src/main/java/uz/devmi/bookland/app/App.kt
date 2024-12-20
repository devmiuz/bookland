package uz.devmi.bookland.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module
import uz.devmi.bookland.core.di.DatabaseModule
import uz.devmi.bookland.core.di.NavigationModule
import uz.devmi.bookland.core.di.NetworkModule

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                listOf(
                    NetworkModule().module,
                    DatabaseModule().module,
                    NavigationModule().module
                )
            )
        }
    }
}