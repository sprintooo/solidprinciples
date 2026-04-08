#!/bin/bash

# Set JAVA_HOME to Java 25 (required for Gradle 9.3.0)
export JAVA_HOME=$(/usr/libexec/java_home -v 25)

# Run the application
./gradlew run
