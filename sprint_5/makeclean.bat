rem echo off

set SPRINTDIR=%~dp0

set TECHDOCDIR=documentation\doc_tech\
set USERDOCDIR=documentation\doc_user\
set TESTDOCDIR=documentation\doc_test\

set JAVABINDIR=OPTI/bin/

@echo ///////////////////////////////////////////////////////

@echo // Cleaning generated technical documentation files

@echo ///////////////////////////////////////////////////////

rd %TECHDOCDIR%javadoc /s /q
del %TECHDOCDIR%doc\docTechnique.html

@echo ///////////////////////////////////////////////////////

@echo // Cleaning generated user documentation files

@echo ///////////////////////////////////////////////////////

del %USERDOCDIR%doc\docUtilisateur.html

@echo ///////////////////////////////////////////////////////

@echo // Cleaning generated test documentation files

@echo ///////////////////////////////////////////////////////

del %TESTDOCDIR%doc\doc-test.html

@echo ///////////////////////////////////////////////////////

@echo // Cleaning compiled java classes

@echo ///////////////////////////////////////////////////////

cd %JAVABINDIR%
rd data library gui /s /q
cd %SPRINTDIR%

@echo ///////////////////////////////////////////////////////

@echo // Done cleaning

@echo ///////////////////////////////////////////////////////

pause