plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = ConfigData.applicationId
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding {
        enable = true
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
}

dependencies {

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.jUnitExt)
    androidTestImplementation(Dependencies.espressoCore)

    implementation(Dependencies.coroutines)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleData)
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.splash)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterGson)
    implementation(Dependencies.httpLoggingInterceptor)
    implementation(Dependencies.roomRuntime)
    annotationProcessor(Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.coreTesting)
    testImplementation(Dependencies.coroutinesTest)
}

kapt {
    correctErrorTypes = true
}