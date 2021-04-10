package Utils;

import java.util.Random;

public class RandomGen {
    public static Random rand = new Random();
    public static int randomIntGen(int mettol, int meddig){
        rand.setSeed(System.currentTimeMillis());
        return rand.nextInt(meddig+1-mettol)+mettol;
    }
}
