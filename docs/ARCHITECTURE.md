# Digital Companion — Architectuur

**Auteur**: Alex  
**Datum**: 2026-02-14

---

## Overzicht

Android companion app voor Rokid Glasses. Eigen AI-backend om niet vast te zitten aan Rokid-ecosysteem.

```
┌─────────────────┐     BLE + WiFi P2P      ┌─────────────────┐
│  Android App    │ ◄─────────────────────► │  Rokid Glasses   │
│  (Companion)    │                         │  (YodaOS-Sprite) │
└────────┬────────┘                         └─────────────────┘
         │
         │ HTTPS
         ▼
┌─────────────────┐
│  Eigen AI       │  ASR, TTS, LLM (te ontwerpen)
│  Backend        │
└─────────────────┘
```

---

## Lagen

| Laag | Verantwoordelijkheid |
|------|------------------------|
| **UI** | Jetpack Compose, schermen, knoppen |
| **ViewModel** | State, business logic |
| **SDK Abstraction** | Wrapper rond CxrApi, mock voor tests |
| **Domain** | Use cases (connect, capture, AI flow) |
| **Data** | BLE, WiFi, API calls |

---

## SDK Abstraction

**Doel**: Testbaarheid zonder hardware. Mock implementatie voor dry-run.

```kotlin
interface RokidSdkFacade {
    fun initBluetooth(context: Context, device: BluetoothDevice, callback: BluetoothStatusCallback)
    fun setBrightness(level: Int)
    fun takePhoto(...)
    // ...
}

// Production
class CxrApiFacade : RokidSdkFacade { /* wraps CxrApi.getInstance() */ }

// Test/Dry-run
class MockRokidFacade : RokidSdkFacade { /* simuleert responses */ }
```

---

## MVP Scope (Sprint 0)

1. **BLE Discovery** — Scan voor Rokid devices (UUID filter)
2. **Connect** — initBluetooth, onConnected
3. **Basis UI** — Knoppen: Brightness, Volume, Battery status
4. **Mock mode** — Zonder bril: MockRokidFacade, log output

---

## Module Structuur (Android)

```
app/                    # Main app
core:sdk-abstraction/   # Rokid SDK wrapper + mock
core:domain/            # Use cases (optioneel later)
```

---

## Referenties

- [rokidsdk.md](../rokidsdk.md) — SDK API referentie
- [Rokid Glasses](https://global.rokid.com/pages/rokid-glasses)
