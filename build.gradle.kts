// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
////    ext.kotlin_version = '1.7.10'
    repositories {
        google()
        jcenter()

    }
//    dependencies {
//        classpath("com.android.tools.build:gradle:7.2.2")
//        classpath("com.google.gms:google-services:4.3.15")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
//        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.10")
//
////        def nav_version = "2.5.3"
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
//
//        // NOTE: Do not place your application dependencies here; they belong
//        // in the individual module build.gradle files
//    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

}
//allprojects {
//    repositories {
//        mavenCentral()
//        google()
//        jcenter()
//        maven("https://jitpack.io")
//        maven("https://mobile-sdk.jumio.com")
//        maven("https://dl.bintray.com/rudderstack/rudderstack")
//        maven("https://nexus3-public.monetplus.cz/repository/ahead-talsec-free-rasp")
//        maven("https://developer.huawei.com/repo/")
//        maven("https://sdk-download.airbridge.io/maven")
//    }
//}

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}



