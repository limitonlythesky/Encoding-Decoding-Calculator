import java.awt.*;
import java.util.Vector;

public class CalculatorHamming {
    int p = 0;
    int [][]ham = new int[500][500];
    int []gen = new int[500];
    String []blocks = new String[500];
    String text = "";
    Vector<Integer> ans = new Vector<>();
    String hammingcode = "";
    int size = 0;

    public CalculatorHamming() {
    }

    public void generateBin(int i, int n){
        if(i == n){
            if(p == 0){
              p++;
              return;
            }
            for(int j = n - 1, k = 1; j >= 0; j--, k++){
                ham[p][k] = gen[j];
            }
            p++;
            return;
        }
        gen[i] = 0;
        generateBin(i + 1, n);
        gen[i] = 1;
        generateBin(i + 1, n);
    }

    public int divideToBlocks(int r, String bin){
        int k = 0, tmp = 0;
        String s = "";
        for (int i = 0; i < bin.length(); i++){
            s += bin.charAt(i);
            k++;
            if(k == r || i == bin.length() - 1){
                blocks[tmp++] = s;
                k = 0;
                s = "";
            }
        }
        if(blocks[tmp - 1].length() != r){
            blocks[tmp - 1] = bin.substring(bin.length() - r, bin.length());
        }
        return tmp;
    }

    public int findNumOfContBit(int r){
        int l = 2;
        while(true){
            if(Math.ceil(Math.log(l + r + 1)/ Math.log(2)) == l){
                return l;
            }
            l += 1.;
        }
    }

    public void HammingEncode(int r, String s){
        int po = findNumOfContBit(r);
        text += s + ":\nExpand the block to " + String.valueOf(r + po) + " bits: ";
        int j = 0, k = 0;
        for(int i = 1; i <= r + po; i++){
            if(Math.pow(2, j) == i){
                ham[i][0] = 0;
                text += "_";
                j++;
            }else{
                text += s.charAt(k);
                ham[i][0] = s.charAt(k++) - '0';
            }
        }
        text += '\n';
        generateBin(0, po);
        j = 0;
        for(int i = 1; i <= po; i++){
            text += "p" + String.valueOf(i) + ": ";
            int sum = 0;
            for(int g = 1; g <= r + po; g++){
                text += String.valueOf(ham[g][0]) + " * " + String.valueOf(ham[g][i]);
                sum += ham[g][0] * ham[g][i];
                if(g != r + po){
                    text += " + ";
                }else{
                    text += " = ";
                }
            }
            text += String.valueOf(sum % 2) + "\n";
            ham[(int) Math.pow(2, j)][0] = sum % 2;
            j++;
        }
        text += "Encoded string: ";
        for(int i = 1; i <= r + po; i++){
            text += String.valueOf(ham[i][0]);
            ans.add(ham[i][0]);
        }
        text += "\n";
    }

    public void hamming(String s, String num){
        int sizeOfBin = divideToBlocks(Integer.valueOf(num), s);
        size = sizeOfBin;
        text += "Data blocks:\n";
        for(int i = 0; i < sizeOfBin; i++){
            text += "b" + String.valueOf(i + 1) + " " + blocks[i] + "\n";
        }
        for(int i = 0; i < sizeOfBin; i++){
            HammingEncode(Integer.valueOf(num), blocks[i]);
        }

        for(int i = 0; i < ans.size(); i++){
            text += String.valueOf(ans.get(i));
            hammingcode += String.valueOf(ans.get(i));
        }
    }
}
