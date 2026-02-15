---
name: ble-integration
description: Implements BLE discovery, connect flow, and lifecycle for Rokid Glasses. Use when implementing BLE scan, connect, or reconnect logic.
---

# BLE Integration for Rokid

## Description

Rokid Service UUID: `00009100-0000-1000-8000-00805f9b34fb`. Flow: BLE first → socket connect → WiFi (optional). See [rokidsdk.md](../../../rokidsdk.md).

## Instructions

1. **Discovery**: ScanFilter on Service UUID; ScanSettings LOW_LATENCY; stop scan after device found
2. **Connect**: `initBluetooth` → `onConnectionInfo` (socketUuid, macAddress) → `connectDevice`
3. **Lifecycle**: Disconnect on pause (optional); reconnect with exponential backoff; 30s timeout
4. **Permissions** (Android 12+): BLUETOOTH_SCAN, BLUETOOTH_CONNECT; ACCESS_FINE_LOCATION (10–11)

## Notes

- BLE before WiFi always
- Graceful disconnect, log error codes for debug
