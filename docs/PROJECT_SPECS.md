# Digital Companion — Project Specificaties

**Status**: Concept  
**Datum**: 2026-02-14

---

## Doel

Android companion app voor Rokid Glasses met **eigen AI-backend** — niet afhankelijk van Rokid-ecosysteem.

---

## Toegankelijkheid (2e project, langetermijndoel)

Verbetering voor **blinden en slechthorenden**:

| Doelgroep | Use case | Technologie |
|-----------|----------|-------------|
| **Blinden** | Boeken *horen* — voorgelezen via LLM op telefoon of remote | TTS, LLM (tekst → spraak), bril als audio-output |
| **Doven/slechthorenden** | Geen gebarentaal nodig van sprekers — tekst op scherm van wat er gezegd wordt | ASR (speech-to-text) → real-time captions op bril |

*Implementatie correct uitvoeren is cruciaal; kwaliteit ASR/TTS bepaalt bruikbaarheid.*

---

## Hardware

- **Rokid Glasses** (CXR-M compatible)
- **Communicatie**: Bluetooth (vereist) + WiFi P2P (optioneel, hoog verbruik)
- **Min Android**: SDK 28

---

## MVP (Sprint 0–1)

| # | Feature | Beschrijving |
|---|---------|--------------|
| 1 | BLE Discovery | Scan voor Rokid devices, filter op UUID |
| 2 | Connect | initBluetooth, connection flow |
| 3 | Basis UI | Scherm met knoppen: Brightness, Volume, Battery |
| 4 | Mock mode | Dry-run zonder bril — MockRokidFacade |
| 5 | Disconnect | Graceful disconnect, reconnect logic |

---

## Fase 2 (Media)

- Photo capture
- Video recording
- Audio streaming

---

## Fase 3 (AI)

- Eigen ASR (speech-to-text)
- Eigen TTS (text-to-speech)
- Eigen LLM/assistant
- AI key event handling

---

## Fase 4 (Toegankelijkheid — 2e project)

- **Blinden**: Boeken horen via LLM + TTS op bril
- **Doven**: Real-time captions via ASR — sprekers geen gebarentaal nodig

*Zie [ACCESSIBILITY_REALISM.md](./ACCESSIBILITY_REALISM.md) voor haalbaarheidsanalyse.*

---

## Technische Keuzes

| Aspect | Keuze |
|--------|-------|
| Taal | Kotlin |
| UI | Jetpack Compose |
| Build | Gradle Kotlin DSL |
| SDK | CXR-M client-m (Rokid Maven) |
| AI Backend | Te ontwerpen (Python/FastAPI) |
