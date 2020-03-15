package com.osama.movieshow

import android.app.Application
import com.osama.movieshow.di.databaseModule
import com.osama.movieshow.di.networkModule
import com.osama.movieshow.di.repositoryModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class MovieShowApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MovieShowApplication))
        importAll(databaseModule, networkModule, repositoryModule)
    }


}