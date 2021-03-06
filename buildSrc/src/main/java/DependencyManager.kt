import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.uiImplentation() {
    implementation(project(Modules.Data.DATABASE))
    AndroidX.getAll().forEach { implementation(it) }
    Coroutines.getAll().forEach { implementation(it) }
    Navigation.getAll().forEach { implementation(it) }
    Accompanist.getAll().forEach { implementation(it) }
    implementation(Coil.coil)
    implementation(Room.room)
    implementation(Room.roomKTX)
    implementation(Room.roomPaging)
    kapt(Room.roomCompiler)
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-compiler:2.42")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
}

fun DependencyHandler.domainImplentation() {
    implementation(Network.retrofitConvertor)
    // implementation(AndroidX.paging)
    Coroutines.getAll().forEach { implementation(it) }
    Network.getAll().forEach { implementation(it) }
}

fun DependencyHandler.domainNetworkImplentation() {
    Network.getAll().forEach { implementation(it) }
}
fun DependencyHandler.databaseImplementation() {
    implementation(Coil.coil)
    implementation(Room.room)
    implementation(Room.roomKTX)
    implementation(Room.roomPaging)
    kapt(Room.roomCompiler)

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
}

fun DependencyHandler.dataImplementation() {
    implementation(project(Modules.Data.DATABASE))
    implementation(Room.room)
    implementation(Room.roomKTX)
    implementation(Room.roomPaging)
    Network.getAll().forEach { implementation(it) }
    implementation(AndroidX.pagingCompose)
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
}

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)
