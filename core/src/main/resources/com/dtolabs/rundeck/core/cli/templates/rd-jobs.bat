:: Copyright 2010 DTO Labs, Inc. (http://dtolabs.com)
::
:: Licensed under the Apache License, Version 2.0 (the "License");
:: you may not use this file except in compliance with the License.
:: You may obtain a copy of the License at
::
::     http://www.apache.org/licenses/LICENSE-2.0
::
:: Unless required by applicable law or agreed to in writing, software
:: distributed under the License is distributed on an "AS IS" BASIS,
:: WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
:: See the License for the specific language governing permissions and
:: limitations under the License.
:: $RCSfile$
::
:: $Revision: 1084 $
::
@ECHO off
setlocal
GOTO:Start

:Start

IF NOT DEFINED RDECK_BASE (
   ECHO RDECK_BASE not set
   GOTO:EOF
)


IF "%JAVA_HOME%" =="" (
   echo JAVA_HOME not set
   GOTO:EOF
)
IF NOT EXIST "%JAVA_HOME%\bin\java.exe" (
   ECHO JAVA_HOME not set or set incorrectly
   GOTO:EOF
)

set LIBDIR=%RDECK_BASE%\tools\lib
set _CLASSPATH=%CLASSPATH%

set CLASSPATH=%1
for %%i in ( %1\*.jar ) do call buildclasspath_append.cmd %%~fsi

if "%_CLASSPATH%" == "" goto END
set CLASSPATH=%_CLASSPATH%;%CLASSPATH%

:END
"%RDECK_BASE%\classes;%ANT_HOME%\lib\ant.jar;%ANT_HOME%\lib\ant-launcher.jar;%ANT_HOME%\lib\regexp-1.5.jar;%ANT_HOME%\lib\ant-apache-regexp.jar"
set Path=%JAVA_HOME%\bin:%ANT_HOME%\bin:%Path%

CALL %JAVA_HOME%\bin\java "-Dant.home=%ANT_HOME%" ^
                          "-Duser.java_home=%JAVA_HOME%" ^
                          "-Drdeck.base=%RDECK_BASE%" ^
                          %RDECK_SSL_OPTS% ^
                          -cp  ^
                          com.dtolabs.rundeck.core.cli.ctljobs.JobsTool %*

IF NOT "%ERRORLEVEL%"=="0" GOTO:EXITSetup



:EXITSetup

EXIT /B %ERRORLEVEL%



