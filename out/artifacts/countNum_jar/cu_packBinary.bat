@echo off

set /p directory=请拖入要打包的文件
java -jar "%~dp0\cu_Packbin.jar"  "%directory%"
:isCon
echo.

pause