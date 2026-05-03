# MyNotes Android App

MyNotes adalah aplikasi Android Kotlin sederhana untuk membuat reminder text yang tampil sebagai widget sticky notes di homescreen.

## Fitur
- Input reminder text
- Widget homescreen Android
- Sticky notes warna-warni dengan rounded corner dan shadow
- Pilihan font: Inter, Plus Jakarta Sans, Outfit, Poppins, Bricolage Grotesque
- Pilihan warna text: hitam/putih
- Pilihan ukuran font: kecil/sedang/besar
- Pilihan warna notes: kuning, pink, lime, cyan, lavender
- Penyimpanan lokal memakai SharedPreferences
- Tanpa login
- Widget otomatis update setelah tombol simpan ditekan

## Cara Build APK
1. Buka Android Studio terbaru.
2. Pilih **Open** lalu arahkan ke folder `MyNotes`.
3. Tunggu Gradle Sync selesai.
4. Pilih menu **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. File APK debug biasanya ada di:
   `app/build/outputs/apk/debug/app-debug.apk`

## Cara Install APK di HP Android
1. Salin APK ke HP.
2. Buka file APK.
3. Jika muncul peringatan, aktifkan **Install unknown apps / Izinkan dari sumber ini**.
4. Install aplikasi.
5. Buka MyNotes, isi reminder, pilih desain, tekan **Simpan / Update Widget**.
6. Tekan lama homescreen > Widgets > pilih **MyNotes**.

## Upload APK ke Hostinger
1. Login Hostinger hPanel.
2. Masuk ke **File Manager**.
3. Buka folder `public_html`.
4. Buat folder baru, misalnya `mynotes`.
5. Upload APK ke folder tersebut, contoh nama file: `mynotes.apk`.
6. Link unduhan menjadi: `https://domainanda.com/mynotes/mynotes.apk`.

## Halaman Download Sederhana di Hostinger
Buat file `index.html` di folder `public_html/mynotes/` dengan isi:

```html
<!doctype html>
<html lang="id">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Download MyNotes</title>
  <style>
    body{font-family:Arial,sans-serif;background:#f7f8fa;display:flex;align-items:center;justify-content:center;min-height:100vh;margin:0}
    .card{background:white;padding:32px;border-radius:24px;box-shadow:0 10px 30px #0002;text-align:center;max-width:420px}
    a{display:inline-block;background:#18d5ff;color:#111;padding:14px 22px;border-radius:14px;text-decoration:none;font-weight:bold}
  </style>
</head>
<body>
  <div class="card">
    <h1>MyNotes</h1>
    <p>Reminder widget sticky notes untuk Android.</p>
    <a href="mynotes.apk">Download APK</a>
  </div>
</body>
</html>
```

## Mengganti Nama Aplikasi
Edit file:
`app/src/main/res/values/strings.xml`

Ubah:
```xml
<string name="app_name">MyNotes</string>
```

## Mengganti Ikon
Ganti file:
`app/src/main/res/drawable/ic_launcher_foreground.xml`

Untuk ikon profesional, gunakan Android Studio:
**File > New > Image Asset**.

## Mengganti Warna Notes
Edit mapping warna di:
`app/src/main/java/com/example/mynotes/NotePrefs.kt`

Bagian fungsi:
```kotlin
fun noteColor(label: String): Int = when (label) { ... }
```

## Mengganti / Menambah Font
Daftar pilihan font ada di:
`NotePrefs.kt`

Bagian:
```kotlin
val fonts = listOf(...)
```

Catatan: Android tidak selalu memiliki semua font Google secara bawaan. Kode tetap build dan berjalan; jika font tidak tersedia, Android akan memakai fallback system font. Untuk hasil 100% sesuai font, tambahkan file `.ttf` berlisensi resmi ke `res/font`, lalu modifikasi renderer memakai `ResourcesCompat`/font resource.

---

## GitHub Auto Build APK
Project versi final ini sudah dilengkapi workflow GitHub Actions:

`.github/workflows/build-apk.yml`

Cara menjalankan:
1. Upload seluruh isi project ke GitHub.
2. Buka tab **Actions**.
3. Pilih **Build MyNotes APK**.
4. Klik **Run workflow**.
5. Download artifact **MyNotes-debug-apk**.

Jika GitHub menampilkan error tentang `gradle-wrapper.jar`, workflow ini tetap aman karena `gradlew` sudah dibuat dengan fallback ke Gradle yang diinstal otomatis oleh GitHub Actions.
