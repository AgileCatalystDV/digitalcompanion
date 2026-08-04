# Cursor Setup — Digital Companion

Agentic model (CO_PM_RESEARCH): **Rules guide. Skills do. Commands trigger.**

## Structuur

```
.cursor/
├── commands/       # /review-pr, /security-audit, /threat-model, /pr
├── rules/          # Personae (Alex, Fede, Floyd, Ian, Maya, PenPeter, Co-PM)
├── skills/         # Just-in-time domein-expertise
├── hooks.json      # Agent/skill references
└── README.md       # Dit bestand
```

## Gebruik

- **Rules**: Altijd actief; compact (< 50 regels per persona)
- **Skills**: Agent laadt wanneer relevant; zie `docs/CO_PM_RESEARCH.md`
- **Commands**: Typ `/` + naam in Chat/Composer
