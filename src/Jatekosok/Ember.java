package Jatekosok;

import Jatekmenet.GameMaster;
import Terep.Mezo;



public class Ember extends Jatekos {

    int papirokSzama=0;
    public Ember(){
        jele="&";
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
