# Digital Companion — Rokid Glasses Android App

Android companion app voor [Rokid Glasses](https://global.rokid.com/pages/rokid-glasses). Eigen AI-backend (niet Rokid-ecosysteem). **Langetermijn**: toegankelijkheid voor blinden (boeken horen) en doven (real-time captions).

## Project

- **Repo**: https://github.com/AgileCatalystDV/digitalcompanion
- **SDK docs**: `rokidsdk.md`
- **Team**: Virtueel AI-team (zie AGENTS.md)

## Structuur

```
digitalcompanion/
├── AGENTS.md           # Team protocol
├── app/                # Android app (Kotlin)
├── docs/               # Architectuur, specs
├── .cursor/
│   ├── rules/          # Agent personae
│   └── skills/         # Domein-specifieke skills
└── rokidsdk.md         # SDK referentie
```

## Start

```bash
# Build
./gradlew assembleDebug

# Install op Android device (bv. S26)
./gradlew installDebug
```

## Huidige status

- Gradle wrapper staat in de repo
- Mock-first telefoonflow staat klaar voor Android dry-run
- Shared Android Studio run configs staan in `.run/`
- Echte Rokid SDK calls zijn nog niet geactiveerd

## Mock-first deploy

- Doelplatform nu: Android telefoon, bv. Samsung S26
- De huidige APK draait **niet** rechtstreeks op de Rokid bril
- De app bevat een `RokidSdkFacade`, `MockRokidFacade` en een mock UI voor scan/connect/control tests
- Run configs:
  - `Run Digital Companion`
  - `Assemble Debug APK`
  - `Install Debug APK`

## Rokid SDK docs

- Voor de volgende fase moeten de Rokid SDK docs opnieuw gerefresht en gevalideerd worden voordat we echte `client-m` API-calls aansluiten
- Controleer dan opnieuw dependency-versie, connect-flow (`initBluetooth`, `onConnectionInfo`, `connectDevice`) en device-compatibiliteit voor de niet-Style Rokid AI Glasses

## MVP Focus

1. BLE discovery & connect
2. Basis UI met knoppen
3. Dry-run SDK functionaliteit (mock mode)
