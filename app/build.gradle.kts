plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // navigation-compose
    id("androidx.navigation.safeargs.kotlin")

    // ksp
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.barril.pokedexapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.barril.pokedexapp"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.compose.foundation:foundation:1.7.0")

    // navigation (futuro)
    val nav_version = "2.8.0"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")
    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // layout
//    implementation(libs.androidx.constraintlayout)
//    //// to use constraintlyout in compose
//    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-beta01")

    //  images
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    // paging
    val paging_version = "3.3.2"

    implementation("androidx.paging:paging-runtime:$paging_version")
    ////  optional - Jetpack Compose integration
    implementation("androidx.paging:paging-compose:$paging_version")

    // api
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation(libs.gson)

    // persistance
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    //// To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    //// optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    //// optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    //// optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")

}
