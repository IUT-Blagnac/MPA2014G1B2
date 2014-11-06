rem echo off

set SPRINTDIR=%~dp0

set OPTIDIR=./OPTI/

set SRCDIR=./OPTI/src/

set BINDIR=./OPTI/bin/

set DOCDIR=./OPTI/doc/

set RUNTEST=1

@echo ///////////////////////////////////////////////////////

@echo // COMPILATION de la librairie

@echo ///////////////////////////////////////////////////////

javac -cp .;%SRCDIR%;tools/junit.jar -d %BINDIR% %SRCDIR%library/*

@echo ///////////////////////////////////////////////////////

@echo // COMPILATION de l'IHM

@echo ///////////////////////////////////////////////////////

javac -d %BINDIR% -cp %SRCDIR% %SRCDIR%gui/*.java

@echo ///////////////////////////////////////////////////////

@echo // COMPILATION de la Data

@echo ///////////////////////////////////////////////////////

javac -d %BINDIR% -cp %SRCDIR% %SRCDIR%data/*.java

@echo ///////////////////////////////////////////////////////

@echo // EXECUTION des tests

@echo ///////////////////////////////////////////////////////

if "%RUNTEST%"=="1" (

 cd %OPTIDIR%

 java -cp bin;../tools/junit.jar library.OptiParserTest

 cd %SPRINTDIR%

)

@echo ///////////////////////////////////////////////////////

@echo // GENERATION de la Javadoc

@echo ///////////////////////////////////////////////////////

javadoc -sourcepath OPTI/src -d ./documentation/doc_tech/javadoc -subpackages data:library:gui -classpath tools/junit.jar