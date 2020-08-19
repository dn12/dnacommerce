package net.adiwilaga.minicommerce

import android.app.Application
import net.adiwilaga.minicommerce.util.appModule
import org.koin.core.context.startKoin

class MiniApplication:Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }

    }

}