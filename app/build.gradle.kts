import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.shoppieeclient"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shoppieeclient"
        minSdk = 29
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val localProperties = Properties()
    val localPropertiesFile = File(rootDir, "secret.properties")
    if(localPropertiesFile.exists() && localPropertiesFile.isFile) {
        localPropertiesFile.inputStream().use {
            localProperties.load(it)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "CLOUDINARY_API_KEY", localProperties.getProperty("CLOUDINARY_API_KEY"))
            buildConfigField("String", "CLOUDINARY_API_SECRET", localProperties.getProperty("CLOUDINARY_API_SECRET"))
            buildConfigField("String", "CLOUDINARY_CLOUD_NAME", localProperties.getProperty("CLOUDINARY_CLOUD_NAME"))
            buildConfigField("String", "UPLOAD_PRESET_CLOUDINARY", localProperties.getProperty("UPLOAD_PRESET_CLOUDINARY"))
            buildConfigField("String", "GOOGLE_CLIENT_ID", localProperties.getProperty("GOOGLE_CLIENT_ID"))
            buildConfigField("String", "FACEBOOK_APP_ID", localProperties.getProperty("FACEBOOK_APP_ID"))
            buildConfigField("String", "FACEBOOK_APP_SECRET", localProperties.getProperty("FACEBOOK_APP_SECRET"))
//            buildConfigField("String", "RAZORPAY_ID", localProperties.getProperty("RAZORPAY_ID"))
        }
        debug {
            buildConfigField("String", "CLOUDINARY_API_KEY", localProperties.getProperty("CLOUDINARY_API_KEY"))
            buildConfigField("String", "CLOUDINARY_API_SECRET", localProperties.getProperty("CLOUDINARY_API_SECRET"))
            buildConfigField("String", "CLOUDINARY_CLOUD_NAME", localProperties.getProperty("CLOUDINARY_CLOUD_NAME"))
            buildConfigField("String", "UPLOAD_PRESET_CLOUDINARY", localProperties.getProperty("UPLOAD_PRESET_CLOUDINARY"))
            buildConfigField("String", "GOOGLE_CLIENT_ID", localProperties.getProperty("GOOGLE_CLIENT_ID"))
            buildConfigField("String", "FACEBOOK_APP_ID", localProperties.getProperty("FACEBOOK_APP_ID"))
            buildConfigField("String", "FACEBOOK_APP_SECRET", localProperties.getProperty("FACEBOOK_APP_SECRET"))
//            buildConfigField("String", "RAZORPAY_ID", localProperties.getProperty("RAZORPAY_ID"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //Navigation compose
    implementation(libs.androidx.navigation.compose)
    //Splashscreen
    implementation(libs.androidx.core.splashscreen)
    //Koin
    implementation(libs.koin.androidx.compose)
    //Kotlin-x-serialization
    implementation(libs.kotlinx.serialization.json)
    //Coil
    implementation(libs.coil.compose)
    //DataStore Preferences
    implementation(libs.androidx.datastore.preferences)
    //Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.cio)
    //Lottie
    implementation(libs.lottie.compose)
    //Paging-3
    implementation(libs.androidx.paging.compose)
    //Cloudinary
    implementation(libs.cloudinary.cloudinary.android)
    // Room
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)

    //credential google
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    //Facebook sdk
    implementation(libs.facebook.login)
    //Razorpay
    implementation(libs.checkout)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}