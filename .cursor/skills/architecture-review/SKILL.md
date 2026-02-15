---
name: architecture-review
description: Multi-perspective review: security, scalability, maintainability, deployability. Use after new architecture plan, major refactor, or SDK/AI-backend decisions.
---

# Architecture Review

## Description

Multi-agent review: PenPeter (security), Maya (testability), Ian (deployability), Co-PM (strategic fit). See [ARCHITECTURE.md](../../../docs/ARCHITECTURE.md).

## Instructions

1. Read ARCHITECTURE.md or design doc
2. PenPeter: BLE/WiFi security, data flow
3. Maya: mock SDK, test doubles
4. Ian: Android build, CI
5. Co-PM: strategic fit
6. Output integrated report

## Output Template

```markdown
# Architecture Review Report
## Review Summary
## Security (PenPeter)
## Maintainability (Maya)
## Deployability (Ian)
## Strategic (Co-PM)
## Recommendations
```
