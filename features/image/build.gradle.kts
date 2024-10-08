plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.image"
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":features:core-ui"))
    implementation(project(":features:navigate"))

    //KTX
    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Ui.androidMaterial)
    implementation(Dev.Ui.AndroidSlidingUpPanel)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)


    implementation(Dev.Glide.glide)
    implementation(Dev.Glide.glide_okhttp3)
    kapt(Dev.Glide.glide_compiler)


    implementation(Dev.Timber.timber)
}