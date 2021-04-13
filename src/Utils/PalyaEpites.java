package Utils;

import Jatekmenet.GameMaster;
import Terep.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PalyaEpites {
    public PalyaEpites() throws FileNotFoundException {
    }

    File palya_fajl = new File("src\\felhasznaloi_palya.txt");
    String default_palya_path = "src\\default_palya.txt";
    Scanner bemenet = new Scanner(palya_fajl);
    boolean van_sajat = false;


    public boolean vanSajatFajl() {
        bemenet.useDelimiter(",");
        return !bemenet.next().equals("0");
    }

    public void palyatEpit() throws IOException {
        if (vanSajatFajl()) {
            palya_fajl = new File("src/felhasznaloi_palya.txt");
            van_sajat = true;
        } else {
            palya_fajl = new File(default_palya_path);
        }
        bemenet = new Scanner(palya_fajl);
        bemenet.useDelimiter(",");
        if (van_sajat) bemenet.next();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                switch (Integer.parseInt(bemenet.next().replace("\r\n", ""))) {
                    case 1:
                        GameMaster.palya[i][j] = new Fu();
                        break;
                    case 2:
                        GameMaster.palya[i][j] = new KisFa();
                        break;
                    case 3:
                        GameMaster.palya[i][j] = new NagyFa();
                        break;
                    case 4:
                        GameMaster.palya[i][j] = new Auto();
                        break;
                    case 5:
                        GameMaster.palya[i][j] = new Teherauto();
                        break;
                    case 6:
                        GameMaster.palya[i][j] = new Szikla();
                        break;
                    case 7:
                        GameMaster.palya[i][j] = new Hordo();
                        break;
                    case 8:
                        GameMaster.palya[i][j] = new Haz(false);
                        break;
                    case 9:
                        GameMaster.palya[i][j] = new Haz(true);
                        break;
                }
            }
        }

    }

    int letettPapirok = 0;

    public void papirLerak() {
        Mezo jelenMezo;
        int randomX, randomY;
        while (letettPapirok != 8) {
            randomX=RandomGen.randomIntGen(0, 14);
            randomY=RandomGen.randomIntGen(0, 14);
            jelenMezo = GameMaster.palya[randomY][randomX];
            if (jelenMezo.isLehetPapir() && !jelenMezo.isVanPapir() && jelenMezo.getKiAllRajta() == null) {
                setPapirIDs(randomX,randomY);
                letettPapirok++;
            }
        }
    }
    int kezdoX, kezdoY;

    public void setPapirIDs(int x, int y) {
            balFelsoSarkotKeres(x,y);
            if(GameMaster.palya[kezdoY][kezdoX].getClass()==Szikla.class){
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        GameMaster.palya[kezdoY+j][kezdoX+i].setVanPapir(true);
                        GameMaster.palya[kezdoY+j][kezdoX+i].setPapirID(letettPapirok);
                    }
                }
            }

            if(GameMaster.palya[kezdoY][kezdoX].getClass()==NagyFa.class){
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        GameMaster.palya[kezdoY+j][kezdoX+i].setVanPapir(true);
                        GameMaster.palya[kezdoY+j][kezdoX+i].setPapirID(letettPapirok);
                    }
                }
            }

            if(GameMaster.palya[kezdoY][kezdoX].getClass()==Auto.class){
                boolean horizontalis=getForgatas(3,2);
                for (int i = 0; i < (horizontalis?3:2); i++) {
                    for (int j = 0; j < (horizontalis?2:3); j++) {
                        GameMaster.palya[kezdoY+j][kezdoX+i].setVanPapir(true);
                        GameMaster.palya[kezdoY+j][kezdoX+i].setPapirID(letettPapirok);
                    }
                }
            }
            if(GameMaster.palya[kezdoY][kezdoX].getClass()==Hordo.class){
                boolean horizontalis=getForgatas(4,2);
                for (int i = 0; i < (horizontalis?4:2); i++) {
                    for (int j = 0; j < (horizontalis?2:4); j++) {
                        GameMaster.palya[kezdoY+j][kezdoX+i].setVanPapir(true);
                        GameMaster.palya[kezdoY+j][kezdoX+i].setPapirID(letettPapirok);
                    }
                }
            }
            if(GameMaster.palya[kezdoY][kezdoX].getClass()==Teherauto.class){
                boolean horizontalis=getForgatas(5,3);
                for (int i = 0; i < (horizontalis?5:3); i++) {
                    for (int j = 0; j < (horizontalis?3:5); j++) {
                        GameMaster.palya[kezdoY+j][kezdoX+i].setVanPapir(true);
                        GameMaster.palya[kezdoY+j][kezdoX+i].setPapirID(letettPapirok);
                    }
                }
            }
            if(GameMaster.palya[kezdoY][kezdoX].getClass()==Haz.class){
                GameMaster.palya[y][x].setPapirID(letettPapirok);
                boolean horizontalis=getForgatas(7,6);
                for (int i = 0; i < (horizontalis?7:6); i++) {
                    for (int j = 0; j < (horizontalis?6:7); j++) {
                        GameMaster.palya[kezdoY+j][kezdoX+i].setVanPapir(true);
                    }
                }
            }
    }


    public boolean getForgatas(int dimX, int dimY){
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                if(j+kezdoX<15 && i+kezdoY<15){
                    if(GameMaster.palya[kezdoY+i][kezdoX+j].getClass() != GameMaster.palya[kezdoY][kezdoX].getClass()){
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return true;
    }


    public void balFelsoSarkotKeres(int x, int y) {
        kezdoX = x;
        kezdoY = y;
        boolean voltLeptetes=true;
        while(voltLeptetes) {
            voltLeptetes=false;
            while (true) {
                if (kezdoX - 1 >= 0) {
                    if (GameMaster.palya[kezdoY][kezdoX - 1].getClass() == GameMaster.palya[kezdoY][kezdoX].getClass() && !GameMaster.palya[kezdoY][kezdoX - 1].isVanPapir()) {
                        kezdoX--;
                        voltLeptetes=true;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            while (true) {
                if (kezdoY - 1 >= 0) {
                    if (GameMaster.palya[kezdoY - 1][kezdoX].getClass() == GameMaster.palya[kezdoY][kezdoX].getClass() && !GameMaster.palya[kezdoY - 1][kezdoX].isVanPapir()) {
                        kezdoY--;
                        voltLeptetes=true;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }


    }
}
