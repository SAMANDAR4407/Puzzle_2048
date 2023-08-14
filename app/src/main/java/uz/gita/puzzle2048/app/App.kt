package uz.gita.puzzle2048.app

import android.app.Application
import uz.gita.puzzle2048.local.PreferenceHelper
import uz.gita.puzzle2048.repository.AppRepository

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)
        AppRepository.init()
    }
}