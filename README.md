# Tugas Praktikum DPBO #6

Dibuat untuk menyelesaikan TP6 Desain Pemrograman Berorientasi Objek (DPBO)

## Janji

Saya Willsoon Tulus Parluhutan Simanjuntak dengan NIM 2404756 mengerjakan evaluasi Tugas Praktikum 6 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. 

Aamiin.

## Desain program

Program ini merupakan program berbasis Swing dengan sebuah JFrame, yang memiliki kelas sebagai berikut:
- App, yang merupakan induk utama dari aplikasi dan memiliki JFrame induk yang juga terkait dengan berbagai JPanel seperti View dan MainMenu
- View, yang merupakan viewport class berbasis JPanel untuk menampilkan UI dan grafik permainan
- MainMenu, yang merupakan class JPanel didalam View untuk menampilkan menu utama sebelum memulai permainan
- Logic, yang merupakan class yang mengimplementasikan ActionListener dan KeyListener untuk alur jalan logika, seperti pergerakan karakter, managemen pipa yang muncul, dsb.
- Player, yang merupakan class entitas yang memiliki atribut pemain, seperti posisi, ukuran, gambar, velocity (akselerasi), dsb.
- Pipe, yang merupakan class entitas yang memiliki atribut sebagai penghalang dalam permainan.
- Audio, yang merupakan class untuk menyederhanakan managemen Sound Effect dalam Java Swing.

## Alur jalan program

Program ini merupakan program re-implementasi dari video game mobile legendaris bernama Flappy Bird menggunakan Java Swing GUI.

Saat program dijalankan, pengguna akan melihat tampilan Main Menu dengan judul game serta tombol untuk memainkan permainan, maupun keluar dari program. 

Jika pengguna mengklik Play (memainkan permainan), pengguna harus menjaga agar karakter tidak menabrak pipa yang akan bermunculan terus-menerus, dengan menekan tombol spasi dan menjaga pergerakan karakter.

Setiap melewati pipa akan menambahkan Skor sebanyak 1 poin. Jika menabrak pipa, maka akan muncul Game Over, pergerakan berhenti hingga restart dengan menekan tombol R di keyboard.

## Legalitas

Program ini merupakan kreasi fan-made untuk tujuan edukasi dan tidak terafiliasi dengan pihak pembuat asli Flappy Bird, yaitu Dong Nguyen dan .GEARS Studio.

## Preview operasional program

https://github.com/user-attachments/assets/92299b32-73e7-425a-afd2-b4299132c8e0

