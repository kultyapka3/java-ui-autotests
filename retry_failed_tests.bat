@echo off
setlocal

set FAILED_XML=target\surefire-reports\testng-failed.xml

if not exist "%FAILED_XML%" (
    echo There are no failed tests from the previous run
    pause
    exit /b 0
)

echo Restarting only the failed tests...

mkdir allure-results\retry 2>nul

mvn test -Dsurefire.suiteXmlFiles=%FAILED_XML% -Dallure.results.directory=allure-results/retry

echo To open the report for only the restarted tests, run:
echo   allure serve allure-results/retry
