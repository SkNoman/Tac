import com.android.ddmlib.Log
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")

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


        buildConfigField("String","API_KEY", getLocalProperty("api_key"))
        buildConfigField("String","SECRET_KEY", getLocalProperty("secret_key"))
        buildConfigField("String","BASE_URL", getLocalProperty("base_url"))

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
        buildConfig = true
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
    implementation("com.google.android.material:material:1.11.0")
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
    androidTestImplementation("junit:junit:4.13.2")
    //retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    //gson
    implementation ("com.google.code.gson:gson:2.10")
    //OKHttpInterceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    //Hilt
    implementation ("com.google.dagger:hilt-android:2.49")
    kapt ("com.google.dagger:hilt-compiler:2.49")


}

fun getLocalProperty(key: String, file: String = "local.properties"): String {
    val properties = Properties()
    val localProperties = File(file)
    if (key != null){
        Log.d("key", key)
    }else{
        Log.d("key","key is null:(")
    }
    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties),Charsets.UTF_8).use { reader->
            properties.load(reader)
        }
    } else error("File not found")
    return properties.getProperty("api_key")
}


/*val getBaseUrl(){
*//*    //Properties properties = new Properties()
    val pro
    properties.load(project.rootProject.file('local.properties').newDataInputStream())

    String baseUrl = properties.getProperty("base_url")
    if (baseUrl == null)
        throw new FileNotFoundException("Add 'base_url' field at local.properties file.")

    return baseUrl*//*
}

val getSecretKey() {
    Properties properties = new Properties()
    properties.load(project.rootProject.file('local.properties').newDataInputStream())

    String key = properties.getProperty("secret_key")
    if (key == null)
        throw new FileNotFoundException("No found")

    return key
}*/

/*val getAPIKey() {
    //Properties properties = new Properties()
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    return properties.getProperty("api_key") ?: throw FileNotFoundException("File Not Found")
}*/
