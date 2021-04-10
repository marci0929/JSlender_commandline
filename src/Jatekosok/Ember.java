package Jatekosok;

import Jatekmenet.GameMaster;
import Terep.Mezo;



public class Ember extends Jatekos {

    int papirokSzama=0;
    public Ember(){
        jele="@";
        embertLetesz();
        setMinall(GameMaster.palya[getPoz_y()][getPoz_x()]);
        GameMaster.palya[getPoz_y()][getPoz_x()].setKiAllRajta(this);
    }

    public int getPapirokSzama() {
        return papirokSzama;
    }

    public void papirHozzaad(int papirokSzama) {
        this.papirokSzama +=1;
    }
    public void lep(){
        if(GameMaster.lepes.equals("w")) setPoz_y(getPoz_y()-1);
        if(GameMaster.lepes.equals("s")) setPoz_y(getPoz_y()+1);
        if(GameMaster.lepes.equals("a")) setPoz_x(getPoz_x()-1);
        if(GameMaster.lepes.equals("d")) setPoz_x(getPoz_x()+1);

    }

    public void embertLetesz(){
        if(GameMaster.palya[0][0].isJarhato()) {
            setPoz_x(0);
            setPoz_y(0);
        }
        if(GameMaster.palya[14][0].isJarhato()) {
            setPoz_x(0);
            setPoz_y(14);
        }
        if(GameMaster.palya[0][14].isJarhato()) {
            setPoz_x(14);
            setPoz_y(0);
        }
        if(GameMaster.palya[14][14].isJarhato()) {
            setPoz_x(14);
            setPoz_y(14);
        }
    }

    public void papirBegyujt(){
        int gyujtottpapirID=-1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(getPoz_x()-1+i>=0 && getPoz_x()-1+i<15 && getPoz_y()-1+j>=0 && getPoz_y()-1+j<15){
                    if(GameMaster.palya[getPoz_y()-1+j][getPoz_x()-1+i].isVanPapir()){
                        gyujtottpapirID=GameMaster.palya[getPoz_y()-1+j][getPoz_x()-1+i].getPapirID();
                        for (int k = 0; k < 15; k++) {
                            for (int l = 0; l < 15; l++) {
                                if(GameMaster.palya[k][l].getPapirID()==gyujtottpapirID){
                                    GameMaster.palya[k][l].setPapirID(-1);
                                    GameMaster.palya[k][l].setVanPapir(false);
                                }
                            }
                        }
                        System.out.println("\n\nTaláltál egy papírt a(z) "+GameMaster.palya[getPoz_y()-1+j][getPoz_x()-1+i].getNeve()+" objektumon!\n");
                        papirokSzama++;
                    }
                }
            }
        }
    }

    @Override
    public int getPoz_x() {
        return poz_x;
    }
    public int getPoz_y() {
        return poz_y;
    }

    @Override
    public void setPoz_x(int poz_x) {
        this.poz_x=poz_x;
    }

    @Override
    public void setPoz_y(int poz_y) {
        this.poz_y=poz_y;
    }

    public Mezo getMinall(){
        return min_all;
    }

    public void setMinall(Mezo min){
        min_all=min;
    }
}
