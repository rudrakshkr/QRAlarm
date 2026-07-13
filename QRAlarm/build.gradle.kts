// Top-level build file. No dependencies are declared here directly - each
// plugin version is pinned once in gradle/libs.versions.toml and then applied
// (but not yet "activated") in every module that needs it. The :app module
// is where these plugins actually get switched on.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
}
