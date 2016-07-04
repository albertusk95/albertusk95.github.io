---
layout: post
title: Stack Frame
---

To discuss about this stack frame, we'll see from Assembly language point of view.

Basically, a program has functions which support the execution of that program. That functions run for being called by the previous function. This calling event which is usually called as **calling convention** has a concept related to stack. It means that when every function is called, then there'll be space formed which has these default elements, namely the function's **return address** and **argument values**. In addition, the space created can be also called as *stack frame**. This is an example of a **stack frame**.

<img src="https://github.com/albertusk95/albertusk95.github.io/blob/master/public/img/stackframe00.png?raw=true" alt="Stack Frame" title="https://i1.wp.com/i.stack.imgur.com/Fxn41.png" />





Gambar di atas merupakan ilustrasi stack. Bagian yang berwarna hijau adalah ruang yang dibentuk setelah memanggil fungsi/prosedur Foo(), dimana elemennya berupa return address dan nilai dari parameter Foo().

FYI: Selain register umum seperti %eax, %ebx, dan %edx, pada bahasa Assembly juga memiliki register yang bersifat pointer seperti %esp dan %ebp. Register pointer %esp ini disebut juga sebagai stack pointer yang bekerja khusus untuk pengaturan stack.

Sekarang perhatikan, sesaat setelah Foo() dipanggil, maka %esp menunjuk pada blok berisi “Return address”.

Kemudian, misalkan terdapat instruksi Assembly seperti ini.

    pushl     %ebp                    [1]

    movl     %esp, %ebp         [2]

Kode pada baris [1] berarti memasukkan ebp ke dalam stack. Konsep pemasukkan ini sama seperti konsep Push pada stack umum, yaitu elemen yang baru dimasukkan menjadi Top dari stack tersebut. Pada kasus ini, ebp menjadi top dari stack dan ditempatkan setelah blok berisi Return address. Diperhatikan pula untuk saat ini %esp menunjuk pada blok yang berisi “Saved ebp”

Kode pada baris [2] berarti memindahkan alamat %ebp menjadi alamat %esp, dimana alamat esp sekarang yaitu pada blok berisi “Saved ebp”. Efeknya adalah sekarang %ebp menunjuk pada blok berisi “Saved ebp”.

Lalu, apa yang dapat disimpulkan dari ilustrasi singkat di atas? Dapat dilihat bahwa pointer %esp selalu menunjuk pada blok di dalam stack yang memiliki alamat paling rendah (read: blok terbawah). Jadi, setiap kali kita melakukan Push ke stack, maka otomatis %esp akan berpindah ke blok yang baru saja di Push tersebut. Kesimpulan lainnya adalah baris kode [2] yang berfungsi untuk memindahkan %ebp setelah blok berisi “Return address” adalah untuk berfungsi sebagai base pointer atau dengan kata lain sebagai counter untuk perhitungan alamat pada sebuah stack miliki suatu fungsi/ prosedur. Hal ini ditangani oleh %ebp karena %esp bersifat dinamis, yaitu selalu menempatkan dirinya pada alamat stack paling rendah, sehingga tidak terlalu efektif untuk dijadikan penanda alamat pada stack. Karena %ebp bersifat statis, maka kita dapat mendefinisikan alamat %ebp bernilai 0 dan blok di atas %ebp bernilai kelipatan 4, yaitu blok berisi “Return address” yang memiliki alamat lebih tinggi dari %ebp bernilai %ebp+4, blok berisi “parameter ke-1” bernilai %ebp+8, dst. Untuk blok yang berada di bawah %ebp (yang mana beralamat lebih rendah), maka alamat blok itu dapat dihitung dengan nilai %ebp-4 (1 blok di bawah %ebp), %ebp-8 (2 blok di bawah %ebp), dst. Sebagai info tambahan, kode Assembly nomor [2] di atas akan selalu ada saat fungsi/ prosedur yang bersangkutan dipanggil.

Lalu, apa yang terjadi jika pada fungsi/ prosedur Foo() kita memanggil fungsi/ prosedur lain? Berikut gambarannya.

Misalkan Foo() memanggil Bang(). Maka, untuk kasus ini Foo() dapat dikatakan sebagai fungsi/ prosedur pemanggil Bang() (Caller), dan Bang() dikatakan sebagai fungsi/ prosedur yang dipanggil oleh Foo() (Called). Dari gambar di atas, maka dapat dianalogikan bahwa Foo() memiliki ruang stack bernama “Caller’s Frame” dan Bang() memiliki ruang stack bernama “Current Frame”. Apa artinya? Artinya adalah setelah Foo() memanggil Bang(), maka akan langsung disediakan sebuah ruang stack bagi Bang() dimana konstruksinya sama seperti milik Foo(), yaitu elemen default nya berupa Return address Bang() dan parameter Bang().

Dapat dilihat bahwa sebelum Bang() dipanggil, register pointer %esp berada pada blok berisi “Saved %ebp” pada “Caller’s Frame” setelah menjalankan 2 buah kode Assembly sebelumnya. Setelah Foo() memanggil Bang(), maka terjadi proses Calling dari Foo() ke Bang(). Kemudian 2 buah kode Assembly di atas dijalankan kembali dimana %esp sekarang berada pada blok berisi “Saved %ebp” di dalam stack frame milik Bang(). Pointer %ebp pun sudah dipindahkan ke alamat yang sama dengan %esp untuk dijadikan patokan dalam penentuan alamat sebuah blok di dalam stack.

Jadi, dapat disimpulkan bahwa setiap kali terjadi proses Calling sebuah fungsi/ prosedur oleh fungsi/ prosedur lain, yang dilakukan oleh sistem operasi adalah menyediakan sebuah stack frame (ruang stack) untuk fungsi/ prosedur yang dipanggil tersebut. Letaknya adalah setelah stack frame fungsi/ prosedur pemanggil. Dapat dilihat di sini bahwa stack frame yang baru dibentuk memiliki alamat lebih rendah dari stack frame fungsi/ prosedur pemanggil.

KESIMPULAN

Konsep dasar dari proses Calling sebuah fungsi/ prosedur terhadap fungsi/ prosedur lainnya adalah dengan stack frame, dimana sebuah fungsi/ prosedur yang dipanggil akan memiliki sebuah frame berisi informasi penting seperti parameter, variabel, dan return address. Sebuah frame dikontrol oleh 2 buah register yang bersifat pointer yaitu %ebp (frame base pointer) dan %esp (stack pointer).

Register %ebp ini berfungsi sebagai frame counter yang berfungsi sebagai penunjuk/ penanda alamat dari blok yang terdapat di dalam sebuah frame. Setiap frame yang ada memiliki sebuah %ebp. Ilustrasinya sebagai berikut (sebenarnya gambar di bawah terbalik, yaitu %esp harus berada paling bawah. Silahkan sesuaikan sendiri :))

Kasus pada gambar di atas dapat dianalogikan sebagai berikut. Terdapat 2 buah fungsi Foo() dan Bang(), dimana frame Foo() memanggil Bang(). Ini berarti %esp berada di frame milik Bang(). Maka, cara kerja %ebp sebagai berikut.

Setelah Foo() dipanggil oleh main(), maka Foo() memiliki %ebp yang berada setelah blok berisi “Return address” (blok ke-3 dari bawah). Nilai %ebp di frame Foo() adalah 0 dan blok “Return address” milik Foo() bernilai %ebp+4. Blok berisi “Local variabel” yang beralamat di %ebp-4 adalah variabel yang ada pada Foo(). Kemudian, Foo() memanggil Bang() yang langsung disediakan stack frame oleh sistem operasi di bawah stack frame Foo(), yaitu stack frame yang dimulai dari alamat %ebp-8 jika dilihat dari sudut pandang %ebp milik Foo(). Kemudian setelah 2 kode Assembly di atas dijalankan, maka stack frame Bang() memiliki %ebp juga yang bernilai 0 (dilihat dari sudut pandang frame Bang(). Jika dilihat dari sudut pandang Foo(), maka blok berisi %ebp milik Bang terletak di alamat %ebp-16). Jadi, dapat dilihat bahwa %ebp bekerja sebagai pointer yang nilainya dilihat dari sudut pandang frame sekarang (frame milik fungsi/ prosedur yang sedang berjalan).

Untuk register %esp cara kerjanya yaitu menempatkan diri di blok beralamat terendah.

Untuk pembahasan selanjutnya, kita akan melihat bagaimana cara kerja dari teknik Buffer Overflow.