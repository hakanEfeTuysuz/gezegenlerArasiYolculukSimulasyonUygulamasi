/** 
* 
* @author Hakan Efe Tüysüz hakanefetuysuz@gamil.com 
* @since 27.04.2025
* <p> 
* Programın çalıştığı main dosyasıdır.
* </p> 
*/package pck;
import java.util.List;
public class Test {

	public static void main(String[] args) {
		List<Kisi> kisiler = DosyaOkuma.kisileriOku("Kisiler.txt");
        List<Gezegen> gezegenler = DosyaOkuma.gezegenleriOku("Gezegenler.txt");
        List<UzayAraci> araclar = DosyaOkuma.araclariOku("Araclar.txt");

        Simulasyon simulasyon = new Simulasyon(kisiler, gezegenler, araclar);
        simulasyon.baslat();


	}

}
