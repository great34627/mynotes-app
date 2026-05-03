# MyNotes - Cara Build APK Otomatis di GitHub

## Cara paling mudah
1. Buat repository baru di GitHub, misalnya `MyNotes`.
2. Upload semua isi folder project ini ke repository tersebut.
3. Pastikan file ini ikut terupload:
   - `.github/workflows/build-apk.yml`
   - `gradlew`
   - `gradlew.bat`
   - `gradle/wrapper/gradle-wrapper.properties`
4. Buka tab **Actions** di GitHub.
5. Pilih workflow **Build MyNotes APK**.
6. Klik **Run workflow**.
7. Setelah selesai, buka hasil workflow, lalu download artifact bernama **MyNotes-debug-apk**.
8. Di dalamnya ada file `MyNotes-debug.apk`.

## Kenapa workflow ini lebih aman?
Workflow ini tidak bergantung pada `gradle-wrapper.jar`. Jika file jar tidak ada, script `gradlew` otomatis memakai Gradle 9.3.1 yang diinstal oleh GitHub Actions.

## Lokasi APK lokal jika build lewat Android Studio
`app/build/outputs/apk/debug/app-debug.apk`
