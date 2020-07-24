package com.kun.easytra

import android.app.Application
import android.content.Context
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.tradata.repository.TraRepository
import org.koin.core.KoinComponent

interface ServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance: ServiceLocator? = null
        fun instance(context: Context): ServiceLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = DefaultServiceLocator(context.applicationContext as Application)
                }
                return instance!!
            }
        }

        /**
         * Allows tests to replace the default implementations.
         */
        // @VisibleForTesting
        fun swap(locator: ServiceLocator) {
            instance = locator
        }
    }

    // `type` can be defined as a param for getting different repository at the corresponding scenario
    // e.g. getRepository(type: RepoType)
    fun getRepository(): ITraRepository
}

open class DefaultServiceLocator(val app: Application) : ServiceLocator, KoinComponent {
    override fun getRepository(): ITraRepository {
        return TraRepository()
    }

}