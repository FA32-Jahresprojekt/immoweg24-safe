# ImmoWeg24

Jahresprojekt der Gruppe A, FA32. Mietobjektverwaltung im Rahmen des 3. Ausbildungsjahres.

## Requirements

- [JDK](https://www.oracle.com/java/technologies/downloads/)
- [Tomcat 10](https://tomcat.apache.org/download-10.cgi)
- Benutze [NetBeans](https://netbeans.apache.org/front/main/index.html) als deine IDE ([IntelliJ Workaround](#intellij-workaround))

## Starting

> Du musst deine NetBeans Tomcat Verbindung konfiguriert haben, andersfall startet das Projekt nicht.
> Du solltest beim ersten bauen eine stabile Internetverbindung aufweisen, andernfalls können die Dependencies nicht aufgelöst werden.

1. Achte darauf, dass du XAMPP offen hast, eine MySQL hinter Port 3306 läuft und die Datenbank "hhbk" existiert.
2. Innerhalb von NetBeans drücke den grünen "Play"-Knopf um die Applikation zu starten.
3. Du wirst aufgefordert deine Tomcat Daten einzutragen, tue dies.
4. Sobald sich der Browser öffnet solltest du die Applikation sehen. 🎉

## IntelliJ Workaround

1. Öffne `Run` > `Configurations`
2. Erstelle einen `Local Tomcat Server` und stelle unter der Option Application Server deinen Pfad zu Tomcat **10**
3. Füge unter dem Tab `Deployment` ein Artifact hinzu und wähle die `war`

> **Troubleshooting:** Falls das nicht funktioniert, schaue ob unter `Project Structure` > `Modules` eine Option names "web" existiert, andernfalls bitte erstellen.


## Lizenz

Dieses Projekt wird nicht für öffentliche Zwecke genutzt und unterliegt der [GNU GENERAL PUBLIC LICENSE](LICENSE).

