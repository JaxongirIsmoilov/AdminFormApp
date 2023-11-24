import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.gms.google-services")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "uz.gita.jaxongir.adminformapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.gita.jaxongir.adminformapp"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

//    firebase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))

//    hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

//  Room
    implementation ("androidx.room:room-runtime:2.6.0")
    annotationProcessor ("androidx.room:room-compiler:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")

    //  Voyager
    implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc05")
    implementation("cafe.adriel.voyager:voyager-androidx:1.0.0-rc05")
    implementation("cafe.adriel.voyager:voyager-hilt:1.0.0-rc05")
    implementation("cafe.adriel.voyager:voyager-transitions:1.0.0-rc05")

//    lottie
    implementation ("com.airbnb.android:lottie-compose:6.1.0")

//    lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-alpha02")

//    Icons
    implementation ("androidx.compose.material3:material3")
    implementation ("androidx.compose.material:material")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")

//  Gson
    implementation("com.google.code.gson:gson:2.10.1")

    //  Time
    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.4")


    // MVI orbit
    implementation ("org.orbit-mvi:orbit-viewmodel:4.6.1")
    implementation ("org.orbit-mvi:orbit-compose:4.6.1")
}

kapt{
    correctErrorTypes = true
}