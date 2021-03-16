package com.example.android.myapplication.di

import com.example.android.myapplication.home_activity.HomeViewModelActivity
import com.example.android.myapplication.home_activity.list.vm.ListViewModel
import com.example.android.myapplication.utils.SharedTransactionVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { HomeViewModelActivity() }
    viewModel { ListViewModel(get()) }
    viewModel { SharedTransactionVM() }
}