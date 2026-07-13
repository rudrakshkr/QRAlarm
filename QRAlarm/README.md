# QR Alarm

An Android alarm app (Kotlin + Jetpack Compose, Material 3, MVVM) where each
alarm is paired with a QR code chosen at setup time. The alarm loops until
the user scans that exact QR code again.

## How to open this project
1. Open Android Studio (Koala/2024.1 or newer recommended).
2. `File > Open` and select the `QRAlarm/` folder.
3. Let Gradle sync (it will generate the Gradle wrapper automatically if
   prompted, or you can point it at a local Gradle 8.7+ install).
4. Run on a device or emulator running API 26+ (a real device is strongly
   recommended for testing the camera-based QR mission).

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
