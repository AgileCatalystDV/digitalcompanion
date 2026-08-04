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
# Genereer Gradle wrapper (eenmalig)
gradle wrapper

# Build
./gradlew assembleDebug

# Run op emulator/device
./gradlew installDebug
```

## MVP Focus

1. BLE discovery & connect
2. Basis UI met knoppen
3. Dry-run SDK functionaliteit (mock mode)
