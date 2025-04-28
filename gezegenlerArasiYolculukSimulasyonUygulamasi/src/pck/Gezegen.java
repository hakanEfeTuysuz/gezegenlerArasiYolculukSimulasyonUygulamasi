/** 
* 
* @author Hakan Efe Tüysüz hakanefetuysuz@gamil.com 
* @since 27.04.2025
* <p> 
* Bu sınıfta gezegenler hakkında bilgiler tutuluyor
* </p> 
*/
package pck;

public class Gezegen {
	private String ad;
    private int gunSaat; // bir gün kaç saat
    private String tarih; // örn: "5.10.2020"

    public Gezegen(String ad, int gunSaat, String tarih) {
        this.ad = ad;
        this.gunSaat = gunSaat;
        this.tarih = tarih;
    }

    public String getAd() {
        return ad;
    }

    public int getGunSaat() {
        return gunSaat;
    }

    public String getTarih() {
        return tarih;
    }

    public String toString() {
        return "Gezegen: " + ad + ", Gün: " + gunSaat + " saat, Tarih: " + tarih;
    }
}
