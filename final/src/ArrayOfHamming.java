import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class ArrayOfHamming {
    ArrayList<Pair<String, Integer> > arrayOfHamming = new ArrayList<>();

    public ArrayOfHamming() {
    }

    public void add(String hammingCode, int r){
        arrayOfHamming.add(Pair.createPair(hammingCode, r));
    }

    public ArrayList <Pair <String, Integer> > get(){
        return arrayOfHamming;
    }

}
