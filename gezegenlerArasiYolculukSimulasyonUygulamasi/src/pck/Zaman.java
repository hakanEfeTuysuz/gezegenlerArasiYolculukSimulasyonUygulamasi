/** 
* 
* @author Hakan Efe Tüysüz hakanefetuysuz@gamil.com 
* @since 27.04.2025
* <p> 
* Zamanla ile ilgili özelliklerin ve metodların tanımlandığı sınıf dosyasıdır.
* </p> 
*/
package pck;

public class Zaman {
	private int gun;
    private int ay;
    private int yil;
    private int saat; // 0 - (gunSaat - 1)
    private int gunSaat; // bu gezegende bir gün kaç saat?

    public Zaman(String tarih, int gunSaat) {
        String[] parca = tarih.split("\\.");
        this.gun = Integer.parseInt(parca[0]);
        this.ay = Integer.parseInt(parca[1]);
        this.yil = Integer.parseInt(parca[2]);
        this.saat = 0;
        this.gunSaat = gunSaat;
    }

    public void ileriSaat() {
        saat++;
        if (saat >= gunSaat) {
            saat = 0;
            gun++;
            
            int gunSayisi = getGunSayisi(ay, yil);
    
            if (gun > gunSayisi) {
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
        }
    }
    
    // Bu yardımcı metodu da ekliyoruz:
    private int getGunSayisi(int ay, int yil) {
        switch (ay) {
            case 2: // Şubat
                if ((yil % 4 == 0 && yil % 100 != 0) || (yil % 400 == 0)) {
                    return 29; // Artık yıl
                } else {
                    return 28;
                }
            case 4: case 6: case 9: case 11:
                return 30; // Nisan, Haziran, Eylül, Kasım
            default:
                return 31; // Ocak, Mart, Mayıs, Temmuz, Ağustos, Ekim, Aralık
        }
    }
    

    public String tarihSaatYaz() {
        return gun + "." + ay + "." + yil + " - " + String.format("%02d:00", saat);
    }

    public String getTarihOnly() {
        return gun + "." + ay + "." + yil;
    }

    public boolean tarihEsitMi(String tarihStr) {
        return getTarihOnly().equals(tarihStr);
    }

    public String toString() {
        return tarihSaatYaz();
    }
    // Getter metotları eklendi
    public int getGun() {
        return gun;
    }

    public int getAy() {
        return ay;
    }

    public int getYil() {
        return yil;
    }

    public int getSaat() {
        return saat;
    }

    public int getGunSaat() {
        return gunSaat;
    }
}
