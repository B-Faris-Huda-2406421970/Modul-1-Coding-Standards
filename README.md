# Module 1

## Reflection 1
Clean code principles yang sudah saya implementasikan adalah:
1. Meaningful names: Menggunakan nama variabel yang maknanya jelas. Contoh: productId, updateProduct, productName.
2. Single Responsibility: Suatu fungsi melakukan satu hal saja. Contoh: update() pada ProductServiceImpl.java hanya melakukan satu hal yaitu menyuruh productRepository untuk melakukan update pada suatu product.
3. Dont Repeat Yourself (DRY): Tidak mengulangi hal yang sama dengan membuat suatu fungsi. Contoh: Fungsi findById() pada ProductRepository.java yang mencari suatu product berdasarkan ID. Jika saya ingin mencari suatu product dengan ID tertentu, saya hanya perlu memanggil fungsi tersebut tanpa perlu membuat ulang kode pencarian product dengan ID tertentu.
4. Small functions: Fungsi-fungsi yang saya buat pendek (tidak lebih dari 20 baris)

Secure coding principles yang sudah saya implementasikan adalah output encoding. Contohnya adalah menggunakan th:text pada Thymeleaf yang secara otomatis melakukan encoding saat menampilkan data ke HTML. Penting untuk mencegah serangan XSS.

Hal yang bisa di-improve dari kode saya adalah melakukan input validation dan sanitasi agar tidak terjadi crash/error.
