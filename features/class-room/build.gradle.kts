plugins {
    kotlin("android")
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.class_room"
    buildFeatures {
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":features:navigate"))
    implementation(project(":shared"))
    implementation(project(":domain"))

    //KTX
    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Appcompat.androidAppcompat)
    implementation(Dev.Android.Fragment.androidxNavigationFragment)
    implementation(Dev.Android.Lifecycle.androidLifecycleViewModel)
    implementation(Dev.Android.Lifecycle.androidLifecycleLivedata)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Android.Ui.androidRecyclerview)
    implementation(Dev.Facebook.shimmer)
    implementation(Dev.Timetable.timetable)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

    implementation(Dev.Timber.timber)

}