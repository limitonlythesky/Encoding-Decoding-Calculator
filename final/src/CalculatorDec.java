public class CalculatorDec {
    String text = "";
    String tmp = "";
    String ans = "";
    Node root = new Node();

    public CalculatorDec(Node root) {
        this.root = root;
    }

    public int traversal(String bits, int numNode, int i, Node current) {
        if (i > bits.length()) {
            return i;
        } else {
            if (numNode != 0) {
                text += tmp + ":Node" + String.valueOf(numNode) + ":" + current.st + "\n";
            }
            if (current.left == null && current.right == null) {
                text += "Symbol: " + tmp + " - " + current.st + "\n\n";
                ans += current.st;
                tmp = "";
                return traversal(bits, 0, i, root);
            }
            tmp += bits.charAt(i);
            if (bits.charAt(i) == '0') {
                return traversal(bits, numNode * 2 + 1, i + 1, current.right);
            } else if (bits.charAt(i) == '1') {
                return traversal(bits, numNode * 2 + 2, i + 1, current.left);
            }
        }
        return i;
    }
}
