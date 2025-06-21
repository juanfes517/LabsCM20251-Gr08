package co.edu.udea.compumovil.gr08_20251.lab2

import android.app.Application
import co.edu.udea.compumovil.gr08_20251.lab2.worker.data.AppContainer
import co.edu.udea.compumovil.gr08_20251.lab2.worker.data.DefaultAppContainer

class MainApplication : Application()  {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}