public class AksiAdmin extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi Admin:");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Logout");
        System.out.println("4. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        // Implementasi melihat list film
        System.out.println("Daftar Film yang Tersedia:");
        for (Film film : Film.getFilms().values()) {
            System.out.println(film.getName() + " - " + film.getDescription() + " - Harga: " + (int)film.getPrice() + " - Stok: " + (int)film.getStock());
        }
    }

    public void tambahFilm() {
        // Implementasi menambahkan film
        System.out.print("Nama Film: ");
        Main.scanner.nextLine();
        String namaFilm = Main.scanner.nextLine();
        System.out.print("Deskripsi Film: ");
        String deskripsiFilm = Main.scanner.nextLine();
        System.out.print("Harga Tiket: ");
        double hargaTiket = Main.scanner.nextDouble();
        System.out.print("Stok Tiket: ");
        int stokTiket = Main.scanner.nextInt();

        Film.addFilm(namaFilm, deskripsiFilm, hargaTiket, stokTiket);
    }
}
