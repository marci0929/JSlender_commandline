package Utils;

import Jatekmenet.GameMaster;
import Jatekosok.Ember;

public class PalyaRajzol {
    public static void rajzol(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if(GameMaster.palya[i][j].getKiAllRajta()!=null){
                    if(GameMaster.palya[i][j].getKiAllRajta()== GameMaster.player) {
                        System.out.print(GameMaster.palya[i][j].getKiAllRajta().getJele() + "    ");
                    }else{
                        if(GameMaster.slender.distanceFromPlayer(j,i)<=3) {
                            System.out.print(GameMaster.palya[i][j].getKiAllRajta().getJele() + "    ");
                        }
                        else{
                            System.out.print(GameMaster.palya[i][j].getJele() + "    ");
                        }
                    }
                }else {
                    System.out.print(GameMaster.palya[i][j].getJele() + "    ");
                }
            }
            System.out.println("\n");
        }
    }
}
