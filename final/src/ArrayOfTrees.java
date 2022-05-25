import java.util.ArrayList;

public class ArrayOfTrees {
    ArrayList <Pair <String, Node> > arrayOfTrees = new ArrayList<>();

    public ArrayOfTrees() {
    }

    public void add(String s, Node root){
        arrayOfTrees.add(Pair.createPair(s, root));
    }

    public ArrayList<Pair<String,Node>> get(){
        return arrayOfTrees;
    }
}
