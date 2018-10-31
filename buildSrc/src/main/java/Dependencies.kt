import org.gradle.api.JavaVersion

object General {
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
}

object Version {
    // project
    const val minSdk = 19
    const val targetSdk = 28
    const val buildToolsVersion = "28.0.3"
    const val compileSdkVersion = 28
    const val androidGradle = "3.2.1"

    // kotlin
    const val kotlin = "1.3.0" // https://kotlinlang.org/

    // android
    const val androidX = "1.0.0"
    const val androidLifecycle = "2.0.0"

    //rxExtensions
    const val rxJava = "2.2.2"    //https://github.com/ReactiveX/RxJava
    const val rxRelay = "2.1.0"   //https://github.com/JakeWharton/RxRelay
    const val rxAndroid = "2.1.0" //https://github.com/ReactiveX/RxAndroid

    // tests
    const val jUnit = "4.12"
    const val androidTestRunner = "1.1.0"
    const val espresso = "3.1.0"
}

object Deps {

    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

    // Android
    const val appCompat = "androidx.appcompat:appcompat:${Version.androidX}"
    const val androidLifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.androidLifecycle}"
    const val androidLifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Version.androidLifecycle}"

    // RxExtension
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
    const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:${Version.rxRelay}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"

    // Tests
    const val jUnit = "junit:junit:${Version.jUnit}"
    const val androidTestRunner = "androidx.test:runner:${Version.androidTestRunner}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
}
