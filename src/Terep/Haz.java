package Terep;

public class Haz extends Mezo{

    public Haz(boolean jar){
        super(!jar,jar,"Haz","_");
        if(!jar){
            super.setJele("#");
        }
    }
}
