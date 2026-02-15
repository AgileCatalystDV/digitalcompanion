# Co-PM Research — Agentic 2025/2026, Skills & Cursor

**Datum**: 2026-02-14  
**Update**: Uitgebreid met agentic 2025–2026, Cursor-specifieke best practices

---

## 1. Kernprincipes — Rules vs Skills vs Commands

| Type | Rol | Activatie | Context-kosten |
|------|-----|-----------|----------------|
| **Rules** | Guardrails, conventies | Altijd actief | Hoog (altijd in prompt) |
| **Commands** | Mission & persona | Handmatig (`/command`) | Matig (task-focused) |
| **Skills** | Domein-expertise | Dynamisch (agent beslist) | Laag (alleen laden wanneer nodig) |

**"Rules guide. Skills do. Commands trigger."** — Rushi's Power User Playbook

### Rules — Non-negotiable guardrails

- **Locatie**: `.cursor/rules/` (of `.cursorrules` in project root)
- **Formaat**: Markdown of `.mdc` (YAML frontmatter + body)
- **Best practice**: Max 300–400 regels totaal; anders negeert de agent het midden
- **Gebruik**: Code style, commands, pointers naar canonical examples

### Commands — Mission & persona

- **Locatie**: `.cursor/commands/`
- **Invocation**: `/` prefix (bv. `/review-pr`, `/security-audit`)
- **Gebruik**: Repeatable workflows, multi-step playbooks, persona-switching

### Skills — Just-in-time intelligence (progressive disclosure)

- **Locatie**: `~/.cursor/skills-cursor/` (global) of `.cursor/skills/` (project)
- **Formaat**: `SKILL.md` per skill
- **Trigger**: Agent matcht request tegen skill descriptions; laadt SKILL.md alleen wanneer relevant
- **Gebruik**: Domein-expertise, complexe workflows, API-integraties

---

## 2. Production-Grade Agentic AI (arXiv 2512.08769, dec 2025)

**9 kernbest practices** voor production-grade agentic workflows:

1. **Tool-first design** — boven Model Context Protocol (MCP)
2. **Pure-function invocation** — voorspelbare, testbare tools
3. **Single-tool & single-responsibility agents** — één focus per agent
4. **Externalized prompt management** — prompts buiten code
5. **Responsible-AI-aligned model consortium** — juiste model per taak
6. **Clean separation** — workflow logic vs MCP/tool servers
7. **Containerized deployment** — schaalbaar, reproduceerbaar
8. **KISS** — Keep It Simple, Stupid
9. **Deterministic orchestration** — waar mogelijk

---

## 3. Cursor Agent Best Practices (2025–2026)

### Plan before coding

- **Plan Mode** (Shift+Tab): Agent maakt plan, wacht op goedkeuring, bouwt daarna
- Plannen opslaan in `.cursor/plans/` voor documentatie en hervatting

### Context management

- **Laat de agent zoeken**: Gebruik semantic search/grep; tag niet handmatig alle bestanden
- **@Branch**: "Review changes on this branch" — oriënteer de agent
- **Nieuwe conversatie** bij: nieuw logisch blok werk, agent lijkt verward, andere feature
- **Verder praten** bij: debuggen van net gebouwde code, itereren opzelfde feature

### Rules in Cursor

- Markdown in `.cursor/rules/`
- Focus: commands, code style, pointers naar voorbeelden
- **Vermijd**: edge cases die zelden gelden, volledige style guides (gebruik linter)

### Skills in Cursor (SKILL.md)

- **Hooks**: Scripts die voor/na agent-acties draaien (`.cursor/hooks.json`)
- **Custom commands**: Reusable workflows via `/`
- **Long-running loops**: Hook kan `followup_message` teruggeven om agent te laten doorgaan tot doel bereikt

---

## 4. Skill Authoring Best Practices (Claude/Cursor)

### Conciseness

- Context window is gedeeld; alleen info die Claude nog niet kent
- Vraag per paragraaf: "Does this justify its token cost?"

### Degrees of freedom

| Niveau | Wanneer | Voorbeeld |
|--------|---------|-----------|
| **Hoog** | Heuristiek, context-afhankelijk | Code review, verbeteringen |
| **Medium** | Configuratie, variatie ok | Report templates, parameters |
| **Laag** | Fragiel, exacte volgorde | DB migrations, deploy scripts |

### SKILL.md structuur

- **Verplicht**: Title, Description, Instructions
- **Optioneel**: Templates, Examples, Notes, Validation
- **Progressive disclosure**: Verwijs naar aparte bestanden; laad alleen wat nodig is
- **Max ~500 regels** in SKILL.md body voor optimale performance

### Beschrijving (description)

- **Derde persoon**: "Processes Excel files" niet "I can help you"
- **Specifiek**: Wat doet de skill + wanneer gebruiken (triggers)
- **Gerund naming**: `processing-pdfs`, `analyzing-spreadsheets`

### Evaluation-driven development

1. Identificeer gaps: Run Claude zonder skill, documenteer failures
2. Maak evaluations: 3 scenario's die de gaps testen
3. Schrijf minimale instructies om evaluations te halen
4. Itereer met Claude: Claude A schrijft skill, Claude B test

---

## 5. Cursor-specifieke details

### Rules format (.mdc)

- YAML frontmatter: `description`, `globs`, `alwaysApply`
- Pseudo-XML body: `<rule>`, `<meta>`, `<title>`, `<description>`
- Cursor v0.45+: pattern-specific rules in `.cursor/rules/*.mdc`

### Agent harness (Cursor)

- **User messages** + **Tools** (edit, search, terminal) + **Instructions** (rules)
- Cursor tune per model; focus op jouw prompts en rules

### Cloud agents

- `cursor.com/agents` — delegate taken (docs, tests, refactors)
- Remote sandbox, notificaties, PR bij klaar
- Slack: @Cursor trigger

### Parallel agents

- Git worktrees: elk agent eigen worktree
- Meerdere modellen tegelijk: vergelijk resultaten

---

## 6. Toegepast op Digital Companion

### Rules (personae)

- Compact, < 50 regels per persona
- Focus: rol, verantwoordelijkheid, NIET doen
- Geen domein-details (die in skills)

### Skills (just-in-time)

- **android-mock-sdk**: Mock/facade zonder hardware
- **ble-integration**: BLE patterns voor Rokid
- **code-review**: Kotlin/Android checklist
- **deployment-pipeline**: Android CI/CD
- **architecture-review**: Multi-perspective
- **security-audit**: Mobile/BLE
- **threat-modeling**: STRIDE

### Commands (aanbevolen)

- `/review-pr` — Code review workflow
- `/security-audit` — PenPeter audit
- `/threat-model` — STRIDE op feature
- `/pr` — Commit, push, open PR (Cursor blog voorbeeld)

---

## 7. Referenties

| Bron | URL |
|------|-----|
| Beyond Rules: Commands, Agents, Skills | https://agenticthinking.ai/blog/beyond-rules/ |
| Cursor Agent Best Practices | https://cursor.com/blog/agent-best-practices |
| Cursor Docs — Agent | https://docs.cursor.com/agent |
| Claude Skills Guide (design.dev) | https://design.dev/guides/claude-skills/ |
| Power User Playbook (Rushi) | https://www.rushis.com/the-power-user-playbook-mastering-cursor-rules-commands-and-skills/ |
| Production-Grade Agentic AI (arXiv 2512.08769) | https://arxiv.org/abs/2512.08769 |
| Claude Skill Authoring Best Practices | https://platform.claude.com/docs/en/agents-and-tools/agent-skills/best-practices |
| OpenAI: Building AI Agents | https://openai.com/business/guides-and-resources/a-practical-guide-to-building-ai-agents/ |
