package project;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project {

    final int AnaSunucuIstekKapasitesi;
    private int AnaSunucuIstek;

    public Project() {
        AnaSunucuIstekKapasitesi = 10000;
        AnaSunucuIstek = 0;
    }

    public int getAnaSunucuIstekKapasitesi() {
        return AnaSunucuIstekKapasitesi;
    }

    public int getAnaSunucuIstek() {
        return AnaSunucuIstek;
    }

    public void setAnaSunucuIstek(int AnaSunucuIstek) {
        this.AnaSunucuIstek = AnaSunucuIstek;
    }

    public void run() {

    }

    private int SunucuSayisi = 1;

    public static void main(String[] args) {

        Arayuz a = new Arayuz();
        a.setVisible(true);

        Thread maint = Thread.currentThread();
        Project mt = new Project();
        Random ran = new Random();

        int OlusturulanAltSunucuIstekSayisi = 0;
        int rasgeleSayi;
        float AnaSunucuIstekYuzde;
        float AnaSunucuSiniri = (mt.getAnaSunucuIstekKapasitesi() / 100) * 70;

        while (true) {

            System.out.print("");

            if (a.onur == true) {
                System.out.println(a.onur);
                while (mt.getAnaSunucuIstek() < AnaSunucuSiniri) {
                    synchronized (mt) {
                        try {
                            if (a.onur == false) {
                                break;
                            }

                            AnaSunucuIstekYuzde = (float) (100 * mt.getAnaSunucuIstek()) / mt.getAnaSunucuIstekKapasitesi();
                            System.out.println("Ana Sunucu İstek: " + mt.getAnaSunucuIstek());
                            //System.out.println("Ana Sunucu İstek Yüzde: %" + AnaSunucuIstekYuzde);
                            a.yuzde = (int) AnaSunucuIstekYuzde;
                            a.jProgressBar1.setValue(a.yuzde);
                            a.jLabel4.setText("" + mt.getSunucuSayisi());
                            rasgeleSayi = ran.nextInt(500);
                            rasgeleSayi=500+rasgeleSayi;
                            System.out.println("Kabul Edilen İşlem Sayı: " + rasgeleSayi);
                            mt.setAnaSunucuIstek(mt.getAnaSunucuIstek() + rasgeleSayi);
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AltSunucu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("\n");
                        try {
                            if (a.onur == false) {
                                break;
                            }

                            AnaSunucuIstekYuzde = (float) (100 * mt.getAnaSunucuIstek()) / mt.getAnaSunucuIstekKapasitesi();
                            System.out.println("Ana Sunucu İstek: " + mt.getAnaSunucuIstek());
                            //System.out.println("Ana Sunucu İstek Yüzde: %" + AnaSunucuIstekYuzde);
                            a.yuzde = (int) AnaSunucuIstekYuzde;
                            a.jProgressBar1.setValue(a.yuzde);
                            a.jLabel4.setText("" + mt.getSunucuSayisi());
                            rasgeleSayi = ran.nextInt(50);
                            System.out.println("Geri Dönüş Yapılan İşlem Sayı: " + rasgeleSayi);
                            if (mt.getAnaSunucuIstek() - rasgeleSayi > 0) {
                                mt.setAnaSunucuIstek(mt.getAnaSunucuIstek() - rasgeleSayi);
                            } else {
                                System.out.println("İşlem Yok");
                                mt.setAnaSunucuIstek(0);
                            }
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("\n");

                        if (mt.getAnaSunucuIstek() > AnaSunucuSiniri) {
                            rasgeleSayi = ran.nextInt(mt.getAnaSunucuIstek() / 2);
                            OlusturulanAltSunucuIstekSayisi = rasgeleSayi;
                            System.out.println("Yeni Sunucu 1 İstek Sayısı: " + (int) OlusturulanAltSunucuIstekSayisi);
                            mt.setAnaSunucuIstek(mt.getAnaSunucuIstek() - rasgeleSayi);
                            Thread t1 = new Thread(new AltSunucu(OlusturulanAltSunucuIstekSayisi, a, mt.getSunucuSayisi()));
                            rasgeleSayi = ran.nextInt(mt.getAnaSunucuIstek() / 2);
                            OlusturulanAltSunucuIstekSayisi = rasgeleSayi;
                            System.out.println("Yeni Sunucu 2 İstek Sayısı: " + OlusturulanAltSunucuIstekSayisi);
                            mt.setAnaSunucuIstek(mt.getAnaSunucuIstek() - rasgeleSayi);
                            Thread t2 = new Thread(new AltSunucuDiger(OlusturulanAltSunucuIstekSayisi, a, mt.getSunucuSayisi()));
                            t1.start();
                            t2.start();
                        }
                    }
                }

            }
        }

    }

    /**
     * @return the SunucuSayisi
     */
    public int getSunucuSayisi() {
        return SunucuSayisi;
    }

    /**
     * @param SunucuSayisi the SunucuSayisi to set
     */
    public void setSunucuSayisi(int SunucuSayisi) {
        this.SunucuSayisi = SunucuSayisi;
    }

}
