import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

public class Calculator {

    ArrayList <Pair <Double, String> > ch = new ArrayList<>();
    Vector<Integer> cod = new Vector<>();
    int []cnt = new int[300];
    Stack <Node> stack = new Stack<>();
    Stack <Node> stack1 = new Stack<>();
    Node root;
    String text = "";
    String enc = "";
    ArrayList <Node>[] ans = new ArrayList[300];
    int max_step = 0;
    ArrayList <Pair <String, Pair <Double, Vector <Integer> > > > ans1 = new ArrayList<>();

    public Calculator() {
    }

    public Double getSum(int l, int r){
        Double sum = 0.0;
        for(int i = l; i <= r; i++){
            sum = sum + ch.get(i).getElement0();
        }
        return sum;
    }

    public String getString(int l, int r){
        String st = "";
        for(int i = l; i <= r; i++){
            st += ch.get(i).getElement1();
        }
        return st;
    }

    public void sorting(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - 1; j++){
                if(ch.get(j).getElement0() < ch.get(j + 1).getElement0()){
                    Collections.swap(ch, j, j + 1);
                }
            }
        }
    }

    public void shannon(int l, int r, Node current){
        if(l == r || l > r) {
            return;
        }
        int mid = (l + r + 1) / 2;
        cod.add(0);
        current.right = new Node(getString(l, mid - 1), getSum(l, mid - 1), cod);
        shannon(l, mid - 1, current.right);
        cod.remove(cod.size() - 1);
        cod.add(1);
        current.left = new Node(getString(mid, r), getSum(mid, r), cod);
        shannon(mid, r, current.left);
        cod.remove(cod.size() - 1);
    }

    void traversal(int step, Node current){
        ans[step].add(current);
        System.out.println(current.code.size() + " " + current.st);
        if(current.left == null && current.right == null){
            ans1.add(Pair.createPair(current.st, Pair.createPair(current.prob, current.code)));
            if(step > max_step){
                max_step = step;
            }
            return;
        }
        traversal(step + 1, current.right);
        traversal(step + 1, current.left);
    }

    public void buildTreeShannon(String s){
        int n = s.length();
        Double pr = 0.0;
        for(int i = 0; i < n; i++){
            cnt[s.charAt(i)]++;
        }
        for(int i = 0; i < 300; i++){
            if(cnt[i] != 0){
                ch.add(new Pair<Double, String>(Double.valueOf(cnt[i]) / n, Character.toString(i)));
            }
        }
        sorting(ch.size());
        for(int i = 0; i < ch.size(); i++){
            pr += ch.get(i).getElement0();
            text += ch.get(i).getElement1() + "-" + String.valueOf(ch.get(i).getElement0()) + '\n';
        }
        root = new Node(s, pr, cod);
        shannon(0, ch.size() - 1, root);
        for(int i = 0; i < 300; i++){
            ans[i] = new ArrayList<>();
        }
        traversal(0, root);
        int tmp = 0;
        for(int i = 1; i <= max_step; i++){
            text += "Step " + String.valueOf(i) + ".\n";
            for(int j = 0; j < ans[i].size(); j++){
                text += "Node" + String.valueOf(tmp) + ": (";
                tmp++;
                for(int k = 0; k < (ans[i].get(j).code.size()); k++){
                    text += String.valueOf(ans[i].get(j).code.get(k));
                }
                text += ") " + ans[i].get(j).st + "-" + String.valueOf(ans[i].get(j).prob) + "\n";
            }
        }
        for(int i = 0; i < ans1.size(); i++){
            text += ans1.get(i).getElement0() + "-" + ans1.get(i).getElement1().getElement0() + "-" + ans1.get(i).getElement1().getElement1().toString() + "\n";
        }
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < ans1.size(); j++){
                if(String.valueOf(s.charAt(i)).equals(ans1.get(j).getElement0())){
                    for(int k = 0; k < ans1.get(j).getElement1().getElement1().size(); k++) {
                        text += String.valueOf(ans1.get(j).getElement1().getElement1().get(k));
                        enc += String.valueOf(ans1.get(j).getElement1().getElement1().get(k));
                    }
                }
            }
        }
    }

    public void treeDown(Node current){
        if(current.left == null && current.right == null){
            return;
        }
        cod.add(0);
        current.right.setCode(cod);
        treeDown(current.right);
        cod.remove(cod.size() - 1);
        cod.add(1);
        current.left.setCode(cod);
        treeDown(current.left);
        cod.remove(cod.size() - 1);
    }

    public void buildTreeHuffman(String s){
        int n = s.length();
        System.out.println(s.length());;
        for(int i = 0; i < n; i++){
            cnt[s.charAt(i)]++;
        }
        for(int i = 0; i < 300; i++){
            if(cnt[i] != 0){
                ch.add(new Pair<Double, String>(Double.valueOf(cnt[i]) / n, Character.toString(i)));
            }
        }
        sorting(ch.size());
        for(int i = 0; i < ch.size(); i++){
            text += ch.get(i).getElement1() + "-" + String.valueOf(ch.get(i).getElement0()) + '\n';
            stack.push(new Node(ch.get(i).getElement1(), ch.get(i).getElement0(), new Vector<>()));
        }

        while(!stack.empty()){
            String st = stack.peek().st;
            Double pr = stack.peek().prob;
            Node right = stack.peek();
            stack.pop();
            st += stack.peek().st;
            pr += stack.peek().prob;
            Node left = stack.peek();
            stack.pop();
            Node node = new Node(st, pr, left, right, new Vector<>());
            if(stack.empty()){
                root = node;
                break;
            }
            while(!stack.empty() && node.prob > stack.peek().prob){
                stack1.push(stack.peek());
                stack.pop();
            }
            stack.push(node);
            while(!stack1.empty()) {
                stack.push(stack1.peek());
                stack1.pop();
            }
        }
        treeDown(root);
        for(int i = 0; i < 300; i++){
            ans[i] = new ArrayList<>();
        }
        traversal(0, root);
        int tmp = 0;
        for(int i = 1; i <= max_step; i++){
            text += "Step " + String.valueOf(i) + ".\n";
            for(int j = 0; j < ans[i].size(); j++){
                text += "Node" + String.valueOf(tmp) + ": (";
                tmp++;
                for(int k = 0; k < (ans[i].get(j).code.size()); k++){
                    text += String.valueOf(ans[i].get(j).code.get(k));
                }
                text += ") " + ans[i].get(j).st + "-" + String.valueOf(ans[i].get(j).prob) + "\n";
            }
        }
        for(int i = 0; i < ans1.size(); i++){
            text += ans1.get(i).getElement0() + "-" + ans1.get(i).getElement1().getElement0() + "-" + ans1.get(i).getElement1().getElement1().toString() + "\n";
        }
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < ans1.size(); j++){
                if(String.valueOf(s.charAt(i)).equals(ans1.get(j).getElement0())){
                    for(int k = 0; k < ans1.get(j).getElement1().getElement1().size(); k++) {
                        text += String.valueOf(ans1.get(j).getElement1().getElement1().get(k));
                        enc += String.valueOf(ans1.get(j).getElement1().getElement1().get(k));
                    }
                }
            }
        }
    }
}
