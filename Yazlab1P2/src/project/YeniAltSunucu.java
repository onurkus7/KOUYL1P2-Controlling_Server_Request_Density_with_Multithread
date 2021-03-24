
package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YeniAltSunucu implements Runnable {

    final int AltSunucuIstekKapasitesi;
    private int AltSunucuIstek;
    Project mt;

    public YeniAltSunucu(int gelenAltSunucuIstek) {
        // AltSunucuIstekKapasitesi=5000;
        AltSunucuIstekKapasitesi = 500;

        AltSunucuIstek = gelenAltSunucuIstek;
        mt = new Project();
    }

    public int getAltSunucuIstek() {
        return AltSunucuIstek;
    }

    public void setAltSunucuIstek(int AltSunucuIstek) {
        this.AltSunucuIstek = AltSunucuIstek;
    }

    @Override
    public void run() {
        Random ran = new Random();

        int rasgeleSayi;
        float AltSunucuIstekYuzde;
        float AltSunucuSiniri = (AltSunucuIstekKapasitesi / 100) * 70;

        while (getAltSunucuIstek() < AltSunucuSiniri) {
            synchronized (mt) {
                System.out.println("\n");
                try {

                    AltSunucuIstekYuzde = (float) (100 * getAltSunucuIstek()) / AltSunucuIstekKapasitesi;
                    System.out.println("Alt Sunucu Yeni İstek: " + getAltSunucuIstek());
                    System.out.println("Alt Sunucu Yeni İstek Yüzde: %" + AltSunucuIstekYuzde);
                    rasgeleSayi = ran.nextInt(250);
                    rasgeleSayi=rasgeleSayi+250;
                    System.out.println("Geri Dönüş Yapılan İşlem Sayı: " + rasgeleSayi);
                    if (getAltSunucuIstek() - rasgeleSayi > 0) {
                        setAltSunucuIstek(getAltSunucuIstek() - rasgeleSayi);
                    } else {
                        System.out.println("İşlem Yok");
                        setAltSunucuIstek(0);

                    }
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AltSunucu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("\n");

            }
        }

    }

}