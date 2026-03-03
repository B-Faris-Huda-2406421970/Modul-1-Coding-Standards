# Module 1

## Reflection 1
Clean code principles yang sudah saya implementasikan adalah:
1. Meaningful names: Menggunakan nama variabel yang maknanya jelas. Contoh: productId, updateProduct, dan productName.
2. Single Responsibility: Suatu fungsi melakukan satu hal saja. Contoh: update() pada ProductServiceImpl.java hanya melakukan satu hal yaitu menyuruh productRepository untuk melakukan update pada suatu product.
3. Dont Repeat Yourself (DRY): Tidak mengulangi logic yang sama dengan membuat suatu fungsi. Contoh: Fungsi findById() pada ProductRepository.java yang mencari suatu product berdasarkan ID. Jika saya ingin mencari suatu product dengan ID tertentu, saya hanya perlu memanggil fungsi tersebut tanpa perlu membuat ulang kode pencarian product dengan ID tertentu.
4. Small functions: Fungsi-fungsi yang saya buat pendek (tidak lebih dari 20 baris)

Secure coding principles yang sudah saya implementasikan adalah output encoding. Contohnya adalah menggunakan th:text pada Thymeleaf yang secara otomatis melakukan encoding saat menampilkan data ke HTML. Penting untuk mencegah serangan XSS.

Hal yang bisa di-improve dari kode saya adalah melakukan input validation dan sanitasi agar tidak terjadi crash/error.

## Reflection 2
1. Rasanya melelahkan untuk membuat banyak unit test, tetapi membuat saya lebih yakin bahwa kode yang saya buat rigid. Menurut saya, jumlah unit test yang perlu dibuat dalam suatu kelas bergantung pada jumlah method yang ada di kelas tersebut, banyaknya percabangan yang ada pada masing-masing method, dan kemungkinan input yang beragam baik yang terlihat normal maupun yang aneh (misal positif, negatif, atau non-angka untuk input angka). Untuk memastikan apakah unit test sudah cukup untuk memverifikasi program kita, kita bisa melihat code coverage yang dihasilkan oleh unit tests tersebut. Apabila code coverage sudah besar (misal > 90%), kita bisa cukup yakin bahwa program tersebut sudah benar untuk kasus-kasus yang telah kita buat. Jika kode saya memiliki coverage 100%, belum tentu kode saya tidak memiliki bug atau error. Bisa jadi saya tidak terpikir kasus yang membuat kode tersebut menjadi error seperti belum validasi input, masalah null pointer, atau suatu bug yang sangat spesifik.

2. Menurut saya, kode tersebut kurang "bersih". Pemisahan kelas Java juga mengurangi kualitas kode. Penyebabnya adalah karena ada kesamaan prosedur setup dan variabel umum yang digunakan oleh keduanya. Contohnya adalah penggunaan base URL yang sama (misal localhost:8080/product). Selain itu, keduanya juga melakukan functional test terhadap Product sehingga lebih baik disatukan saja dalam satu file yang sama. Hal ini sesuai dengan salah satu clean code principles yaitu prinsip Dont Repeat Yourself (DRY).

# Module 2

## Reflection
Public URL hasil deployment terakhir: https://fragile-calypso-hyude-647bd39d.koyeb.app/

1. Isu kualitas kode yang saya temui adalah:
   - Isu: Utility class EshopApplication.java punya constructor non-private.
   Solusi: Saya menambahkan constructor private tersebut. Tapi setelah saya menambahkan private constructor, malah muncul isu selanjutnya yaitu class tersebut hanya berisi private constructor dan mungkin saja final. Solusinya adalah saya menambahkan anotasi @SuppressWarnings() karena defaultnya dari Spring Boot EshopApplication merupakan public class biasa, bukan final.
	- Isu: Field productData pada ProductRepository.java bisa dideklarasikan sebagai final.
   Solusi: Saya menambahkan keyword final setelah visibility sehingga menjadi private final.

2. Menurut saya, workflow saya sudah memenuhi Continuous Integration (CI) dan Continuous Deployment (CD). CI sudah dipenuhi karena sudah ada build, testing, dan static code analysis yang dilakukan secara otomatis oleh workflow tersebut. Static code analysis dilakukan melalui PMD dengan ruleset yang saya tambahkan pada config/pmd/ruleset.xml. Saya juga menambahkan feedback loop yang akan menghentikan build jika ada pelanggaran yang terjadi seperti gagal melewati tahap testing. CD sudah dipenuhi karena ada job "deploy" pada workflow yang akan menginstall Koyeb CLI. Setelah instalasi, workflow akan melakukan deployment ke Koyeb melalui API dengan API Token berupa secrets pada GitHub. Sehingga deployment juga dilakukan secara otomatis (CD).

# Module 3

## Reflection
1. Prinsip yang diaplikasikan pada proyek saya adalah SOLID. Prinsip-prinsip SOLID yaitu:
- Single Responsibility Principle (SRP): Setiap class hanya mengurus suatu hal yang berkaitan dengan class tersebut. Method-method juga dibuat simple dan tidak mengerjakan banyak hal sekaligus. Jika ada banyak hal yang dilakukan oleh suatu method, pecah menjadi method-method yang lebih kecil/modular. Perubahan yang saya lakukan terkait SRP adalah:
	- Memindahkan class CarController dari ProductController.java ke CarController.java
	- Membuat method kecil untuk validasi dan pembuatan ID baru setIDIfNull() untuk method create() pada CarRepositoryImpl.java
	- Menghilangkan temporary variable pada method delete() di ProductRepositoryImpl.java
- Open-Closed Principle (OCP): Suatu class harus terbuka untuk extension (perluasan) tetapi harus tertutup terhadap modification (modifikasi). Perubahan pada proyek terkait OCP adalah membuat interface CarRepository.java dan ProductRepository.java. Jika nantinya akan ada repository untuk model lain (misal Electric Car), kita tinggal melakukan implementasi dari interface CarRepository.java tersebut.
- Liskov Substitution Principle (LSP): Implementasi dari suatu class atau interface harus memenuhi class yang di-implement atau di-extend serta konsisten dan tidak menyebabkan error saat dijalankan dimana saja. Contohnya adalah saya mengimplementasikan seluruh method pada interface CarService di CarServiceImpl.java
- Interface Segregation Principle (ISP): Interface yang dibuat harus bersifat spesifik dan saling berkaitan. Contohnya adalah pada ProductRepository.java, saya hanya melakukan operasi Create, Read, Update, and Delete (CRUD) Product. Tidak ada method lain selain CRUD Product. Jika nanti interface terasa terlalu banyak method dan kurang spesifik, maka saya bisa memecah interface menjadi interface yang lebih kecil dan spesifik.
- Dependency Inversion Principle (DIP): Modul tingkat tinggi tidak boleh bergantung pada modul tingkat rendah. Keduanya harus bergantung pada suatu abstraksi. Contoh pada proyek adalah saya menggunakan abstraksi interface CarRepository yang merupakan penghubung antara service dengan repository. Dengan demikian, service tidak bergantung pada kelas konkrit seperti CarRepositoryImpl.

2. Kelebihan menerapkan SOLID pada proyek:
- Memudahkan testing. Prinsip Dependency Inversion memastikan kita membuat suatu abstraksi sebagai penghubung antara modul tingkat tinggi dengan tingkat rendah. Akibatnya, testing bisa dilakukan secara terisolasi. Misalkan kita bisa melakukan testing pada CarServiceImpl menggunakan repository palsu (Mock) untuk mensimulasikan repository asli.
- Jika ada perubahan class yang digunakan, misal mengubah database, kita hanya perlu membuat class baru yang mengimplementasikan interface repository tersebut. Hal ini bisa dilakukan karena kita sudah menerapkan prinsip OCP sehingga kode pada CarServiceImpl tidak perlu ikut diubah.
- Jika ada perubahan logika bisnis, kita hanya perlu fokus pada suatu method atau class yang ingin diubah. Ini bisa dilakukan karena kita menerapkan prinsip SRP sehingga tiap modul punya tanggung jawab spesifik.
- Kode lebih readable sehingga lebih mudah dipahami. Penggunaan interface dan penerapan prinsip Interface Segregation memudahkan kita jika ingin tau apa yang dilakukan suatu modul secara garis besar.

3. Kekurangan jika tidak mengaplikasikan SOLID pada proyek:
- Satu method bisa melakukan banyak hal sehingga menyulitkan testing akibat banyak case yang harus dicover untuk satu method tersebut
- Method-method terlalu campur aduk satu sama lain sehingga jika ada perubahan logika, bisa saja method lain perlu diubah juga. Selain itu, perubahan pada satu method bisa saja menyebabkan error pada method lainnya.
- Jika fitur sudah banyak sekali, kode akan sulit dirawat (maintainability) karena kode menjadi "Spaghetti Code"
- Jika ada perubahan logika bisnis, kode harus diubah secara menyeluruh karena tidak ada abstraksi yang menjembatani modul tingkat tinggi dengan tingkat rendah. Contohnya jika kita ingin mengubah database, maka kita perlu mengubah kode di repository. Akan tetapi, karena tidak ada penghubung antara service dengan repository, kita mungkin saja perlu mengubah beberapa (atau mungkin semua) potongan kode yang ada di service tersebut
