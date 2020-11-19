plugins {
  id("java-library")
  id("kotlin")
}

repositories {
  google()
  jcenter()
}

dependencies {
  compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  // The Lint version is strongly tied to the Android Gradle Plugin (AGP) version
  // Add 23 to the major version number of AGP (x in x.y.z)
  // The project is currently dependent on AGP v4.1.1
  val lintVersion = "27.1.1"

  // Lint
  compileOnly("com.android.tools.lint:lint-api:${lintVersion}")
  compileOnly("com.android.tools.lint:lint-checks:${lintVersion}")

  // Lint testing
  testImplementation("com.android.tools.lint:lint:${lintVersion}")
  testImplementation("com.android.tools.lint:lint-tests:${lintVersion}")
  testImplementation("junit:junit:4.13.1")
}

tasks {
  jar {
    manifest {
      attributes(
          mapOf("Lint-Registry-v2" to "dev.zarah.lint.checks.IssueRegistry")
      )
    }
  }
}