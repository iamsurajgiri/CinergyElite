package tuna.cinergyelite.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tuna.cinergyelite.viewModels.EscapeRoomViewModel
import tuna.cinergyelite.viewModels.LoginViewModel

val appModule = module {
    viewModel{LoginViewModel(get())}
    viewModel{EscapeRoomViewModel(get())}
}