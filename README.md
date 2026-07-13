# QR Alarm

An Android alarm app (Kotlin + Jetpack Compose, Material 3, MVVM) where each
alarm is paired with a QR code chosen at setup time. The alarm loops until
the user scans that exact QR code again.

## How to open this project
1. Open Android Studio (Koala/2024.1 or newer recommended).
2. `File > Open` and select the `QRAlarm/` folder.
3. Let Gradle sync.
4. Run on a device or emulator running API 26+ (a real device is strongly
   recommended for testing the camera-based QR mission).

## Gradle Wrapper
`gradlew`, `gradlew.bat`, and `gradle/wrapper/gradle-wrapper.properties`
(pinned to Gradle 8.7, the minimum required by AGP 8.5.2) are included.

**One caveat:** `gradle/wrapper/gradle-wrapper.jar` is a compiled binary, and
it was *not* generated in this repo yet - it needs to come from an actual
Gradle install rather than being hand-written. You have two ways to get a
real one, and you only need to do one of them:

- **Locally (10 seconds, recommended):** open this project in Android Studio
  once and let it sync - Studio generates the wrapper jar automatically. Or,
  if you have Gradle installed, run:
  ```
  gradle wrapper --gradle-version 8.7 --distribution-type bin
  ```
  Then commit the resulting `gradle/wrapper/gradle-wrapper.jar`.
- **In CI only:** `.github/workflows/android-ci.yml` already handles this -
  it provisions Gradle via the official `gradle/actions/setup-gradle` action
  and runs the same `gradle wrapper` command as a build step before calling
  `./gradlew assembleDebug`, so CI works today even before you've committed
  a jar locally. Once you've committed a real jar via the step above, you
  can delete the "Regenerate Gradle Wrapper" step from the workflow if you'd
  rather CI just trust the committed jar directly.

## Build progress
This project is being built one feature/stage at a time. Checked stages are
present in this snapshot:

- [x] 1. Project setup + Room data layer (`Alarm`, `AlarmDao`, `AppDatabase`, `Converters`)
- [ ] 2. Repository + ViewModels (MVVM domain layer)
- [ ] 3. Alarm scheduling (`AlarmManager`, `AlarmReceiver`, boot rescheduling)
- [ ] 4. Foreground `AlarmService` (looping sound/vibration + notification)
- [ ] 5. Full-screen `AlarmRingingActivity`
- [ ] 6. QR mission (CameraX + ML Kit scanner, setup capture + verification)
- [ ] 7. Compose UI (alarm list, add/edit screen, theme, navigation)
- [ ] 8. Final manifest wiring + polish

## Package layout so far
```
com.qralarm.app
└── data.local
    ├── Alarm.kt          Room entity for one alarm
    ├── Converters.kt      TypeConverter for the repeatDays list column
    ├── AlarmDao.kt        Queries/inserts/updates/deletes
    └── AppDatabase.kt     RoomDatabase singleton
```
