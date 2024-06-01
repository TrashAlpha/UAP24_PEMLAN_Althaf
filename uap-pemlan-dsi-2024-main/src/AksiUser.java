public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
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

    public void lihatSaldo() {
        // Implementasi lihat Saldo
        System.out.println("Saldo anda: " + (int)Akun.getCurrentUser().getSaldo());
    }

    public void pesanFilm() {
        // Implementasi pemesanan film
        System.out.print("Nama Film yang ingin dipesan: ");
        Main.scanner.nextLine();
        String namaFilm = Main.scanner.nextLine();
        Film film = Film.getFilms().get(namaFilm);

        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlahTiket = Main.scanner.nextInt();

        if (film.getStock() < jumlahTiket) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }

        System.out.println("Harga satuan tiket " + (int)film.getPrice());

        double totalHarga = film.getPrice() * jumlahTiket;
        User currentUser = Akun.getCurrentUser();

        if (currentUser.getSaldo() < totalHarga) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki: " + (int)currentUser.getSaldo());
            return;
        }

        currentUser.setSaldo(currentUser.getSaldo() - totalHarga);
        film.setStock(film.getStock() - jumlahTiket);
        currentUser.addPesanan(film, jumlahTiket);
        System.out.println("Tiket berhasil dipesan.");
    }

    public void lihatPesanan() {
        // Implementasi melihat pesanan
        User currentUser = Akun.getCurrentUser();

        if (currentUser.getPesanan().isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
            return;
        }

        for (Pesanan pesanan : currentUser.getPesanan().values()) {
            System.out.println("Film: " + pesanan.getFilm().getName() + " - Jumlah: " + (int)pesanan.getKuantitas() + " - Total Harga: " + (int)(pesanan.getKuantitas() * pesanan.getFilm().getPrice()));
        }
    }
}
