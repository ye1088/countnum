@echo off

set /p directory=请拖入要打包的文件
java -jar "%~dp0\countNum.jar"  "%directory%"
:isCon
echo.
echo hehaloxi
pause