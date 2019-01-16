/**
 * Created by vincent on 13/2/18.
 */
object BuildConfig {
  const val compileSdk = 28
  const val minSdk = 16
  const val targetSdk = 28

  private const val versionMajor = 1
  private const val versionMinor = 0
  private const val versionPatch = 0
  private const val versionBuild = 0

  const val versionName =
    versionMajor.toString() + "." + versionMinor.toString() + "." + versionPatch.toString()
  const val versionCode =
    versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionBuild

}

object CommonLibs {
  const val android_gradle_plugin = "com.android.tools.build:gradle:3.2.1"

  const val dexter = "com.karumi:dexter:5.0.0"
  const val phrase = "com.squareup.phrase:phrase:1.1.0"
  const val sonar = "com.facebook.sonar:sonar:0.0.1"
  const val timber = "com.jakewharton.timber:timber:4.7.0"
  const val junit = "junit:junit:4.12"
  const val javaxInject = "javax.inject:javax.inject:1"
  const val rabbkt = "com.aungkyawpaing.rabbkt:rabbkt:1.0.1"
}

//region AndroidX
object AndroidXAnnotations {
  const val annotations = "androidx.annotation:annotation:1.0.1"
}

object AndroidXAppCompat {
  const val app_compat = "androidx.appcompat:appcompat:1.0.2"
}

object AndroidXRecyclerView {
  private const val version = "1.0.0"
  const val recycler_view = "androidx.recyclerview:recyclerview:$version"
  const val selection = "androidx.recyclerview:recyclerview-selection:$version"
}

object AndroidXCardView {
  const val card_view = "androidx.cardview:cardview:1.0.0"
}

object AndroidXConstraintLayout {
  private const val version = "1.1.3"
  const val constraint_layout = "com.android.support.constraint:constraint-layout:$version"
}

object AndroidXViewPager {
  const val view_pager = "androidx.viewpager:viewpager:1.0.0"
}

object AndroidXLegacy {
  private const val version = "1.0.0"
  const val support_v4 = "androidx.legacy:legacy-support-v4:$version"
}

object AndroidXSqlite {
  private const val version = "2.0.0-rc01"
  const val sqlite = "androidx.sqlite:sqlite:$version"
  const val sqlite_framework = "androidx.sqlite:sqlite-framework:$version"
  const val sqlite_ktx = "androidx.sqlite:sqlite-ktx:$version"
}

object AndroidArchLifeCycle {
  private const val version = "2.0.0"
  const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$version"
  const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:$version"
}

object AndroidArchCore {
  private const val version = "1.1.1"
  const val test = "android.arch.core:core-testing:$version"
}

object AndroidArchWork {
  private const val version = "1.0.0-alpha12"
  const val work_runtime = "android.arch.work:work-runtime:$version"
  const val work_runtime_ktx = "android.arch.work:work-runtime-ktx:$version"
}

object AndroidArchNavigation {
  private const val version = "1.0.0-alpha08"
  const val common = "android.arch.navigation:navigation-common:$version"
  const val common_ktx = "android.arch.navigation:navigation-common-ktx:$version"
  const val fragment = "android.arch.navigation:navigation-fragment:$version"
  const val fragment_ktx = "android.arch.navigation:navigation-fragment-ktx:$version"
  const val runtime = "android.arch.navigation:navigation-runtime:$version"
  const val runtime_ktx = "android.arch.navigation:navigation-runtime-ktx:$version"
  const val safe_args_generator = "android.arch.navigation:navigation-safe-args-generator:$version"
  const val safe_args_plugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:$version"
  const val testing = "android.arch.navigation:navigation-testing:$version"
  const val testing_ktx = "android.arch.navigation:navigation-testing-ktx:$version"
  const val ui = "android.arch.navigation:navigation-ui:$version"
  const val ui_ktx = "android.arch.navigation:navigation-ui-ktx:$version"
}

object AndroidXCore {
  private const val version = "1.0.1"
  const val core = "androidx.core:core:$version"
  const val core_kt = "androidx.core:core-ktx:$version"
}

object AndroidXFragment {
  private const val version = "1.1.0-alpha02"

  const val fragment = "androidx.fragment:fragment:$version"
  const val fragment_ktx = "androidx.fragment:fragment-ktx:$version"
  const val fragment_testing = "androidx.fragment:fragment-testing:$version"
}

object AndroidXTest {
  private const val version = "1.1.1-beta01"
  const val core = "androidx.test:core:$version"
  const val core_ktx = "androidx.test:core-ktx:1.1.0-beta01"
  const val runner = "androidx.test:runner:$version"
  const val rules = "androidx.test:rules:$version"
  const val roboelectric = "org.robolectric:robolectric:4.0.2"
}

object AndroidXTestExt {
  private const val version = "1.1.0-beta01"

  const val junit = "androidx.test.ext:junit:$version"
  const val junit_ktx = "androidx.test.ext:junit-ktx:$version"
  const val truth = "androidx.test.ext:truth:$version"
}

object AndroidXEspresso {
  private const val version = "3.1.0"
  const val core = "androidx.test.espresso:espresso-core:$version"
  const val contrib = "androidx.test.espresso:espresso-contrib:$version"
  const val intents = "androidx.test.espresso:espresso-intents:$version"
  const val idling_resource = "androidx.test.espresso:espresso-idling-resource:$version"
  const val idling_concurrent = "androidx.test.espresso.idling:idling-concurrent:$version"
  const val rx_idler = "com.squareup.rx.idler:rx2-idler:0.9.0"
}

//endregion

object Material {
  const val material = "com.google.android.material:material:1.0.0"
}

object Dagger {
  private const val version = "2.19"

  const val core = "com.google.dagger:dagger:$version"
  const val compiler = "com.google.dagger:dagger-compiler:$version"
  const val android_core = "com.google.dagger:dagger-android:$version"
  const val android_support = "com.google.dagger:dagger-android-support:$version"
  const val android_processor = "com.google.dagger:dagger-android-processor:$version"
}

object EverNoteJob {
  private const val version = "1.2.6"

  const val core = "com.evernote:android-job:$version"
}

object Firebase {
  private const val version = "16.0.6"

  const val auth = "com.google.firebase:firebase-auth:$version"
  const val core = "com.google.firebase:firebase-core:$version"
  const val db = "com.google.firebase:firebase-database:$version"
  const val messaging = "com.google.firebase:firebase-messaging:17.3.4"
  const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.3"
  const val fabric_plugin = "io.fabric.tools:gradle:1.26.1"
}

object FragmentTestRule {
  private const val version = "1.1.0"

  const val android = "com.21buttons:fragment-test-rule:$version"
  const val extra = "com.21buttons:fragment-test-rule-extras:$version"
}

object Glide {
  private const val version = "4.7.1"
  const val runtime = "com.github.bumptech.glide:glide:$version"
  const val compiler = "com.github.bumptech.glide:compiler:$version"
  const val transformations = "jp.wasabeef:glide-transformations:3.0.1"
}

object GoogleService {
  const val ads = "com.google.android.gms:play-services-ads:17.1.2"
  const val consent = "com.google.android.ads.consent:consent-library:1.0.6"
  const val gms = "com.google.gms:google-services:4.2.0"
}

object Kotlin {
  private const val version = "1.3.11"

  const val stdblib_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
  const val gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
}

object Mockito {
  private const val version = "2.23.4"

  const val core = "org.mockito:mockito-core:$version"
  const val android = "org.mockito:mockito-android:$version"
  const val inline = "org.mockito:mockito-inline:$version"
  const val kotlin = "com.nhaarman:mockito-kotlin:1.5.0"
}

object LeakCanary {
  private const val version = "1.6.1"

  const val core = "com.squareup.leakcanary:leakcanary-android:$version"
  const val no_op = "com.squareup.leakcanary:leakcanary-android-no-op:$version"
  const val support_fragment = "com.squareup.leakcanary:leakcanary-support-fragment:$version"
}

object Moshi {
  private const val version = "1.6.0"

  const val core = "com.squareup.moshi:moshi:$version"
  const val code_gen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
}

object OkHttp {
  private const val version = "3.11.0"

  const val client = "com.squareup.okhttp3:okhttp:$version"
  const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
}

object ReactiveX {
  const val rxjava = "io.reactivex.rxjava2:rxjava:2.1.12"
  const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.0"
  const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.2.0"
}

object RxBinding {
  private const val version = "3.0.0-alpha1"

  const val platform = "com.jakewharton.rxbinding3:rxbinding:$version"
}

object Retrofit {
  private const val version = "2.4.0"

  const val core = "com.squareup.retrofit2:retrofit:$version"
  const val moshi_converter = "com.squareup.retrofit2:converter-moshi:$version"
}

object SqlDelight {
  private const val version = "1.0.0-rc4"

  const val gradle_plugin = "com.squareup.sqldelight:gradle-plugin:$version"
  const val android_driver = "com.squareup.sqldelight:android-driver:$version"
  const val runtime = "com.squareup.sqldelight:runtime-common::$version"
}

object Stetho {
  private const val version = "1.5.0"

  const val core = "com.facebook.stetho:stetho:$version"
  const val okhttp3 = "com.facebook.stetho:stetho-okhttp3:$version"
}

object Shimmer {
  const val reycler_view = "com.github.sharish:ShimmerRecyclerView:v1.3"
}

object ThreeTenBp {
  private const val version = "1.3.7"

  const val core = "org.threeten:threetenbp:$version"
  const val no_tz_db = "org.threeten:threetenbp:$version:no-tzdb"
  const val android = "com.jakewharton.threetenabp:threetenabp:1.1.0"
}

