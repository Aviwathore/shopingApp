plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

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
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
//    implementation (libs.retrofit)
    implementation(libs.retrofit)
//    implementation (libs.converter.gson)
//    implementation (libs.gson)

    //noinspection UseTomlInstead
//    implementation ("ru.egslava:MaskedEditText:1.0.5"

//    implementation (libs.MaskedEditText)
//    implementation(libs.maskedEditText)
//    implementation(libs.masked.edittext)
    //noinspection UseTomlInstead
//    implementation("com.github.pinball83:masked-edittext:1.0.5")
//    implementation(libs.pinball83.masked.edittext)
//    implementation(libs.pinball83.masked.edittext)
//    implementation(libs.material.v150)
//    implementation (libs.countrycodepicker)
    implementation (libs.countrycodepicker)
//    implementation (libs.countrycodepicker)
    implementation (libs.ccp)
//    implementation(libs.maskedEditText)
//    implementation (libs.masked.edittext)
//    implementation (libs.multidex)
    implementation(libs.androidx.cardview)
    implementation (libs.androidx.viewpager2)
    implementation (libs.ambilwarna)

    implementation(libs.converter.gson)
   implementation(libs.masked.edittext)
    implementation (libs.glide.v4110)
    implementation(libs.glide)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
