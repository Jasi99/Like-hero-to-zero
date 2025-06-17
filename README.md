# herolikezero
 Prototyp zur Anzeige und Bearbeitung von CO2-Emissionsdaten für die Fallstudie IPWA02-01

## Projektinhalt

- Öffentliche Webseite zur Anzeige von CO₂-Daten pro Land
- Login-Bereich für registrierte Wissenschaftler:innen
- Backend zur Eingabe und Korrektur von CO₂-Daten
- Persistente Speicherung mit JPA (H2-Datenbank)
- Webfrontend mit JSF (JavaServer Faces)
- Umsetzung im Rahmen der Fallstudie IPWA02-01 an der IU Internationale Hochschule

## Technologien

- Java EE (JSF, CDI, JPA)
- Apache Tomcat 10
- Maven
- H2-Datenbank
- Git & GitHub

## Voraussetzungen
Zur Ausführung der Anwendung werden folgende Komponenten benötigt:

- Java Development Kit (JDK)
Empfohlen: Java 22
Download: https://jdk.java.net/

- Apache Tomcat
Empfohlen: Version 9.0.80 oder höher
Download: https://tomcat.apache.org/download-90.cgi
Nach dem Entpacken wird der Server über startup.bat (Windows) bzw. startup.sh (Mac/Linux) gestartet.

- Apache Maven
Empfohlen: Version 3.8 oder höher
Download: https://maven.apache.org/download.cgi
   
- H2-Datenbank (integriert)
Es ist keine separate Installation notwendig.
Die Datenbank wird automatisch im Ordner ./data/ angelegt.

## Anwendung kompilieren und starten
1. WAR-Datei erzeugen (bereits erzeugt und im target Ordner abgelegt - herolikezero-1.1.war)
Im Hauptverzeichnis des Projekts:
mvn clean package

Die erzeugte WAR-Datei wird unter target/ abgelegt
2. Deployment auf Apache Tomcat
Die WAR-Datei in den Ordner webapps/ der Tomcat-Installation kopieren.
Tomcat über startup.bat (Windows) oder startup.sh (Mac/Linux) starten.
Die Anwendung wird beim Start automatisch entpackt und bereitgestellt.

3. Anwendung im Browser aufrufen
http://localhost:8080/herolikezero-1.1
Falls die WAR-Datei anders benannt wurde, muss der Pfad entsprechend angepasst werden.

## Zugangsdaten für Wissenschaftler
Benutzername: admin
Passwort: 1234

