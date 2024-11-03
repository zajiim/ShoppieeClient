package com.example.shoppieeclient.presentation.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewmodelModule)
}