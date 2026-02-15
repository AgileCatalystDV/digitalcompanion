---
name: code-review
description: Performs structured code review with Kotlin/Android focus. Use when reviewing PR, feature branch, or on request @Maya.
---

# Code Review (Kotlin/Android)

## Description

Evaluates correctness, lifecycle, BLE edge cases, and code quality. Output structured report.

## Instructions

1. Read changed files
2. Check: logic, edge cases (null, disconnect, BLE timeout), lifecycle (onPause/onResume)
3. Check: naming, DRY, coroutines/Flow; no memory leaks; main thread; permissions
4. Categorize: Blocking / Suggestions / Nice to haves
5. Output: structured markdown report

## Output Template

```markdown
## Code Review Report
### Blocking Issues
### Suggestions
### Nice to haves
```
