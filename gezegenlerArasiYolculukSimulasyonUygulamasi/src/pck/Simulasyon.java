/** 
* 
* @author Hakan Efe Tüysüz hakanefetuysuz@gamil.com 
* @since 27.04.2025
* <p> 
* Uzay araçlarının gezegenler arası yolculuğunun simüle edildiği öenmli sınıftır.
* </p> 
*/
package pck;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Simulasyon {
	 private List<Kisi> kisiler;
	    private List<Gezegen> gezegenler;
	    private List<UzayAraci> araclar;
	    private Map<String, Zaman> gezegenZamanMap;
	    private Map<UzayAraci, String> varisTarihi = new HashMap<>();
	    private Map<UzayAraci, List<Kisi>> aracYolcular = new HashMap<>();
	    private Map<UzayAraci, String> aracDurum = new HashMap<>();
	    private Map<UzayAraci, Gezegen> aracCikis = new HashMap<>();
	    private Map<UzayAraci, Gezegen> aracVaris = new HashMap<>();

	    public Simulasyon(List<Kisi> kisiler, List<Gezegen> gezegenler, List<UzayAraci> araclar) {
	        this.kisiler = kisiler;
	        this.gezegenler = gezegenler;
	        this.araclar = araclar;
	        this.gezegenZamanMap = new HashMap<>();

	        for (UzayAraci arac : araclar) {
	            ArrayList<Kisi> yolcular = new ArrayList<>();
	            for (Kisi kisi : kisiler) {
	                if (kisi.getUzayAraciAdi().equals(arac.getAd())) {
	                    yolcular.add(kisi);
	                }
	            }
	            aracYolcular.put(arac, yolcular);

	            for (Gezegen gezegen : gezegenler) {
	                if (gezegen.getAd().equals(arac.getCikisGezegeni())) {
	                    aracCikis.put(arac, gezegen);
	                } else if (gezegen.getAd().equals(arac.getVarisGezegeni())) {
	                    aracVaris.put(arac, gezegen);
	                }
	            }
	            aracDurum.put(arac, "Bekleme");
	        }

	        for (Gezegen g : gezegenler) {
	            gezegenZamanMap.put(g.getAd(), new Zaman(g.getTarih(), g.getGunSaat()));
	        }
	        varisHesapla();
	    }

	    public void varisHesapla() {
	        for (UzayAraci arac : araclar) {
	            Gezegen cgezegen = null;
	            Gezegen vgezegen = null;

	            for (Gezegen gezegen : gezegenler) {

	                if (gezegen.getAd().equals(arac.getCikisGezegeni())) {
	                    cgezegen = gezegen;
	                } else if (gezegen.getAd().equals(arac.getVarisGezegeni())) {
	                    vgezegen = gezegen;
	                }
	            }

	            String aracCikisT = arac.getCikisTarihi();
	            String CikisGezegenT = cgezegen.getTarih(); // aracın bulunduğu gezegenin tarihi
	            int CikisGezegenGs = cgezegen.getGunSaat(); // aracın bulunduğu gezegenin 1 gününün saati
	            int mesafe = arac.getMesafe();
	            int varisGezegenGs = vgezegen.getGunSaat(); // aracın varacağı gezegenin 1 gününün saati
	            String varisGezegenT = vgezegen.getTarih(); // aracın varacağı gezegenin tarihi

	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
	            LocalDate cikisT = LocalDate.parse(CikisGezegenT, formatter);
	            LocalDate varisT = LocalDate.parse(varisGezegenT, formatter);
	            LocalDate araccikisT = LocalDate.parse(aracCikisT, formatter);
	            long fark = ChronoUnit.DAYS.between(cikisT, araccikisT); // A-B
	            long farkGun = (fark * CikisGezegenGs + mesafe) / varisGezegenGs;
	            if ((fark * CikisGezegenGs + mesafe) % varisGezegenGs != 0) {
	                farkGun++;
	            }
	            LocalDate varisTarihi = varisT.plusDays(farkGun);
	            String varisString = varisTarihi.format(formatter);
	            this.varisTarihi.put(arac, varisString);

	            // string aracınvaracağıarih = ((A-B)*C + D)/E + F
	        }

	    }

	    public void baslat() {
	        boolean devam = true;

	        while (devam) {
	            try {
	                Thread.sleep(00);
	                System.out.print("\033[H\033[2J");
	                System.out.flush();
	            } catch (Exception e) {

	            }
	            for (Gezegen gezegen : gezegenler) {
	                Zaman zaman = gezegenZamanMap.get(gezegen.getAd());
	                zaman.ileriSaat();
	            }

	            for (Kisi kisi : kisiler) {
	                kisi.azaltOmur();
	            }

	            for (UzayAraci arac : araclar) {
	                if (aracDurum.get(arac).equals("IMHA")) {
	                    continue;
	                } else if (aracDurum.get(arac).equals("Bekleme")) {

	                    Gezegen cikisGezegen = aracCikis.get(arac);
	                    Zaman cikisZaman = gezegenZamanMap.get(cikisGezegen.getAd());
	                    if (arac.getCikisTarihi().equals(cikisZaman.getTarihOnly())) {
	                        aracDurum.put(arac, "Yolda");
	                    }

	                } else if (aracDurum.get(arac).equals("Yolda")) {
	                    if (arac.azaltMesafe() <= 0) {
	                        aracDurum.put(arac, "Vardı");
	                    }

	                }
	                if (aracYolcular.get(arac).stream().noneMatch(Var -> Var.isAlive())) {
	                    aracDurum.put(arac, "IMHA");
	                }
	            }
	            // Yazdirma
	            System.out.print("gezegenler : \n\t\t\t");
	            for (Gezegen gezegen : gezegenler) {
	                System.out.print("---" + gezegen.getAd() + "---\t");
	            }
	            System.out.print("\n tarih : \t\t");
	            for (Gezegen gezegen : gezegenler) {
	                Zaman zaman = gezegenZamanMap.get(gezegen.getAd());
	                System.out.print(zaman.getTarihOnly()+"\t");
	            }
	            System.out.print("\n nüfus : \t\t");
	            for (Gezegen gezegen : gezegenler) {
	                System.out.print(sayGezegendekiNufus(gezegen)+"\t\t");
	            }
	            System.out.println();

	            int bitti = 0;
	            System.out.println();
	            System.out.println("Araç\tDurum\tÇıkış\tVarış\tMesafe\tVarış Tarihi");
	            for (UzayAraci arac : araclar) {
	                String durum = aracDurum.get(arac);
	                if (durum.equals("IMHA") || durum.equals("Vardı")) {
	                    bitti++;
	                }
	                System.out.println(arac.getAd() + "\t" + durum + "\t" + arac.getCikisGezegeni() + "\t"
	                        + arac.getVarisGezegeni() + "\t"
	                        + (durum.equals("IMHA") ? "--" : arac.getMesafe()) + "\t" +
	                        (durum.equals("IMHA") ? "--" : varisTarihi.get(arac)));

	            }
	            if (bitti == araclar.size()) {
	                devam = false;
	            }
	           
	        }

	    }

	    private int sayGezegendekiNufus(Gezegen gezegen) {
	        int toplam = 0;
	        for (UzayAraci arac : araclar) {
	            if (arac.getCikisGezegeni().equals(gezegen.getAd()) && aracDurum.get(arac).equals("Bekleme")) {
	                for(Kisi kisi : aracYolcular.get(arac)){
	                    if(kisi.isAlive()){
	                        toplam++;
	                    }
	                }
	            } else if (arac.getVarisGezegeni().equals(gezegen.getAd()) && aracDurum.get(arac).equals("Vardı")) {
	                for(Kisi kisi : aracYolcular.get(arac)){
	                    if(kisi.isAlive()){
	                        toplam++;
	                    }
	                }
	            }

	        }
	        return toplam;
	    }

}
