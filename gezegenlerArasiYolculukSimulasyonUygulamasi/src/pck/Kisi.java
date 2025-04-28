/** 
* 
* @author Hakan Efe Tüysüz hakanefetuysuz@gamil.com 
* @since 27.04.2025
* <p> 
* Kişilerin bilgilerini tutar ve bazı öenmli metodlara sahiptir
* </p> 
*/
package pck;

public class Kisi {
	 private String isim;
	    private int yas;
	    private int kalanOmur;
	    private String uzayAraciAdi;

	    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
	        this.isim = isim;
	        this.yas = yas;
	        this.kalanOmur = kalanOmur;
	        this.uzayAraciAdi = uzayAraciAdi;
	    }
	    public String getisim(){
	        return isim;
	    }

	    public boolean isAlive() {
	        return kalanOmur > 0;
	    }

	    public void azaltOmur() {
	        if (kalanOmur > 0)
	            kalanOmur--;
	    }

	    public String getUzayAraciAdi() {
	        return uzayAraciAdi;
	    }

	    public String toString() {
	        return isim + " (" + yas + " yasinda, kalan omur: " + kalanOmur + " saat)";
	    }
}
