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

2. Menurut saya, workflow saya sudah memenuhi Continous Integration (CI) tetapi belum memenuhi Continous Deployment (CD). CI sudah dipenuhi karena sudah build, testing, dan static code analysis yang dilakukan secara otomatis oleh workflow tersebut. Static code analysis dilakukan melalui PMD dengan ruleset yang saya tambahkan pada config/pmd/ruleset.xml. Saya juga menambahkan feedback loop yang akan menghentikan build jika ada pelanggaran yang terjadi seperti gagal melewati tes. Tidak adanya CD pada workflow karena saya menggunakan Git-driven deployment pada Koyeb. Koyeb akan memeriksa push atau pull request yang ada pada branch main dan melakukan deployment dari commit terakhir tersebut berdasarkan Dockerfile yang saya gunakan.
