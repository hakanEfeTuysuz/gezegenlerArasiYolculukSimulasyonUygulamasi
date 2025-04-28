/** 
* 
* @author Hakan Efe Tüysüz hakanefetuysuz@gamil.com 
* @since 27.04.2025
* <p> 
* txt dosyalarından ilgili bilgiler çekilir.
* </p> 
*/
package pck;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DosyaOkuma {
	public static List<Kisi> kisileriOku(String dosyaYolu) {
        List<Kisi> kisiler = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;

            while ((satir = br.readLine()) != null) {
                String[] parcalar = satir.split("#");
                if (parcalar.length == 4) {
                    String isim = parcalar[0];
                    int yas = Integer.parseInt(parcalar[1]);
                    int kalanOmur = Integer.parseInt(parcalar[2]);
                    String uzayAraci = parcalar[3];

                    Kisi kisi = new Kisi(isim, yas, kalanOmur, uzayAraci);
                    kisiler.add(kisi);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatasi: " + e.getMessage());
        }

        return kisiler;
    }

    public static List<Gezegen> gezegenleriOku(String dosyaYolu) {
        List<Gezegen> gezegenler = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parcalar = satir.split("#");
                if (parcalar.length == 3) {
                    String ad = parcalar[0];
                    int gunSaat = Integer.parseInt(parcalar[1]);
                    String tarih = parcalar[2];

                    Gezegen gezegen = new Gezegen(ad, gunSaat, tarih);
                    gezegenler.add(gezegen);
                }
            }
        } catch (IOException e) {
            System.out.println("Gezegen dosyasi okuma hatasi: " + e.getMessage());
        }

        return gezegenler;
    }

    public static List<UzayAraci> araclariOku(String dosyaYolu) {
        List<UzayAraci> araclar = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parcalar = satir.split("#");
                if (parcalar.length == 5) {
                    String ad = parcalar[0];
                    String cikisGezegen = parcalar[1];
                    String varisGezegen = parcalar[2];
                    String cikisTarihi = parcalar[3];
                    int mesafe = Integer.parseInt(parcalar[4]);

                    UzayAraci arac = new UzayAraci(ad, cikisGezegen, varisGezegen, cikisTarihi, mesafe);
                    araclar.add(arac);
                }
            }
        } catch (IOException e) {
            System.out.println("Arac dosyasi okuma hatasi: " + e.getMessage());
        }

        return araclar;
    }
}
