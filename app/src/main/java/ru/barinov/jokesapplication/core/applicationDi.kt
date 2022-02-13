package ru.barinov.jokesapplication

import androidx.room.Room
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.barinov.jokesapplication.data.localDataBase.*
import ru.barinov.jokesapplication.data.localRepository.*
import ru.barinov.jokesapplication.data.remoteDataBaseLoader.ChuckNorrisAPI
import ru.barinov.jokesapplication.data.remoteRepository.*
import ru.barinov.jokesapplication.ui.JokesViewModel

private const val BASE_URL = "https://api.icndb.com/"

val appModule = module {

    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    single<DataBase> {
        Room.databaseBuilder(androidApplication(), DataBase::class.java, "local_database").build()
    }

    single<ChuckNorrisAPI>{
        get<Retrofit>().create(ChuckNorrisAPI::class.java)
    }

    single<ChuckNorrisApiServiceImpl>{
        ChuckNorrisApiServiceImpl(get())
    }

    single<FavoriteJokesDAO> {
        get<DataBase>().favoriteJokesDao()
    }

    single<LocalRepositoryImpl>{
        LocalRepositoryImpl(get())
    }

    viewModel<JokesViewModel>{
        JokesViewModel(get<LocalRepositoryImpl>() as LocalRepository  , get<ChuckNorrisApiServiceImpl>() as ChuckNorrisApiService)
    }
}