@echo off
setlocal

curl -s http://localhost:4444/status | findstr "message" > nul
if %errorlevel% neq 0 (
    echo Selenium Grid is not enabled. Please start selenium_grid.bat.
    exit /b 1
)

mkdir allure-results\grid 2>nul

mvn clean test -Dgrid.url=http://localhost:4444/wd/hub -Dallure.results.directory=allure-results/grid

echo The tests are running in parallel mode.
echo To view the report after completing the tests, run:
echo   allure serve allure-results/grid
