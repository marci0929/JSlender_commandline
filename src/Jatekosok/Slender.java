package Jatekosok;

import Jatekmenet.GameMaster;
import Utils.RandomGen;

public class Slender extends Jatekos {
    public Slender() {
                  jele="O";
                  poz_y=-1;
                  poz_x=-1;
    }
    int randX, randY;
    int egyTavolsagraSzam=0;
    int elkapasEselye;

    public void teleport(){
        if(poz_x!=-1 && poz_y!=-1){
            GameMaster.palya[poz_y][poz_x].setKiAllRajta(null);
        }
        if(GameMaster.player.lepesekElsoPapirUtan%5==0){
            randX=RandomGen.randomIntGen(0,14);
            randY=RandomGen.randomIntGen(0,14);
        }
        else{
            switch (GameMaster.player.getPapirokSzama()){
                case 1:
                    generateCorrectPosition(5,true);
                    break;
                case 2:
                case 3:
                    generateCorrectPosition(5,false);
                    break;
                case 4:
                case 5:
                    generateCorrectPosition(4,false);
                    break;
                case 6:
                case 7:
                    generateCorrectPosition(3,false);
                    break;
            }
        }
        if(distanceFromPlayer(randX,randY)==1){
            egyTavolsagraSzam++;
        }else{
            egyTavolsagraSzam=0;
        }
        if(egyTavolsagraSzam==3){
            switch(GameMaster.player.getPapirokSzama()){
                case 2:
                case 3:
                    elkapasEselye=33;
                    break;
                case 4:
                case 5:
                    elkapasEselye=50;
                    break;
                case 6:
                case 7:
                    elkapasEselye=66;
                    break;
            }
            int elkaptae=RandomGen.randomIntGen(1,100);
            if(elkaptae<=elkapasEselye){
                randX=GameMaster.player.getPoz_x();
                randY=GameMaster.player.getPoz_y();
            }
            egyTavolsagraSzam=0;
        }
        poz_x=randX;
        poz_y=randY;
        GameMaster.palya[poz_y][poz_x].setKiAllRajta(this);
    }

    public int distanceFromPlayer(int x, int y){
        return Math.abs(x-GameMaster.player.getPoz_x())+Math.abs(y-GameMaster.player.getPoz_y());
    }

    public void generateCorrectPosition(int tav, boolean legalabb){ //legalabb=true -> legalabb akkora tavolsagra keres
        boolean joTav=false;
        while(!joTav){
            randX=RandomGen.randomIntGen(0,14);
            randY=RandomGen.randomIntGen(0,14);
            if(legalabb){
                if(distanceFromPlayer(randX, randY)>=tav){
                    joTav=true;
                }
            }else{
                if(distanceFromPlayer(randX, randY)<=tav){
                    joTav=true;
                }
            }
        }
    }
}
