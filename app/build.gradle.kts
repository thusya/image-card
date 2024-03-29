@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.thusee.imagecard"
    compileSdk = libs.versions.android.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.thusee.imagecard"
        minSdk = libs.versions.android.min.sdk.get().toInt()
        targetSdk = libs.versions.android.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"${project.properties["baseUrl"]}\""
            )
        }
        release {
            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"${project.properties["baseUrl"]}\""
            )
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.kotlin.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    //Navigation
    implementation(libs.androidx.navigation)

    //Coil
    implementation(libs.coil.compose)

    //Network
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.moshi.kotlin)
    implementation(libs.squareup.moshi.kotlin.codegen)
    implementation(libs.converter.moshi)
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.okhttp.logging.interceptor)

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    //Timber for logging
    implementation(libs.jakewharton.timber)

    // JUnit5
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.parametrized)
    androidTestImplementation(libs.junit.jupiter)

    //Coroutines Test
    testImplementation(libs.kotlinx.coroutines.test)

    // mockk
    testImplementation(libs.mockk)
}
