set SPRINTDIR=%~dp0

set ASCIIDOCDIR=.\tools\asciidoc-8.6.7\

set PLANTUMLDIR=.\tools\

set DOCDIR=.\documentation\

set JAVADOCDIR=.\documentation\doc_tech\javadoc

set SRCDOCDIR=.\srcdoc\

@echo ///////////////////////////////////////////////////////

@echo // COMPILATION des documentations OPTI

@echo ///////////////////////////////////////////////////////

java -jar %PLANTUMLDIR%plantuml.jar -Tpng -o %SRCDOCDIR%images %SRCDOCDIR%diag0.puml

::javadoc -d %JAVADOCDIR% -sourcepath ./OPTI/src

python %ASCIIDOCDIR%asciidoc.py -a iconsdir=%ASCIIDOCDIR%images/icons -o %DOCDIR%OPTI/doc_user/docUtilisateur.html %SRCDOCDIR%OPTI/doc_user/srcdoc/doc.txt

python %ASCIIDOCDIR%asciidoc.py -a iconsdir=%ASCIIDOCDIR%images/icons -o %DOCDIR%OPTI/doc_tech/docTechnique.html %SRCDOCDIR%OPTI/doc_tech/srcdoc/doc.txt

REM java -jar %PLANTUMLDIR%plantuml.jar -Tpng -o %DOCDIR%images %SRCDOCDIR%diag0.puml

python %ASCIIDOCDIR%asciidoc.py -o %DOCDIR%OPTI/doc_test/doc-test.html %SRCDOCDIR%OPTI/doc_test/srcdoc/doc-test.txt

@echo ///////////////////////////////////////////////////////

@echo // COMPILATION des documentations OPTIweb

@echo ///////////////////////////////////////////////////////

python %ASCIIDOCDIR%asciidoc.py -o %DOCDIR%OPTIweb/docUtilisateur.html %SRCDOCDIR%OPTIweb/docUtilisateur.txt

python %ASCIIDOCDIR%asciidoc.py -o %DOCDIR%OPTIweb/docTechnique.html %SRCDOCDIR%OPTIweb/docTechnique.txt

python %ASCIIDOCDIR%asciidoc.py -a data-uri -a toc -b slidy -o %DOCDIR%presentationFinale.html %SRCDOCDIR%presentationFinale.txt