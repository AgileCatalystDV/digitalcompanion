---
name: threat-modeling
description: Identifies threats before implementation. Use for new features (connect flow, AI pipeline) or on request @PenPeter.
---

# STRIDE Threat Modeling

## Description

STRIDE: Spoofing, Tampering, Repudiation, Information disclosure, Denial of service, Elevation of privilege.

## Instructions

1. Define scope (component, flow)
2. For each: S — fake device/app? T — data tampering? R — repudiation? I — data leak? D — DoS? E — privilege abuse?
3. Output: threats per component, mitigations, architecture recommendations
