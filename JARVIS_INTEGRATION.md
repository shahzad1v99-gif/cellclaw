# Jarvis integration notes

This fork is the native Android agent for the Shahzad Jarvis HUD learning project. The HUD and this agent remain separate applications until a deliberate bridge is implemented.

## Hardening baseline

The fork defaults to the `Cautious` permission profile. Invalid or missing stored profiles fall back to `Cautious`, and unknown tool names are denied rather than auto-approved. The agent now enforces a tool's explicit `requiresApproval` flag before execution instead of relying only on the selected profile. Screen tree reads, screenshots, and vision analysis are marked for explicit approval.

These changes do not yet implement the Jarvis Master Power switch or the HUD Screen Access switch. They are native-agent safety defaults only. They also do not claim that the current wake-word model recognizes “Hey Jarvis”.

## Planned boundary

```text
Jarvis HUD (Expo/React Native)
        │ authenticated localhost bridge or signed Android IPC
        ▼
CellClaw agent (Kotlin/Android)
        │ server-side provider adapter, after approval
        ▼
Jarvis backend / configured provider
```

The final design must keep provider credentials out of the HUD APK, keep screen data blocked when Screen Access is OFF, and require fresh Allow/Deny approval for screen observation and external/write actions. The native agent must not expose an unauthenticated LAN server.

## Build requirements

A CellClaw debug APK requires JDK 17 and Android SDK 35. A build has not been claimed from this Sandbox until those prerequisites are available. Do not paste API keys into source, commit local properties, or upload generated APKs containing secrets.

## Upstream attribution

This fork is derived from `https://github.com/frido22/cellclaw`. Preserve upstream attribution and verify the applicable license text before redistribution.
