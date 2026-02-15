---
name: deployment-pipeline
description: Builds CI/CD for Android: Gradle, GitHub Actions, APK signing. Use when setting up CI or changing build/deploy.
---

# Android Deployment Pipeline

## Description

Reproducible path from code to APK. Gradle, signing, optional Firebase App Distribution.

## Instructions

1. **Gradle**: settings.gradle.kts, build.gradle.kts; version catalog; signing config
2. **GitHub Actions**: checkout, setup-java 17, `./gradlew assembleDebug`, test, artifact upload
3. **Signing**: keystore in GitHub Secrets
4. **Distribution** (optional): Firebase App Distribution

## Template

```yaml
# .github/workflows/android.yml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
      - run: ./gradlew assembleDebug
```
