/** 
* 
* @author Hakan Efe Tüysüz hakanefetuysuz@gamil.com 
* @since 27.04.2025
* <p> 
* Uzay araçlarının bilgilerinin tutulduğu sınıf dosyasıdır.
* </p> 
*/package pck;

public class UzayAraci {
	private String ad;
    private String cikisGezegeni;
    private String varisGezegeni;
    private String cikisTarihi;
    private int mesafe; // saat cinsinden

    public UzayAraci(String ad, String cikisGezegeni, String varisGezegeni, String cikisTarihi, int mesafe) {
        this.ad = ad;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;
    }

    public String getAd() {
        return ad;
    }

    public String getCikisGezegeni() {
        return cikisGezegeni;
    }

    public String getVarisGezegeni() {
        return varisGezegeni;
    }

    public String getCikisTarihi() {
        return cikisTarihi;
    }

    public int getMesafe() {
        return mesafe;
    }

    public String toString() {
        return "Uzay Araci: " + ad + ", " + cikisGezegeni + " -> " + varisGezegeni + ", Çikiş: " + cikisTarihi + ", Mesafe: " + mesafe + " saat";
    }
    public int azaltMesafe(){
        mesafe--;
        return mesafe;
    }
}
