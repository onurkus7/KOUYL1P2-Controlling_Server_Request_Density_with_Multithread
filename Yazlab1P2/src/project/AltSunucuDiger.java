
package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AltSunucuDiger implements Runnable {

    final int AltSunucuIstekKapasitesi;
    private int AltSunucuIstek;
    Project mt;
    Arayuz c;
    int SunucuSayisi;

    public AltSunucuDiger(int gelenAltSunucuIstek, Arayuz y, int l) {
        AltSunucuIstekKapasitesi = 5000;
        c = y;
        //AltSunucuIstekKapasitesi=5000;
        AltSunucuIstek = gelenAltSunucuIstek;
        mt = new Project();
        SunucuSayisi = l;
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
        ArrayList<YeniAltSunucu> yeniAltSunucu = new ArrayList<YeniAltSunucu>();
        int rasgeleSayi;
        float AltSunucuIstekYuzde;
        float AltSunucuSiniri = (AltSunucuIstekKapasitesi / 100) * 70;
        SunucuSayisi++;
        while (getAltSunucuIstek() < AltSunucuSiniri) {
            synchronized (mt) {
                System.out.println("\n");
                try {

                    AltSunucuIstekYuzde = (float) (100 * getAltSunucuIstek()) / AltSunucuIstekKapasitesi;
                    System.out.println("Alt Sunucu 2 İstek: " + getAltSunucuIstek());
                    //System.out.println("Alt Sunucu 2 İstek Yüzde: %" + AltSunucuIstekYuzde);
                    if (AltSunucuIstekYuzde != 0) {

                        c.jLabel4.setText("" + SunucuSayisi);
                        c.yuzde = (int) AltSunucuIstekYuzde;
                        c.jProgressBar3.setValue(c.yuzde);
                    }

                    rasgeleSayi = ran.nextInt(250);
                    rasgeleSayi=rasgeleSayi+250;
                    System.out.println("Geri Dönüş Yapılan İşlem Sayı: " + rasgeleSayi);
                    if (getAltSunucuIstek() - rasgeleSayi > 0) {
                        setAltSunucuIstek(getAltSunucuIstek() - rasgeleSayi);
                    } else {
                        System.out.println("İşlem Yok");
                        setAltSunucuIstek(0);

                        c.jLabel4.setText("" + SunucuSayisi);

                    }
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AltSunucu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("\n");
            }
        }
        SunucuSayisi--;
        /*
        if (getAltSunucuIstek() > AltSunucuSiniri) {
            rasgeleSayi = ran.nextInt(getAltSunucuIstek());
            OlusturulanAltSunucuIstekSayisi = rasgeleSayi;
            System.out.println("Yeni Sunucu İstek Sayısı: " + OlusturulanAltSunucuIstekSayisi);
            YeniAltSunucu yeniAlt = new YeniAltSunucu(getAltSunucuIstek() - OlusturulanAltSunucuIstekSayisi);
            yeniAltSunucu.add(yeniAlt);
            for (int i = 0; i < 20; i = i + 2) {
                if (yeniAltSunucu.get(i).getAltSunucuIstek() > AltSunucuIstekYuzde) {
                    yeniAltSunucu.add(yeniAlt);
                }
                if (yeniAltSunucu.get(i).getAltSunucuIstek() == 0) {
                    yeniAltSunucu.remove(i);
                }
            }
        }*/

    }

}