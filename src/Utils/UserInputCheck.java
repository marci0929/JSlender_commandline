package Utils;

import Jatekmenet.GameMaster;

public class UserInputCheck {
   public boolean checkStepValidity(){
       if(GameMaster.lepes.equals("s")){
           if(GameMaster.player.getPoz_y() + 1 < 15)
               return GameMaster.palya[GameMaster.player.getPoz_y()+1][GameMaster.player.getPoz_x()].isJarhato();
       }
       if(GameMaster.lepes.equals("w")){
           if(GameMaster.player.getPoz_y() - 1 >= 0)
               return GameMaster.palya[GameMaster.player.getPoz_y()-1][GameMaster.player.getPoz_x()].isJarhato();
       }
       if(GameMaster.lepes.equals("a")){
           if(GameMaster.player.getPoz_x() - 1 >= 0)
               return GameMaster.palya[GameMaster.player.getPoz_y()][GameMaster.player.getPoz_x()-1].isJarhato();
       }
       if(GameMaster.lepes.equals("d")){
           if(GameMaster.player.getPoz_x() + 1 < 15)
               return GameMaster.palya[GameMaster.player.getPoz_y()][GameMaster.player.getPoz_x()+1].isJarhato();
       }
       return false;
   }

   public boolean lepesVoltE() {
       if(GameMaster.lepes.matches("^[wasd]$")) return true;
       if(GameMaster.lepes.equals("iranyitas")) System.out.println(UserTajekoztatas.iranyitas);
       if(GameMaster.lepes.equals("targykodok")) System.out.println(UserTajekoztatas.targyKodok);
       return false;
   }

   public boolean checkValidInput() throws InterruptedException {
       if( GameMaster.lepes.matches("^[wasd]$")){
           return true;
       }else if(GameMaster.lepes.equals("iranyitas") || GameMaster.lepes.equals("targykodok")){
           lepesVoltE();
           PalyaRajzol.rajzol();
           return false;
       }
       else{
           System.out.println("Nincs ilyen parancs. A parancsokat az \"iranyitas\" szo beirasaval erheted el.");
           return false;
       }
   }
}
