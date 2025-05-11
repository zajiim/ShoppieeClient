package com.example.shoppieeclient.utils

import com.example.shoppieeclient.BuildConfig

object Constants {
    const val DATASTORE_NAME = "shoppie_datastore"
    const val DATASTORE_ONBOARDING_KEY = "shoppie_onboarding_key"
    const val DATASTORE_TOKEN_KEY = "shoppie_token_key"
    const val SHOPPIEE_URL = "http://192.168.1.2:3000/api"
//    const val SHOPPIEE_URL = "http://192.168.87.223:3000/api"
    const val USER_NAME = "user_name"
    const val USER_IMAGE = "user_image"
    const val PER_PAGE_ITEMS = 10
    const val INITIAL_PAGE_INDEX = 1
//    const val SHOPPIEE_URL = "http://192.168.76.223:3000/api"
//    const val SHOPPIEE_URL = "http://192.168.101.223:3000/api"

    const val UPLOAD_PRESET_CLOUDINARY = BuildConfig.UPLOAD_PRESET_CLOUDINARY
    const val PROFILE_DATA_TABLE = "profile_data_table"
    const val ADDRESS_DATA_TABLE = "address_data_table"
    const val PAYMENT_DATA_TABLE = "payment_data_table"
    const val SHOPPIEE_DB = "shoppiee_db"
}