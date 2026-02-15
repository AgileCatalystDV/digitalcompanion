# Toegankelijkheid — Realiteitsanalyse

**Auteurs**: Alex (Architect), Co-PM Intelligence  
**Datum**: 2026-02-14

---

## Doelstellingen (2e project)

1. **Blinden**: Boeken voorgelezen via LLM op telefoon/remote → TTS → bril
2. **Doven**: Real-time captions op bril via ASR — sprekers hoeven geen gebarentaal te gebruiken

---

## [Alex] Technische haalbaarheid

### Blinden — Boeken horen

| Aspect | Beoordeling |
|--------|-------------|
| **LLM + TTS** | ✅ Haalbaar. Bestaande modellen (Whisper, open TTS, LLM APIs) kunnen tekst → spraak. |
| **Boeken** | ✅ EPUB/PDF → tekst extractie → LLM (samenvatting/aanpassing) → TTS. |
| **Bril als audio** | ✅ Rokid Glasses hebben audio-output; SDK ondersteunt dit. |
| **Remote** | ✅ Telefoon/cloud doet zwaar werk; bril is output device. |

**Risico**: Kwaliteit TTS (natuurlijkheid, snelheid) en latency. Goede TTS-modellen bestaan (Coqui, ElevenLabs, cloud APIs).

### Doven — Real-time captions

| Aspect | Beoordeling |
|--------|-------------|
| **ASR** | ✅ Haalbaar. Whisper, Google Speech-to-Text, etc. doen real-time of near-real-time. |
| **Tekst op bril** | ✅ AR-display toont tekst; SDK ondersteunt overlay. |
| **Latency** | ⚠️ Kritiek. < 1–2 s vertraging nodig voor conversatie. Streaming ASR + edge/cloud trade-off. |
| **Accuraatheid** | ⚠️ Kritiek. Slechte ASR = verkeerde info. Moet getest met dove gebruikers. |

**Risico**: Accuraatheid en latency. Goede ASR bestaat, maar edge cases (achtergrondlawaai, accenten) blijven uitdaging.

---

## [Co-PM] Strategische beoordeling

### Realistisch?

**Ja, technisch realistisch** — de bouwstenen bestaan. De uitdaging is **kwaliteit en UX**, niet fundamentele technologie.

### Aanbevelingen

1. **MVP eerst**: Connectiviteit, basis AI-flow. Toegankelijkheid als expliciete Fase 4 (na media, AI-core).
2. **User testing**: Vanaf eerste toegankelijkheidsprototype met echte blinde/dove gebruikers.
3. **Fallbacks**: Offline TTS/ASR voor wanneer netwerk faalt.
4. **Regulering**: Check toegankelijkheidsnormen (EU Accessibility Act, WCAG) voor compliance.

### Risico's

- **Verwachtingen**: "Het werkt" vs "het werkt goed genoeg" — groot verschil voor dagelijks gebruik.
- **Scope creep**: Toegankelijkheid is breed; focus op 1–2 use cases per doelgroep.

---

## Conclusie

**Realistisch en waardevol.** Bouw eerst solide basis (connectiviteit, eigen AI-backend), plan toegankelijkheid als expliciete fase met user testing en kwaliteitscriteria.
