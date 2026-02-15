# Review Pull Request

Perform a structured code review following Maya's QA checklist.

1. Read all changed files in the diff
2. For each file, evaluate:
   - Correctness: logic, edge cases (null, disconnect, BLE timeout)
   - Lifecycle: onPause/onResume, connection state
   - Code quality: naming, DRY, coroutines/Flow
   - Android: no memory leaks, main thread usage, permissions
3. Categorize: Blocking / Suggestions / Nice to haves
4. Output structured report
