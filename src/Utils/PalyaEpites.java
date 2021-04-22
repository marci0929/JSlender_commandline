package Utils;

import Jatekmenet.GameMaster;
import Terep.*;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class PalyaEpites {
    public PalyaEpites() {
    }

    File palya_fajl = new File("src\\felhasznaloi_palya.txt");

    //ez a default pálya, ezen semmiképpen ne változtass!
    //azért került bele magába a kódba, ha esetleg valami letiltaná a program fájlelérését, akkor is használható legyen a játék
    final int[] default_palya =
                        {
                                6,6,6,1,1,1,4,4,4,1,1,2,1,1,1,
                                6,6,6,2,1,1,4,4,4,1,1,1,1,2,1,
                                6,6,6,1,1,1,1,1,8,8,8,8,8,8,1,
                                1,1,1,7,7,7,7,1,9,9,8,9,9,8,1,
                                2,1,1,7,7,7,7,1,8,9,8,9,9,8,1,
                                1,1,1,1,1,1,1,2,8,9,8,9,9,8,2,
                                1,1,1,1,1,1,1,1,8,9,9,9,9,8,1,
                                6,6,6,1,1,1,4,4,8,8,8,8,9,8,1,
                                6,6,6,1,1,1,4,4,8,8,8,8,9,8,1,
                                6,6,6,1,1,1,4,4,1,1,1,1,1,3,3,
                                1,1,2,1,1,3,3,1,1,5,5,5,1,3,3,
                                1,1,1,1,1,3,3,1,1,5,5,5,1,1,1,
                                1,1,1,1,2,1,1,1,1,5,5,5,2,1,1,
                                3,3,1,1,3,3,1,1,1,5,5,5,2,1,1,
                                3,3,1,1,3,3,1,1,1,5,5,5,2,1,1
                        };
    boolean van_sajat = false;
    Scanner bemenet;

    public void palyatEpit(boolean sajatPalya){
        if (sajatPalya) {
            try {
                URL url = getClass().getResource("./felhasznaloi_palya.txt");
                assert url != null;
                palya_fajl = new File(url.getPath());
                bemenet = new Scanner(palya_fajl);
                bemenet.useDelimiter(",");
                bemenet.next();
                van_sajat=true;
            }catch(Exception e){
                System.out.println("A felhasználói pálya fájl nem létezik. A default pálya lesz betöltve. A \"beüzemelés.txt\" fájlban megtalálod, hogy mi okozhatja a problémát, és hogyan javítsd meg!");
                van_sajat = false;
            }

        } else {
            van_sajat=false;
        }
        int nextInput;
        try {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (van_sajat) {
                        nextInput = Integer.parseInt(bemenet.next().replace("\r\n", "").replace("\n", ""));
                    } else {
                        nextInput = default_palya[j + i * 15];
                    }
                    switch (nextInput) {
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
        }catch (Exception e){
            System.out.println("A bemeneti fájl szerkezetileg valahol hibás. A \"beüzemelés.txt\" fájlban találsz egy mintát a felépítésére, azzal kijavíthatod!");
            System.exit(1);
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
            if(eselyKorrekcio(jelenMezo)){
                if (jelenMezo.isLehetPapir() && !jelenMezo.isVanPapir() && jelenMezo.getKiAllRajta() == null) {
                    setPapirIDs(randomX, randomY);
                    letettPapirok++;
                }
            }
        }
    }

    public boolean eselyKorrekcio(Mezo mezo){
        int randomSzam=RandomGen.randomIntGen(0,100);
        double korrekciosTenyezo=0;
        double palyaTenyezo=225;
        if(mezo.getClass()==Szikla.class){
            korrekciosTenyezo=9/palyaTenyezo;
        }
        if(mezo.getClass()==Auto.class){
            korrekciosTenyezo=6/palyaTenyezo;
        }
        if(mezo.getClass()==Fu.class){
            korrekciosTenyezo=0;
        }
        if(mezo.getClass()==Haz.class){
            korrekciosTenyezo=42/palyaTenyezo;
        }
        if(mezo.getClass()==Hordo.class){
            korrekciosTenyezo=8/palyaTenyezo;
        }
        if(mezo.getClass()==KisFa.class){
            korrekciosTenyezo=1/palyaTenyezo;
        }
        if(mezo.getClass()==NagyFa.class){
            korrekciosTenyezo=4/palyaTenyezo;
        }
        if(mezo.getClass()==Teherauto.class){
            korrekciosTenyezo=15/palyaTenyezo;
        }

        return !(korrekciosTenyezo * 100 >= randomSzam);

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
