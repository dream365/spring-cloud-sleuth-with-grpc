import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version Ver.kotlin
    kotlin("kapt") version Ver.kotlin
    id(Plugin.kotlinSpring) version Ver.kotlin
    id(Plugin.protobuf) version Ver.protobufPlugin
    id(Plugin.springBoot) version Ver.springBoot apply false
    id(Plugin.ktLint) version Ver.ktlintPlugin apply false
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin(Plugin.idea)
        plugin(Plugin.kotlin)
        plugin(Plugin.kotlinKapt)
        plugin(Plugin.kotlinSpring)
        plugin(Plugin.ktLint)
        plugin(Plugin.protobuf)
        plugin(Plugin.springDependencyManagement)
    }

    group = "com.dream365"
    version = "1.0-SNAPSHOT"

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(grpc("protobuf"))
        implementation(grpcSpringBootStarter())
        implementation(protobufJava())
        implementation(reactorGrpcStub())

        implementation(springBoot("starter-webflux"))

        protobuf(files("$rootDir/protobuf"))
    }

    protobuf {
        protoc {
            artifact = "com.google.protobuf:protoc:${Ver.protobuf}"
        }
        plugins {
            id("grpc") {
                artifact = "io.grpc:protoc-gen-grpc-java:${Ver.grpc}"
            }
            id("reactor") {
                artifact = "com.salesforce.servicelibs:reactor-grpc:${Ver.reactorGrpc}"
            }
        }
        generateProtoTasks {
            ofSourceSet("main").forEach {
                it.plugins {
                    // Apply the "grpc" and "reactor" plugins whose specs are defined above, without options.
                    id("grpc")
                    id("reactor")
                }
            }
        }
    }
}