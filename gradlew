#!/usr/bin/env sh

# MyNotes robust Gradle launcher.
# If gradle-wrapper.jar exists, use the standard wrapper.
# If it is missing, fall back to the Gradle installed by GitHub Actions setup-gradle.

APP_HOME=$(cd "$(dirname "$0")"; pwd)
WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

if [ -f "$WRAPPER_JAR" ]; then
  exec java -classpath "$WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
fi

if command -v gradle >/dev/null 2>&1; then
  echo "gradle-wrapper.jar not found; using installed Gradle from PATH."
  exec gradle "$@"
fi

echo "ERROR: gradle-wrapper.jar is missing and Gradle is not installed in PATH." >&2
echo "On GitHub Actions, use gradle/actions/setup-gradle before running ./gradlew." >&2
exit 1
