# Sprint 0 — Project Setup & BLE Discovery

**Status**: In uitvoering  
**Focus**: Basis app, BLE discovery, mock mode

---

## Doel

1. Android project draait
2. Mock-first telefoonflow draait op Android device (S26)
3. Mock mode: dry-run zonder bril
4. SDK facade interface staat klaar voor echte Rokid integratie

---

## Taken

| # | Taak | Verantwoordelijke |
|---|------|-------------------|
| 1 | Gradle wrapper, build valideren | Klaar |
| 2 | RokidSdkFacade interface + MockRokidFacade | Klaar |
| 3 | Mock scan/connect scherm (Compose) | Klaar |
| 4 | Shared run configs voor build/install | Klaar |
| 5 | Permissions (runtime, Android 12+) | Later, bij echte BLE integratie |

---

## Opgeleverd

- `RokidSdkFacade` + `MockRokidFacade`
- `CompanionViewModel` met mock acties voor scan, connect, disconnect, brightness, volume en battery
- Compose control surface voor mock Rokid device op de telefoon
- Shared Android Studio run configs in `.run/`
- Succesvolle `./gradlew assembleDebug` build

---

## Volgende stap

- Rokid SDK docs opnieuw refreshen en valideren voor de echte device flow
- Daarna pas de echte `client-m` dependency en BLE connect-path activeren

---

## Referenties

- [ARCHITECTURE.md](./ARCHITECTURE.md)
- [rokidsdk.md](../rokidsdk.md)
- [AGENTS.md](../AGENTS.md)
