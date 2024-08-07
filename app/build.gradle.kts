plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")

//    id("kotlin-kapt")
}

android {
    namespace = "com.example.userinformation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.userinformation"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

//        multiDexEnabled =true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.animation.android)
    implementation(libs.firebase.messaging)
    implementation(libs.core.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.retrofit)
//    implementation (libs.countrycodepicker)
    implementation (libs.countrycodepicker)
    implementation (libs.ccp)
    implementation(libs.androidx.cardview)
    implementation (libs.androidx.viewpager2)
    implementation (libs.ambilwarna)
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.converter.gson)
   implementation(libs.masked.edittext)
    implementation (libs.glide.v4110)
    implementation(libs.glide)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.preference)
    implementation(libs.glide)
//    implementation (libs.smallbang)

//    implementation (libs.monthandyearpicker)
    implementation (libs.shimmer)
    annotationProcessor (libs.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.android.stubutton)
//    implementation (libs.slidingbutton.v200)
    implementation("com.github.mhdmoh:swipe-button:1.0.3")
    implementation (libs.slidetoact)
//    implementation(libs.proswipebutton)
//    kapt ("androidx.room:room-compiler:2.5.1")

    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation(libs.okhttp)

}
