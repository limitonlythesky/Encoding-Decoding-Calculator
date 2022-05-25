import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Node {
    String st;
    double prob;
    Node left;
    Node right;
    Vector<Integer> code;

    public Node() {
    }

    public Node(String st, double prob, Vector<Integer> code) {
        this.st = st;
        this.prob = prob;
        this.left = null;
        this.right = null;
        Vector <Integer> list = new Vector<>();
        for(int i = 0; i < code.size(); i++){
            list.add(code.get(i));
        }
        this.code = list;
    }

    public Node(String st, double prob, Node left, Node right, Vector<Integer> code) {
        this.st = st;
        this.prob = prob;
        this.left = left;
        this.right = right;
        Vector <Integer> list = new Vector<>();
        for(int i = 0; i < code.size(); i++){
            list.add(code.get(i));
        }
        this.code = list;
    }

    public void setCode(Vector<Integer> code) {
        Vector <Integer> list = new Vector<>();
        for(int i = 0; i < code.size(); i++){
            list.add(code.get(i));
        }
        this.code = list;
    }
}
