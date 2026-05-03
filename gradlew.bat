@ECHO OFF
SETLOCAL

SET APP_HOME=%~dp0
SET WRAPPER_JAR=%APP_HOME%gradle\wrapper\gradle-wrapper.jar

IF EXIST "%WRAPPER_JAR%" (
  java -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
  EXIT /B %ERRORLEVEL%
)

WHERE gradle >NUL 2>NUL
IF %ERRORLEVEL% EQU 0 (
  ECHO gradle-wrapper.jar not found; using installed Gradle from PATH.
  gradle %*
  EXIT /B %ERRORLEVEL%
)

ECHO ERROR: gradle-wrapper.jar is missing and Gradle is not installed in PATH.
EXIT /B 1
