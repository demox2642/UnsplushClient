plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    namespace = "com.example.home"
}

dependencies {
    api(project(Modules.Presentation.BASE_UI))
    api(project(Modules.Domain.HOME))
    api(project(Modules.Data.HOME))
    uiImplentation()
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.gms:play-services-basement:18.0.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(AndroidX.composeUiToolingPreview)
    debugImplementation(AndroidX.composeUiTooling)
    implementation(AndroidX.composeUi)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeMaterial3)
    implementation(Accompanist.viewPager)
    implementation(AndroidX.composeMaterialIconsExtended)
    implementation(AndroidX.composeConstraint)
    implementation(Navigation.composeNavigation)
    implementation(Navigation.hiltNavigation)

    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-compiler:2.42")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //  kapt ("com.google.dagger:hilt-android-compiler:2.41")
    //  kapt ("androidx.hilt:hilt-compiler:1.0.0")

    implementation("androidx.browser:browser:1.4.0")

//    implementation(AndroidX.composeUiToolingPreview)
//    debugImplementation(AndroidX.composeUiTooling)
//    implementation(AndroidX.composeUi)
//    implementation(AndroidX.composeMaterial)
//    implementation(AndroidX.composeMaterial3)
//    implementation(AndroidX.composeMaterialIconsExtended)
}
