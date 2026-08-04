---
name: android-mock-sdk
description: Provides testable SDK abstraction for dry-run without hardware. Use when designing SDK facade, writing tests, or implementing mock mode.
---

# Mocking Rokid SDK

## Description

Implements RokidSdkFacade interface with MockRokidFacade for unit/integration tests and dry-run. See [ARCHITECTURE.md](../../../docs/ARCHITECTURE.md) for layer design.

## Instructions

1. Define `RokidSdkFacade` with only methods the app needs
2. `CxrApiFacade` wraps `CxrApi.getInstance()` for production
3. `MockRokidFacade` simulates responses, logs for dry-run
4. Inject via BuildConfig/feature flag `USE_MOCK_SDK`

## Template

```kotlin
interface RokidSdkFacade {
    fun initBluetooth(context: Context, device: BluetoothDevice, callback: BluetoothStatusCallback)
    fun setGlassBrightness(level: Int)
    fun setGlassVolume(level: Int)
}
```

## Notes

- Keep interface minimal
- Mock: deterministic for tests; optional delays for UX dry-run
