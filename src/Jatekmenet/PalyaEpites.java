package Jatekmenet;

import Terep.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PalyaEpites {
    public PalyaEpites() throws FileNotFoundException {
    }

    File palya_fajl=new File("src\\felhasznaloi_palya.txt");
    String default_palya_path="src\\default_palya.txt";
    Scanner bemenet=new Scanner(palya_fajl);
    boolean van_sajat=false;


    public boolean vanSajatFajl(){
        bemenet.useDelimiter(",");
        if(bemenet.next().equals("0")){
            return false;
        }
        return true;
    }

    public void palyatEpit() throws IOException {
        if(vanSajatFajl()){
            palya_fajl=new File("src/felhasznaloi_palya.txt");
            van_sajat=true;
        }else{
            palya_fajl=new File(default_palya_path);
        }
        bemenet=new Scanner(palya_fajl);
        bemenet.useDelimiter(",");
        if(van_sajat) bemenet.next();
        for (int i = 0; i <15; i++) {
            for (int j = 0; j < 15; j++) {
                switch (Integer.parseInt(bemenet.next().replace("\r\n",""))){
                    case 1: GameMaster.palya[i][j]=new Fu(); break;
                    case 2: GameMaster.palya[i][j]=new KisFa(); break;
                    case 3: GameMaster.palya[i][j]=new NagyFa(); break;
                    case 4: GameMaster.palya[i][j]=new Auto(); break;
                    case 5: GameMaster.palya[i][j]=new Teherauto(); break;
                    case 6: GameMaster.palya[i][j]=new Szikla(); break;
                    case 7: GameMaster.palya[i][j]=new Hordo(); break;
                    case 8: GameMaster.palya[i][j]=new Haz(false); break;
                    case 9: GameMaster.palya[i][j]=new Haz(true); break;
                }
            }
        }

    }



}
