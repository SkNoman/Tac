plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.etac.service"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.etac.service"
        minSdk = 21
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
        viewBinding = true
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("junit:junit:4.13.2")
    //NAVIGATION COMPONENT
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")
    //LOTTIE ANIMATION
    implementation ("com.airbnb.android:lottie:3.4.2")
    //OTP LAYOUT
    implementation ("io.github.chaosleung:pinview:1.4.4")
    //GLIDE
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    //View Pager
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    //Rounded Image
    implementation ("com.makeramen:roundedimageview:2.3.0")
    //Custom Bottom Navigation View
    implementation ("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")
    androidTestImplementation("junit:junit:4.12")


}