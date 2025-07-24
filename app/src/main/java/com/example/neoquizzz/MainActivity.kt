package com.example.neoquizzz

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neoquiz.QuestionModel
import com.example.neoquiz.QuizModel
import com.example.neoquizzz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var quizListAdapter: QuizListAdapter
    private lateinit var fullQuizList: List<QuizModel>  // data asli

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullQuizList = listOf(
            QuizModel("1", "Struktur Data", "Belajar struktur data dasar", "10", listOf(
                QuestionModel("Stack bekerja dengan prinsip?", listOf("FIFO", "LIFO", "FILO", "LILO"), "LIFO"),
                QuestionModel("Queue bekerja dengan prinsip?", listOf("LIFO", "FILO", "FIFO", "LILO"), "FIFO"),
                QuestionModel("Struktur data LIFO adalah?", listOf("Queue", "Tree", "Stack", "Linked List"), "Stack"),
                QuestionModel("Struktur data FIFO adalah?", listOf("Array", "Stack", "Queue", "Graph"), "Queue"),
                QuestionModel("Binary tree termasuk?", listOf("Linear", "Non-linear", "Circular", "Double"), "Non-linear"),
                QuestionModel("Linked list termasuk?", listOf("Linear", "Non-linear", "Random", "Hierarki"), "Linear"),
                QuestionModel("Circular queue adalah?", listOf("Queue yang kembali ke awal", "Stack", "LinkedList", "Tree"), "Queue yang kembali ke awal"),
                QuestionModel("Stack digunakan untuk?", listOf("Undo", "Save", "Print", "Delete"), "Undo"),
                QuestionModel("Traversal pada tree dilakukan dengan?", listOf("DFS", "BFS", "A*", "Binary"), "DFS"),
                QuestionModel("Level order traversal menggunakan?", listOf("DFS", "BFS", "Greedy", "Backtrack"), "BFS"),
                QuestionModel("Struktur data untuk graf?", listOf("Adjacency list", "Queue", "Stack", "Array"), "Adjacency list"),
                QuestionModel("Operasi PUSH dilakukan pada?", listOf("Queue", "Stack", "Tree", "Heap"), "Stack"),
                QuestionModel("Operasi DEQUEUE dilakukan pada?", listOf("Queue", "Tree", "Stack", "Graph"), "Queue"),
                QuestionModel("Heap digunakan untuk?", listOf("Sorting", "Searching", "Hashing", "Encoding"), "Sorting"),
                QuestionModel("Postorder tree traversal urutan?", listOf("Left-Right-Root", "Root-Left-Right", "Right-Root-Left", "Left-Root-Right"), "Left-Right-Root"),
                QuestionModel("Inorder traversal urutan?", listOf("Left-Root-Right", "Right-Left-Root", "Root-Left-Right", "Left-Right-Root"), "Left-Root-Right"),
                QuestionModel("Array bersifat?", listOf("Statik", "Dinamik", "Non-linear", "Hierarki"), "Statik"),
                QuestionModel("Linked list bisa?", listOf("Menambah node dinamis", "Statik", "Tidak berubah", "Langsung akses indeks"), "Menambah node dinamis"),
                QuestionModel("Operasi insert pada linked list?", listOf("Tambah node", "Hapus node", "Ganti node", "Sort node"), "Tambah node"),
                QuestionModel("DFS singkatan dari?", listOf("Depth First Search", "Data File Search", "Drive File System", "Depth File Stack"), "Depth First Search")
            )),
            QuizModel("2", "Jaringan Komputer", "Cara kerja LIFO & FIFO", "15", listOf(
                QuestionModel("Protokol utama di internet?", listOf("HTTP", "TCP", "IP", "FTP"), "IP"),
                QuestionModel("Protokol untuk transfer file?", listOf("HTTP", "FTP", "IP", "SMTP"), "FTP"),
                QuestionModel("HTTP digunakan untuk?", listOf("Web", "Email", "Transfer file", "Ping"), "Web"),
                QuestionModel("IP adalah singkatan dari?", listOf("Internet Protocol", "Internal Path", "Instant Protocol", "Internet Path"), "Internet Protocol"),
                QuestionModel("Fungsi DNS?", listOf("Terjemah domain ke IP", "Ping server", "Amankan koneksi", "Transfer file"), "Terjemah domain ke IP"),
                QuestionModel("Port HTTP adalah?", listOf("80", "443", "21", "25"), "80"),
                QuestionModel("Port HTTPS adalah?", listOf("443", "80", "21", "110"), "443"),
                QuestionModel("Fungsi router?", listOf("Hubungkan jaringan", "Cetak dokumen", "Backup data", "Amankan sistem"), "Hubungkan jaringan"),
                QuestionModel("OSI Layer paling atas?", listOf("Application", "Network", "Session", "Physical"), "Application"),
                QuestionModel("OSI Layer paling bawah?", listOf("Physical", "Data Link", "Network", "Transport"), "Physical"),
                QuestionModel("TCP/IP layer ada berapa?", listOf("4", "5", "7", "3"), "4"),
                QuestionModel("Switch bekerja di layer?", listOf("Data Link", "Physical", "Network", "Application"), "Data Link"),
                QuestionModel("IP versi 4 panjangnya?", listOf("32 bit", "64 bit", "128 bit", "16 bit"), "32 bit"),
                QuestionModel("IP versi 6 panjangnya?", listOf("128 bit", "64 bit", "32 bit", "256 bit"), "128 bit"),
                QuestionModel("Protokol pengiriman email?", listOf("SMTP", "HTTP", "FTP", "IP"), "SMTP"),
                QuestionModel("Protokol untuk ambil email?", listOf("POP3", "SMTP", "FTP", "HTTP"), "POP3"),
                QuestionModel("Topologi jaringan bintang disebut?", listOf("Star", "Bus", "Ring", "Mesh"), "Star"),
                QuestionModel("Topologi jaringan yang semua terhubung?", listOf("Mesh", "Star", "Bus", "Ring"), "Mesh"),
                QuestionModel("MAC address terdapat di?", listOf("NIC", "Router", "Modem", "Switch"), "NIC"),
                QuestionModel("Ping digunakan untuk?", listOf("Cek koneksi", "Transfer file", "Login server", "Backup data"), "Cek koneksi")
            )),
            QuizModel(
                id = "3", title = "Pemrograman Web", subtitle = "JavaScript Dasar untuk Interaksi", time = "20", questions = listOf(
                    QuestionModel("Apa fungsi utama dari JavaScript di dalam halaman web?", listOf("Mengatur tampilan", "Membuat struktur", "Menambahkan interaktivitas", "Membuat database"), "Menambahkan interaktivitas"),
                    QuestionModel("Fungsi `alert()` digunakan untuk?", listOf("Membuat variabel", "Menampilkan pesan pop-up", "Mengganti warna", "Menambahkan gambar"), "Menampilkan pesan pop-up"),
                    QuestionModel("Bagaimana cara membuat komentar satu baris di JavaScript?", listOf("/* komentar */", "// komentar", "<!-- komentar -->", "# komentar"), "// komentar"),
                    QuestionModel("Fungsi dari `document.getElementById()` adalah?", listOf("Menambahkan elemen", "Menghapus elemen", "Mengambil elemen berdasarkan ID", "Menyimpan elemen ke database"), "Mengambil elemen berdasarkan ID"),
                    QuestionModel("Tipe data `boolean` hanya memiliki nilai?", listOf("True dan False", "0 dan 1", "Ya dan Tidak", "Benar dan Salah"), "True dan False"),
                    QuestionModel("Sintaks yang benar untuk membuat variabel adalah?", listOf("let x = 5;", "variable x = 5;", "x := 5;", "v x = 5;"), "let x = 5;"),
                    QuestionModel("Bagaimana cara memanggil fungsi bernama `hitung()`?", listOf("call hitung;", "hitung();", "run hitung();", "hitung:"), "hitung();"),
                    QuestionModel("Metode yang digunakan untuk menambahkan item ke array?", listOf("append()", "add()", "push()", "insert()"), "push()"),
                    QuestionModel("Operator perbandingan untuk 'sama dengan' adalah?", listOf("==", "=", "===", "!="), "=="),
                    QuestionModel("Event `onclick` dipicu saat?", listOf("Mouse diarahkan", "Elemen diklik", "Halaman dimuat", "Form dikirim"), "Elemen diklik"),
                    QuestionModel("Bagaimana cara mengecek panjang string `text`?", listOf("text.length", "text.size()", "text.count()", "text.length()"), "text.length"),
                    QuestionModel("Sintaks `if` yang benar di JavaScript?", listOf("if x > y then", "if (x > y)", "if x > y:", "if x > y {}"), "if (x > y)"),
                    QuestionModel("Tipe data array ditandai dengan tanda?", listOf("{}", "[]", "()", "<>"), "[]"),
                    QuestionModel("Event `onchange` sering digunakan pada elemen?", listOf("div", "input", "select", "button"), "select"),
                    QuestionModel("Nilai default dari variabel yang belum diberi nilai?", listOf("null", "0", "undefined", "empty"), "undefined"),
                    QuestionModel("Fungsi `console.log()` digunakan untuk?", listOf("Menampilkan pesan di HTML", "Menampilkan log di console", "Menjalankan script", "Menyimpan file"), "Menampilkan log di console"),
                    QuestionModel("JavaScript dijalankan di?", listOf("Client (browser)", "Server", "Database", "Terminal"), "Client (browser)"),
                    QuestionModel("Metode untuk menghapus elemen terakhir dari array?", listOf("pop()", "shift()", "remove()", "cut()"), "pop()"),
                    QuestionModel("Keyword `const` digunakan untuk?", listOf("Deklarasi variabel tetap", "Looping", "Menentukan class", "Mendeklarasi fungsi"), "Deklarasi variabel tetap"),
                    QuestionModel("Ekstensi file JavaScript adalah?", listOf(".js", ".java", ".script", ".jsx"), ".js")
                )),
            QuizModel(id = "3", title = "Pemrograman Web", subtitle = "JavaScript Dasar untuk Interaksi", time = "15", questions = listOf(
                    QuestionModel("Apa fungsi utama dari JavaScript di dalam halaman web?", listOf("Mengatur tampilan", "Membuat struktur", "Menambahkan interaktivitas", "Membuat database"), "Menambahkan interaktivitas"),
                    QuestionModel("Fungsi `alert()` digunakan untuk?", listOf("Membuat variabel", "Menampilkan pesan pop-up", "Mengganti warna", "Menambahkan gambar"), "Menampilkan pesan pop-up"),
                    QuestionModel("Bagaimana cara membuat komentar satu baris di JavaScript?", listOf("/* komentar */", "// komentar", "<!-- komentar -->", "# komentar"), "// komentar"),
                    QuestionModel("Fungsi dari `document.getElementById()` adalah?", listOf("Menambahkan elemen", "Menghapus elemen", "Mengambil elemen berdasarkan ID", "Menyimpan elemen ke database"), "Mengambil elemen berdasarkan ID"),
                    QuestionModel("Tipe data `boolean` hanya memiliki nilai?", listOf("True dan False", "0 dan 1", "Ya dan Tidak", "Benar dan Salah"), "True dan False"),
                    QuestionModel("Sintaks yang benar untuk membuat variabel adalah?", listOf("let x = 5;", "variable x = 5;", "x := 5;", "v x = 5;"), "let x = 5;"),
                    QuestionModel("Bagaimana cara memanggil fungsi bernama `hitung()`?", listOf("call hitung;", "hitung();", "run hitung();", "hitung:"), "hitung();"),
                    QuestionModel("Metode yang digunakan untuk menambahkan item ke array?", listOf("append()", "add()", "push()", "insert()"), "push()"),
                    QuestionModel("Operator perbandingan untuk 'sama dengan' adalah?", listOf("==", "=", "===", "!="), "=="),
                    QuestionModel("Event `onclick` dipicu saat?", listOf("Mouse diarahkan", "Elemen diklik", "Halaman dimuat", "Form dikirim"), "Elemen diklik"),
                    QuestionModel("Bagaimana cara mengecek panjang string `text`?", listOf("text.length", "text.size()", "text.count()", "text.length()"), "text.length"),
                    QuestionModel("Sintaks `if` yang benar di JavaScript?", listOf("if x > y then", "if (x > y)", "if x > y:", "if x > y {}"), "if (x > y)"),
                    QuestionModel("Tipe data array ditandai dengan tanda?", listOf("{}", "[]", "()", "<>"), "[]"),
                    QuestionModel("Event `onchange` sering digunakan pada elemen?", listOf("div", "input", "select", "button"), "select"),
                    QuestionModel("Nilai default dari variabel yang belum diberi nilai?", listOf("null", "0", "undefined", "empty"), "undefined"),
                    QuestionModel("Fungsi `console.log()` digunakan untuk?", listOf("Menampilkan pesan di HTML", "Menampilkan log di console", "Menjalankan script", "Menyimpan file"), "Menampilkan log di console"),
                    QuestionModel("JavaScript dijalankan di?", listOf("Client (browser)", "Server", "Database", "Terminal"), "Client (browser)"),
                    QuestionModel("Metode untuk menghapus elemen terakhir dari array?", listOf("pop()", "shift()", "remove()", "cut()"), "pop()"),
                    QuestionModel("Keyword `const` digunakan untuk?", listOf("Deklarasi variabel tetap", "Looping", "Menentukan class", "Mendeklarasi fungsi"), "Deklarasi variabel tetap"),
                    QuestionModel("Ekstensi file JavaScript adalah?", listOf(".js", ".java", ".script", ".jsx"), ".js")
                )),

            QuizModel(id = "4", title = "Basis Data", subtitle = "Pengenalan Basis Data", time = "10", questions = listOf(
                QuestionModel("Apa itu basis data?", listOf("Kumpulan tabel", "Kumpulan data terorganisir", "File excel", "Harddisk"), "Kumpulan data terorganisir"),
                QuestionModel("Apa kepanjangan dari DBMS?", listOf("Data Base Management System", "Data Backup Main Server", "Device Binary Memory System", "Database Basic Mode Server"), "Data Base Management System"),
                QuestionModel("Contoh DBMS berikut ini, kecuali?", listOf("MySQL", "Oracle", "MongoDB", "Photoshop"), "Photoshop"),
                QuestionModel("Perintah SQL untuk menampilkan data adalah?", listOf("GET", "SHOW", "SELECT", "DISPLAY"), "SELECT"),
                QuestionModel("Primary key digunakan untuk?", listOf("Membuat tabel", "Menghubungkan dua tabel", "Mengidentifikasi data secara unik", "Menghapus data"), "Mengidentifikasi data secara unik"),
                QuestionModel("Perintah untuk menghapus tabel dalam SQL adalah?", listOf("DELETE", "REMOVE", "DROP", "CLEAR"), "DROP"),
                QuestionModel("Fungsi dari foreign key adalah?", listOf("Menghapus data", "Menghubungkan tabel", "Mengurutkan data", "Menambah kolom"), "Menghubungkan tabel"),
                QuestionModel("Jenis relasi satu ke banyak disebut?", listOf("One to one", "Many to many", "One to many", "One to none"), "One to many"),
                QuestionModel("Apa itu normalisasi dalam basis data?", listOf("Penghapusan data", "Penyimpanan data", "Proses mengorganisasi data untuk mengurangi duplikasi", "Pemulihan data"), "Proses mengorganisasi data untuk mengurangi duplikasi"),
                QuestionModel("Perintah SQL untuk menambah data ke tabel adalah?", listOf("INSERT INTO", "ADD TO", "APPEND", "JOIN"), "INSERT INTO"),
                QuestionModel("Perintah untuk memperbarui data adalah?", listOf("SET", "UPDATE", "MODIFY", "CHANGE"), "UPDATE"),
                QuestionModel("Apa fungsi dari WHERE dalam SQL?", listOf("Menentukan kondisi", "Menentukan tabel", "Menentukan kolom", "Menentukan hasil"), "Menentukan kondisi"),
                QuestionModel("Jenis data 'VARCHAR' digunakan untuk menyimpan?", listOf("Angka", "Teks", "Tanggal", "Boolean"), "Teks"),
                QuestionModel("Apa hasil dari query SELECT COUNT(*)?", listOf("Jumlah baris", "Jumlah kolom", "Jumlah tabel", "Jumlah karakter"), "Jumlah baris"),
                QuestionModel("JOIN digunakan untuk?", listOf("Menggabungkan dua tabel", "Menghapus tabel", "Menyalin tabel", "Membuat tabel baru"), "Menggabungkan dua tabel"),
                QuestionModel("Index dalam basis data digunakan untuk?", listOf("Mengurutkan data", "Mempercepat pencarian", "Menghapus data", "Menyimpan backup"), "Mempercepat pencarian"),
                QuestionModel("Perintah SQL untuk membuat tabel adalah?", listOf("CREATE TABLE", "MAKE TABLE", "NEW TABLE", "TABLE ADD"), "CREATE TABLE"),
                QuestionModel("Relasi antar tabel diatur melalui?", listOf("Primary key dan Foreign key", "SELECT dan INSERT", "WHERE dan GROUP BY", "DELETE dan DROP"), "Primary key dan Foreign key"),
                QuestionModel("Kunci utama tidak boleh bernilai?", listOf("0", "1", "NULL", "Teks"), "NULL"),
                QuestionModel("Query untuk mengambil data unik?", listOf("SELECT DISTINCT", "SELECT UNIQUE", "SELECT SINGLE", "SELECT ONLY"), "SELECT DISTINCT")
            )),

            QuizModel(
                id = "5", title = "Teknik Kompilasi", subtitle = "Dasar-Dasar Teknik Kompilasi", time = "20", questions = listOf(
                    QuestionModel("Apa tugas utama dari kompiler?", listOf("Menjalankan program", "Mengedit kode sumber", "Menerjemahkan kode sumber ke kode mesin", "Menghapus error"), "Menerjemahkan kode sumber ke kode mesin"),
                    QuestionModel("Tahapan pertama dalam proses kompilasi adalah?", listOf("Parsing", "Lexical Analysis", "Optimization", "Code Generation"), "Lexical Analysis"),
                    QuestionModel("Token dalam kompilasi dihasilkan oleh tahap?", listOf("Syntax Analysis", "Lexical Analysis", "Semantic Analysis", "Code Optimization"), "Lexical Analysis"),
                    QuestionModel("Parser digunakan untuk?", listOf("Mengoptimasi kode", "Menganalisis struktur sintaks", "Menyimpan data", "Menulis program"), "Menganalisis struktur sintaks"),
                    QuestionModel("Simbol tabel digunakan untuk menyimpan?", listOf("Syntax error", "Informasi variabel dan fungsi", "Instruksi mesin", "Komentar program"), "Informasi variabel dan fungsi"),
                    QuestionModel("Apa itu AST dalam teknik kompilasi?", listOf("Automatic Syntax Test", "Abstract Syntax Tree", "Asynchronous Stack Table", "Applied Syntax Token"), "Abstract Syntax Tree"),
                    QuestionModel("Semantic analysis bertugas untuk?", listOf("Menghapus spasi kosong", "Memastikan makna kode benar", "Mengubah token", "Menambahkan komentar"), "Memastikan makna kode benar"),
                    QuestionModel("Intermediate Code biasa berbentuk?", listOf("Bahasa Java", "Bahasa mesin", "Kode tingkat menengah", "Teks biasa"), "Kode tingkat menengah"),
                    QuestionModel("Tahap optimisasi bertujuan untuk?", listOf("Mempercantik kode", "Menjalankan program", "Mengurangi resource yang digunakan", "Membuat error"), "Mengurangi resource yang digunakan"),
                    QuestionModel("Contoh bahasa pemrograman yang biasa dikompilasi adalah?", listOf("HTML", "Python", "Java", "CSS"), "Java"),
                    QuestionModel("Lexeme adalah?", listOf("Error log", "Token yang dianalisis", "Unit terkecil dalam program", "Kode lengkap"), "Unit terkecil dalam program"),
                    QuestionModel("Compiler vs Interpreter, bedanya apa?", listOf("Compiler mengeksekusi langsung, interpreter tidak", "Interpreter menerjemahkan per baris, compiler sekaligus", "Keduanya identik", "Compiler hanya untuk HTML"), "Interpreter menerjemahkan per baris, compiler sekaligus"),
                    QuestionModel("CFG singkatan dari?", listOf("Context Free Grammar", "Control Flow Graph", "Code Free Graph", "Compile File Generator"), "Context Free Grammar"),
                    QuestionModel("Backtracking biasanya terjadi pada tahap?", listOf("Lexical", "Parsing", "Semantic", "Code Generation"), "Parsing"),
                    QuestionModel("Output akhir dari compiler adalah?", listOf("Kode sumber", "Token", "Kode mesin", "Komentar"), "Kode mesin"),
                    QuestionModel("Tools compiler seperti Lex dan Yacc digunakan untuk?", listOf("Lex = parser, Yacc = lexer", "Lex = lexer, Yacc = parser", "Keduanya untuk debug", "Keduanya untuk interpretasi"), "Lex = lexer, Yacc = parser"),
                    QuestionModel("Apa fungsi dari DFA dalam kompilasi?", listOf("Parsing ekspresi", "Mengatur sintaks", "Menganalisis leksikal", "Menampilkan hasil"), "Menganalisis leksikal"),
                    QuestionModel("Error semantik terjadi jika?", listOf("Variabel tidak dideklarasikan", "Kurung kurawal hilang", "Titik koma lupa", "File tidak ditemukan"), "Variabel tidak dideklarasikan"),
                    QuestionModel("Instruksi tiga alamat adalah contoh dari?", listOf("Syntax tree", "Lexeme", "Intermediate code", "Error log"), "Intermediate code"),
                    QuestionModel("Tahapan terakhir dari compiler adalah?", listOf("Lexical", "Parsing", "Code generation", "Syntax analysis"), "Code generation")
                )),

            QuizModel(id = "6", title = "Statistika", subtitle = "Dasar-Dasar Statistika", time = "20", questions = listOf(
                    QuestionModel("Apa itu statistika?", listOf("Ilmu data", "Ilmu menghitung uang", "Ilmu menghitung bangunan", "Ilmu tentang mesin"), "Ilmu data"),
                    QuestionModel("Data kuantitatif adalah?", listOf("Data berbentuk angka", "Data berbentuk kata", "Data dari hasil wawancara", "Data gambar"), "Data berbentuk angka"),
                    QuestionModel("Rata-rata dari 2, 4, 6, 8, 10 adalah?", listOf("5", "6", "7", "8"), "6"),
                    QuestionModel("Median dari 1, 3, 5, 7, 9 adalah?", listOf("3", "4", "5", "6"), "5"),
                    QuestionModel("Modus dari data 2, 4, 4, 6, 8 adalah?", listOf("2", "4", "6", "8"), "4"),
                    QuestionModel("Ukuran penyebaran yang paling umum adalah?", listOf("Median", "Range", "Rata-rata", "Frekuensi"), "Range"),
                    QuestionModel("Range dari data 3, 7, 10, 12 adalah?", listOf("7", "9", "10", "12"), "9"),
                    QuestionModel("Simbol dari rata-rata populasi adalah?", listOf("x̄", "μ", "σ", "π"), "μ"),
                    QuestionModel("Simbol standar deviasi populasi adalah?", listOf("x̄", "μ", "σ", "s"), "σ"),
                    QuestionModel("Apa itu inferensi statistik?", listOf("Membuat grafik", "Mengumpulkan data", "Mengambil kesimpulan dari sampel", "Menyusun data"), "Mengambil kesimpulan dari sampel"),
                    QuestionModel("Jenis data berikut yang termasuk kualitatif adalah?", listOf("Berat badan", "Jenis kelamin", "Usia", "Tinggi badan"), "Jenis kelamin"),
                    QuestionModel("Tabel distribusi frekuensi digunakan untuk?", listOf("Menjumlahkan data", "Mengelompokkan data", "Menyusun grafik", "Membuat rata-rata"), "Mengelompokkan data"),
                    QuestionModel("Histogram digunakan untuk?", listOf("Data kualitatif", "Data numerik berkelompok", "Teks", "Data teks"), "Data numerik berkelompok"),
                    QuestionModel("Grafik batang digunakan untuk?", listOf("Data interval", "Data nominal", "Data kontinyu", "Data diskrit"), "Data nominal"),
                    QuestionModel("Ukuran letak data terdiri dari?", listOf("Mean, median, modus", "Kuartil, desil, persentil", "Range, simpangan baku", "Frekuensi"), "Kuartil, desil, persentil"),
                    QuestionModel("Jika simpangan baku kecil, maka data?", listOf("Menyebar luas", "Beragam", "Terkonsentrasi di tengah", "Tidak teratur"), "Terkonsentrasi di tengah"),
                    QuestionModel("Jenis penyajian data yang menggunakan titik-titik disebut?", listOf("Histogram", "Poligon", "Ogive", "Diagram garis"), "Poligon"),
                    QuestionModel("Persentil ke-50 sama dengan?", listOf("Mean", "Median", "Modus", "Kuartil 1"), "Median"),
                    QuestionModel("Berikut ini termasuk ukuran pemusatan data, kecuali?", listOf("Mean", "Median", "Modus", "Range"), "Range"),
                    QuestionModel("Simbol untuk rata-rata sampel adalah?", listOf("μ", "σ", "x̄", "n"), "x̄")
                )),

            QuizModel(id = "7", title = "Sistem Pakar", subtitle = "Pengenalan dan Konsep Dasar Sistem Pakar", time = "10", questions = listOf(
                    QuestionModel("Apa itu sistem pakar?", listOf("Sistem perangkat lunak umum", "Sistem yang meniru keahlian manusia", "Sistem basis data", "Sistem operasi"), "Sistem yang meniru keahlian manusia"),
                    QuestionModel("Komponen utama sistem pakar adalah, kecuali?", listOf("Basis pengetahuan", "Mesin inferensi", "Antarmuka pengguna", "Antivirus"), "Antivirus"),
                    QuestionModel("Fungsi mesin inferensi adalah?", listOf("Menampilkan gambar", "Meniru logika pakar", "Menyimpan data", "Menghapus aturan"), "Meniru logika pakar"),
                    QuestionModel("Basis pengetahuan berisi?", listOf("Prosedur aplikasi", "Fakta dan aturan", "File gambar", "Data pengguna"), "Fakta dan aturan"),
                    QuestionModel("Contoh sistem pakar di bidang medis adalah?", listOf("MYCIN", "Photoshop", "Windows", "Excel"), "MYCIN"),
                    QuestionModel("Backward chaining dimulai dari?", listOf("Kesimpulan ke fakta", "Fakta ke aturan", "Aturan ke kesimpulan", "Grafik ke tabel"), "Kesimpulan ke fakta"),
                    QuestionModel("Forward chaining dimulai dari?", listOf("Kesimpulan", "Fakta", "Tabel", "Interface"), "Fakta"),
                    QuestionModel("Salah satu kelebihan sistem pakar adalah?", listOf("Murah dan tidak perlu internet", "Konsisten dan tidak lelah", "Tidak butuh komputer", "Tidak perlu debugging"), "Konsisten dan tidak lelah"),
                    QuestionModel("Rule-based system menggunakan?", listOf("Algoritma genetik", "Aturan if-then", "Machine learning", "Statistik"), "Aturan if-then"),
                    QuestionModel("Tujuan sistem pakar adalah?", listOf("Menggantikan programmer", "Mengambil keputusan seperti pakar", "Mengedit data pengguna", "Menjalankan sistem operasi"), "Mengambil keputusan seperti pakar"),
                    QuestionModel("Sistem pakar sering disebut juga sebagai?", listOf("Smartphone", "Expert system", "Operating system", "Database"), "Expert system"),
                    QuestionModel("Kelemahan sistem pakar?", listOf("Tidak pernah lelah", "Tergantung pada pengetahuan awal", "Selalu benar", "Tidak menggunakan basis data"), "Tergantung pada pengetahuan awal"),
                    QuestionModel("Apa itu knowledge acquisition?", listOf("Proses debugging", "Proses memperoleh pengetahuan", "Proses instalasi", "Proses login"), "Proses memperoleh pengetahuan"),
                    QuestionModel("Representasi pengetahuan dengan jaringan disebut?", listOf("Decision tree", "Frame", "Semantic network", "Rule base"), "Semantic network"),
                    QuestionModel("Contoh sistem pakar dalam pertanian?", listOf("DSSAT", "Windows", "LINUX", "Oracle"), "DSSAT"),
                    QuestionModel("Inference engine bekerja berdasarkan?", listOf("HTML", "PHP", "Aturan logika", "Komputasi numerik"), "Aturan logika"),
                    QuestionModel("Cara sistem pakar menjelaskan keputusan disebut?", listOf("Trace", "Explanation facility", "Backpropagation", "Simulation"), "Explanation facility"),
                    QuestionModel("Fakta dalam sistem pakar sering disimpan sebagai?", listOf("SQL", "Prolog", "Aturan", "Database relasional"), "Aturan"),
                    QuestionModel("Bahasa pemrograman umum untuk sistem pakar adalah?", listOf("Python", "LISP dan Prolog", "C#", "Pascal"), "LISP dan Prolog"),
                    QuestionModel("Knowledge base disebut juga sebagai?", listOf("Memory", "Database", "Gudang pengetahuan", "User interface"), "Gudang pengetahuan")
                )),

            QuizModel(id = "8", title = "Sistem Operasi", subtitle = "Dasar Sistem Operasi Komputer", time = "10", questions = listOf(
                    QuestionModel("Apa itu sistem operasi?", listOf("Aplikasi pengolah kata", "Perangkat lunak penghubung antara user dan hardware", "Program musik", "Aplikasi desain"), "Perangkat lunak penghubung antara user dan hardware"),
                    QuestionModel("Contoh sistem operasi adalah, kecuali?", listOf("Linux", "Windows", "Android", "Photoshop"), "Photoshop"),
                    QuestionModel("Tugas utama sistem operasi adalah?", listOf("Menjual komputer", "Menjadikan komputer lambat", "Mengelola perangkat keras", "Menjalankan PowerPoint"), "Mengelola perangkat keras"),
                    QuestionModel("Kernel adalah?", listOf("Bagian utama dari OS", "Bagian dari browser", "Nama prosesor", "Jenis file"), "Bagian utama dari OS"),
                    QuestionModel("Sistem operasi multitasking artinya?", listOf("Bisa bermain game", "Bisa menjalankan banyak program sekaligus", "Tidak bisa shutdown", "Berbasis DOS"), "Bisa menjalankan banyak program sekaligus"),
                    QuestionModel("Contoh sistem operasi open source adalah?", listOf("Windows", "MacOS", "Linux", "Android Studio"), "Linux"),
                    QuestionModel("Proses dalam sistem operasi adalah?", listOf("Program dalam keadaan dieksekusi", "File musik", "File dokumen", "File PDF"), "Program dalam keadaan dieksekusi"),
                    QuestionModel("Scheduler berfungsi untuk?", listOf("Mencetak dokumen", "Menjadwalkan eksekusi proses", "Menghapus virus", "Menyalin file"), "Menjadwalkan eksekusi proses"),
                    QuestionModel("Manajemen memori bertugas untuk?", listOf("Mencari file", "Mengelola RAM", "Menampilkan layar", "Menambah aplikasi"), "Mengelola RAM"),
                    QuestionModel("Deadlock terjadi saat?", listOf("Program crash", "Dua atau lebih proses saling menunggu", "Virus menyerang", "Jaringan lambat"), "Dua atau lebih proses saling menunggu"),
                    QuestionModel("File system digunakan untuk?", listOf("Menjalankan virus", "Mengelola penyimpanan dan file", "Meningkatkan RAM", "Membuka internet"), "Mengelola penyimpanan dan file"),
                    QuestionModel("Apa itu thread?", listOf("Komponen RAM", "Proses ringan dalam program", "Perangkat keras", "Sistem database"), "Proses ringan dalam program"),
                    QuestionModel("Virtual memory memungkinkan?", listOf("Mematikan komputer otomatis", "Menjalankan program lebih besar dari RAM", "Mempercepat internet", "Menghapus file otomatis"), "Menjalankan program lebih besar dari RAM"),
                    QuestionModel("Jenis sistem operasi untuk server?", listOf("Windows Home", "Linux Server", "Windows XP", "DOS"), "Linux Server"),
                    QuestionModel("Perintah dasar pada terminal Linux adalah?", listOf("cd, ls, mkdir", "open, close, play", "file, path, copy", "click, double click"), "cd, ls, mkdir"),
                    QuestionModel("Sistem operasi real-time digunakan untuk?", listOf("Multimedia", "Game", "Sistem kritikal seperti roket", "Belajar online"), "Sistem kritikal seperti roket"),
                    QuestionModel("Device driver berfungsi untuk?", listOf("Menghubungkan hardware dengan sistem operasi", "Menambah RAM", "Menghapus data", "Menjalankan game"), "Menghubungkan hardware dengan sistem operasi"),
                    QuestionModel("Sistem operasi mobile populer adalah?", listOf("Android dan iOS", "Windows dan Linux", "Excel dan Word", "Ubuntu dan RedHat"), "Android dan iOS"),
                    QuestionModel("Antarmuka pengguna (UI) digunakan untuk?", listOf("Komunikasi pengguna dengan sistem", "Mempercepat hardware", "Menghubungkan printer", "Menambah baterai"), "Komunikasi pengguna dengan sistem"),
                    QuestionModel("Contoh sistem operasi berbasis GUI?", listOf("Linux CLI", "Windows", "Bash", "DOS"), "Windows")
                )),

            QuizModel(id = "9", title = "OOP (Pemrograman Berorientasi Objek)", subtitle = "Dasar OOP dalam Pemrograman", time = "10", questions = listOf(
                    QuestionModel("Apa kepanjangan dari OOP?", listOf("Object Oriented Programming", "Open Operation Programming", "Overload Oriented Process", "Output Of Program"), "Object Oriented Programming"),
                    QuestionModel("Konsep utama OOP yang bukan termasuk adalah?", listOf("Encapsulation", "Polymorphism", "Inheritance", "Compilation"), "Compilation"),
                    QuestionModel("Apa itu class dalam OOP?", listOf("Tipe data primitif", "Blueprint atau cetakan objek", "Fungsi utama program", "Looping structure"), "Blueprint atau cetakan objek"),
                    QuestionModel("Apa itu objek dalam OOP?", listOf("Variabel", "Instance dari class", "Method dari class", "Array dari string"), "Instance dari class"),
                    QuestionModel("Konsep menyembunyikan data disebut?", listOf("Polymorphism", "Encapsulation", "Overriding", "Looping"), "Encapsulation"),
                    QuestionModel("Keyword untuk mewariskan class di Java adalah?", listOf("inherit", "extends", "implement", "instanceof"), "extends"),
                    QuestionModel("Polymorphism memungkinkan?", listOf("Mengubah bentuk objek secara dinamis", "Menghapus objek", "Menambah memori", "Menjalankan compiler"), "Mengubah bentuk objek secara dinamis"),
                    QuestionModel("Apa itu overloading?", listOf("Memanggil method dari superclass", "Membuat dua method dengan nama sama tapi parameter berbeda", "Menghapus constructor", "Menjalankan loop dalam class"), "Membuat dua method dengan nama sama tapi parameter berbeda"),
                    QuestionModel("Overriding terjadi saat?", listOf("Method superclass ditulis ulang di subclass", "Method dibuat dua kali dalam class", "Loop berulang", "Variable sama di dua tempat"), "Method superclass ditulis ulang di subclass"),
                    QuestionModel("Constructor digunakan untuk?", listOf("Menghapus objek", "Mengisi data awal objek", "Menghentikan program", "Menginisialisasi loop"), "Mengisi data awal objek"),
                    QuestionModel("Default access modifier di Java adalah?", listOf("public", "private", "protected", "package-private"), "package-private"),
                    QuestionModel("Apa itu interface dalam OOP?", listOf("Class lengkap", "Blueprint tanpa implementasi method", "Array of objects", "Loop class"), "Blueprint tanpa implementasi method"),
                    QuestionModel("Keyword untuk implementasi interface?", listOf("extends", "override", "implements", "instanceof"), "implements"),
                    QuestionModel("Super() digunakan untuk?", listOf("Memanggil constructor dari superclass", "Membuat objek baru", "Menjalankan main()", "Membuat array"), "Memanggil constructor dari superclass"),
                    QuestionModel("Getter dan Setter digunakan untuk?", listOf("Looping data", "Mengakses dan mengubah data privat", "Menambah class", "Menghapus objek"), "Mengakses dan mengubah data privat"),
                    QuestionModel("Final class berarti?", listOf("Tidak bisa diwariskan", "Selalu public", "Tidak bisa dibuat objek", "Hanya bisa satu file"), "Tidak bisa diwariskan"),
                    QuestionModel("Apa itu inheritance?", listOf("Pewarisan sifat dari superclass ke subclass", "Pembuatan method", "Deklarasi variabel", "Penggabungan array"), "Pewarisan sifat dari superclass ke subclass"),
                    QuestionModel("Encapsulation menjaga?", listOf("Modularitas class", "Keamanan data", "Penggunaan RAM", "Akses Internet"), "Keamanan data"),
                    QuestionModel("Instanceof digunakan untuk?", listOf("Memeriksa tipe objek", "Membuat objek", "Menghapus objek", "Menambah memory"), "Memeriksa tipe objek"),
                    QuestionModel("Apa itu abstract class?", listOf("Class tanpa method", "Class dengan satu atau lebih method abstrak", "Class tidak dapat dibuat objek", "Class hanya berisi variabel"), "Class dengan satu atau lebih method abstrak")
                ))
        )


        quizListAdapter = QuizListAdapter(fullQuizList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = quizListAdapter

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtered = fullQuizList.filter {
                    it.title.contains(s.toString(), ignoreCase = true)
                }
                quizListAdapter.filterList(filtered)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
