package Jatekmenet;
import Jatekosok.*;
import Terep.Mezo;
import Utils.PalyaEpites;
import Utils.PalyaRajzol;
import Utils.UserInputCheck;
import Utils.UserTajekoztatas;


import java.util.Scanner;


public class GameMaster{
    public static Mezo[][] palya=new Mezo[15][15];
    public static Ember player;
    public static Slender slender;
    public static String lepes="";
    public static void main(String[] args) throws Exception{
        boolean vege=false;
        boolean nemLepett=true;
        Scanner bemenet=new Scanner(System.in);
        UserInputCheck inputCheck = new UserInputCheck();
        PalyaEpites palya_epito=new PalyaEpites();
        palya_epito.palyatEpit();
        player=new Ember();
        slender=new Slender();
        palya_epito.papirLerak();

        System.out.println(UserTajekoztatas.welcome);
        System.out.println(UserTajekoztatas.iranyitas);
        System.out.println(UserTajekoztatas.targyKodok+"\n");

        while(!vege){
            System.out.println("A jelenlegi poziciod: x:"+(player.getPoz_x()+1)+", y: "+(player.getPoz_y()+1));
            PalyaRajzol.rajzol();
            nemLepett=true;
            while(nemLepett) {
                do {
                    System.out.print("Mi a kovetkezo lepesed?: ");
                    lepes = bemenet.next();
                }
                while (!inputCheck.checkValidInput());
                if(inputCheck.lepesVoltE())
                    if(inputCheck.checkStepValidity()){
                        nemLepett=false;
                    }
                else{
                        System.out.println(UserTajekoztatas.nemLephetsz);
                }
            }
            palya[player.getPoz_y()][player.getPoz_x()].setKiAllRajta(null);
            player.lep();
            palya[player.getPoz_y()][player.getPoz_x()].setKiAllRajta(player);

            player.papirBegyujt();
            System.out.println("Jelenleg osszegyujtott papirok: "+player.getPapirokSzama()+" db");

        }
    }
}
