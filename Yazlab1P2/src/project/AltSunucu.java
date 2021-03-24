
package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AltSunucu implements Runnable {

    final int AltSunucuIstekKapasitesi;
    private int AltSunucuIstek;
    Project mt;
    Arayuz b;
    int SunucuSayisi;

    public AltSunucu(int gelenAltSunucuIstek, Arayuz x, int k) {
        AltSunucuIstekKapasitesi = 5000;
        b = x;
        AltSunucuIstek = gelenAltSunucuIstek;
        mt = new Project();
        SunucuSayisi = k;
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
        int OlusturulanAltSunucuIstekSayisi;
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
                    System.out.println("Alt Sunucu 1 İstek: " + getAltSunucuIstek());
                    //System.out.println("Alt Sunucu 1 İstek Yüzde: %" + AltSunucuIstekYuzde);
                    if (AltSunucuIstekYuzde != 0) {

                        b.jLabel4.setText("" + SunucuSayisi);
                        b.yuzde = (int) AltSunucuIstekYuzde;
                        b.jProgressBar2.setValue(b.yuzde);
                    }

                    rasgeleSayi = ran.nextInt(250);
                    rasgeleSayi=rasgeleSayi+250;
                    System.out.println("Geri Dönüş Yapılan İşlem Sayı: " + rasgeleSayi);
                    if (getAltSunucuIstek() - rasgeleSayi > 0) {
                        setAltSunucuIstek(getAltSunucuIstek() - rasgeleSayi);
                    } else {
                        System.out.println("İşlem Yok");
                        setAltSunucuIstek(0);

                        b.jLabel4.setText("" + SunucuSayisi);

                    }
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AltSunucu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("\n");
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
        SunucuSayisi--;
    }

}