# Rokid Glasses SDK — Technical Development Guide

**Bron**: [digitalcompanion repo](https://github.com/AgileCatalystDV/digitalcompanion)
**Product**: [Rokid Glasses](https://global.rokid.com/pages/rokid-glasses)

---

## Overview

CXR-M SDK voor Android companion apps. Bidirectionele communicatie via Bluetooth + WiFi P2P met Rokid Glasses (YodaOS-Sprite).

## Architecture

- **CXR-M**: Mobile (Android)
- **CXR-S**: On-device (YodaOS-Sprite)
- **Protocol**: Bluetooth + WiFi P2P

## Key Components

- Device connection (BLE, WiFi P2P)
- Hardware control (brightness, volume, power)
- Media capture (photo, video, audio)
- AI interaction pipeline (ASR, TTS)

## Setup

- Min SDK: 28
- Kotlin/Java, Gradle Kotlin DSL
- Maven: `https://maven.rokid.com/repository/maven-public/`
- Dependency: `com.rokid.cxr:client-m:1.0.1-20250812.080117-2`

## Current Project Status

- Huidige app-flow draait als mock-first companion APK op de Android telefoon
- `client-m` staat nog niet actief in de build voor production calls
- De echte Rokid integratie wordt pas aangesloten nadat de SDK docs opnieuw gerefresht en gevalideerd zijn

## Documentation Refresh Required

Voor de volgende implementatiefase moeten we de Rokid SDK docs expliciet opnieuw refreshen en controleren:

1. Bevestig de juiste dependency en versie voor de niet-Style Rokid AI Glasses
2. Valideer de echte connect-flow: `initBluetooth` -> `onConnectionInfo` -> `connectDevice`
3. Controleer welke callbacks, permissions en transportstappen verplicht zijn voor dit specifieke device
4. Herbevestig of deploy uitsluitend via Android companion hoort te lopen, en niet rechtstreeks op de bril

## ROKID_SERVICE_UUID

```
00009100-0000-1000-8000-00805f9b34fb
```

## Best Practices

1. Start met BLE connectivity
2. WiFi alleen wanneer nodig (hoog verbruik)
3. Robuuste reconnect logic
4. Mock SDK voor testen zonder hardware

---

*Zie volledige referentie in [GitHub](https://github.com/AgileCatalystDV/digitalcompanion/blob/main/rokidsdk.md)*
