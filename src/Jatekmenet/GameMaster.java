package Jatekmenet;

import Jatekosok.*;
import Terep.Mezo;
import Utils.PalyaEpites;
import Utils.PalyaRajzol;
import Utils.UserInputCheck;
import Utils.UserTajekoztatas;


import java.util.Scanner;


public class GameMaster {
    public static Mezo[][] palya = new Mezo[15][15];
    public static Ember player;
    public static Slender slender;
    public static String lepes = "";

    public static void main(String[] args) throws Exception {
        int vege;
        boolean nemLepett;
        Scanner bemenet = new Scanner(System.in);

        System.out.println(UserTajekoztatas.welcome);
        System.out.println(UserTajekoztatas.iranyitas);
        System.out.println(UserTajekoztatas.targyKodok + "\n");

        UserInputCheck inputCheck = new UserInputCheck();
        PalyaEpites palya_epito = new PalyaEpites();
        System.out.print("Szeretnéd fájlból betölteni az egyedi pályádat? (i=igen, n=nem): ");
        String sajatPalya=bemenet.next();
        palya_epito.palyatEpit(sajatPalya.equals("i"));
        player = new Ember();
        slender = new Slender();
        palya_epito.papirLerak();




        while (true) {

            // a korábbi lépések, a program helyességének ellenőrzése céljából, nem kerülnek törlésre a konzolablakból

            System.out.println("A jelenlegi pozíciód: x:" + (player.getPoz_x() + 1) + ", y: " + (player.getPoz_y() + 1));
            PalyaRajzol.rajzol();
            nemLepett = true;
            while (nemLepett) {
                do {
                    System.out.print("Mi a következő lépésed?: ");
                    lepes = bemenet.next();
                }
                while (!inputCheck.checkValidInput());
                if (inputCheck.lepesVoltE())
                    if (inputCheck.checkStepValidity()) {
                        nemLepett = false;
                    } else {
                        System.out.println(UserTajekoztatas.nemLephetsz);
                    }
            }
            palya[player.getPoz_y()][player.getPoz_x()].setKiAllRajta(null);
            player.lep();
            palya[player.getPoz_y()][player.getPoz_x()].setKiAllRajta(player);
            player.lepesHozzaad();

            if (slender.getPoz_x() == player.getPoz_x() && slender.getPoz_y() == player.getPoz_y()) {
                vege = 2;
                break;
            }

            player.papirBegyujt();
            System.out.println("Jelenleg összegyűjtött papírok: " + player.getPapirokSzama() + " db");
            if (player.getPapirokSzama() == 8) {
                vege = 1;
                break;
            }
            if (player.getPapirokSzama() > 0) {
                slender.teleport();
            }
            if (slender.getPoz_x() == player.getPoz_x() && slender.getPoz_y() == player.getPoz_y()) {
                vege = 2;
                break;
            }

        }
        switch (vege) {
            case 1:
                System.out.println(UserTajekoztatas.nyertel);
                break;
            case 2:
                System.out.println(UserTajekoztatas.vesztettel);
                break;
        }
        System.out.println("\nKöszi, hogy játszottál a játékkal!");
    }
}
