# video-portal-backend
[![Spotless Check](https://github.com/Doma1612/video-portal-backend/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/Doma1612/video-portal-backend/actions/workflows/ci.yml)

Diese Repo beinhaltet den Code für das Java Backend mitsamt maven.

## Formatierung des Java-Quellcodes mit Spotless

Bevor du deine Änderungen hochlädst, stelle sicher, dass du Spotless lokal ausführst, um deinen Java-Code zu formatieren. Spotless gewährleistet, dass der Code den definierten Code-Stilrichtlinien entspricht.

### Überprüfung des Code-Stils

Um zu überprüfen, ob dein Code den Stilrichtlinien entspricht, führe den folgenden Befehl im Projektverzeichnis aus:

Wichtig: IM ORDNER DES PARENTMODULS also in `video-portal-backend`

```sh
mvn spotless:check
```

Dieser Befehl analysiert den Code und meldet eventuelle Formatierungsverstöße, ohne die Dateien zu ändern.

### Anwendung des Code-Stils

Um den korrekten Code-Stil automatisch auf deine Dateien anzuwenden, führe den folgenden Befehl aus:
```sh
mvn spotless:apply
```


Dieser Befehl formatiert die Dateien, um den definierten Stilrichtlinien zu entsprechen.

Stelle sicher, dass du alle von `spotless:check` gemeldeten Formatierungsprobleme vor dem Anwenden der Formatierungsänderungen behebst. Nach dem Anwenden der Änderungen überprüfe die modifizierten Dateien, um sicherzustellen, dass alles korrekt aussieht, bevor du deine Änderungen bestätigst und hochlädst.

**Wichtig:** Unsere CI-Pipeline führt automatische Überprüfungen durch, um sicherzustellen, dass der Code-Stil eingehalten wird. Wenn die CI-Pipeline fehlschlägt, bedeutet das, dass Anpassungen am Code-Stil erforderlich sind, bevor dein Branch gemerged werden kann. Bitte beachte die Fehlermeldungen und passe deinen Code entsprechend an, bevor du einen Pull Request erstellst.

Denke daran: Führe `spotless:check` und `spotless:apply` lokal aus, bevor du einen Pull Request erstellst, um einen konsistenten Code-Stil im gesamten Projekt sicherzustellen.
