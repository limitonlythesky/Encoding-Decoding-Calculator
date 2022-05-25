import java.util.ArrayList;
import java.util.Collections;

public class CalculatorHammingDec {

    int sizeOfBin = 0; // count of blocks
    String text = "";
    int p = 0;
    ArrayList <Integer> randOfBit = new ArrayList<>();
    ArrayList <Integer> randOfBlock = new ArrayList<>();
    String []blocksError = new String[500];
    String []blocks = new String[500];
    int [][]ham = new int[500][500];
    int []gen = new int[500];


    public CalculatorHammingDec(int sizeOfBin) {
        this.sizeOfBin = sizeOfBin;
    }

    public void errorGen(int per, int len){
        int numOfErrorBlocks = len * per / 100;
        text += "Blocks with errors: ";
        for(int i = 0; i < numOfErrorBlocks; i++){
            Collections.shuffle(randOfBit);
            if(blocks[randOfBlock.get(i)].charAt(randOfBit.get(0)) == '1'){
                StringBuilder s = new StringBuilder(blocks[randOfBlock.get(i)]);
                s.setCharAt(randOfBit.get(0), '0');
                blocksError[i] = String.valueOf(s);
            }else {
                StringBuilder s = new StringBuilder(blocks[randOfBlock.get(i)]);
                s.setCharAt(randOfBit.get(0), '1');
                blocksError[i] = String.valueOf(s);
            }
            text += blocksError[i] + " ";
        }
    }

    public int findNumofParBit(int len){
        int ans = 0, j = 0;
        for(int i = 1; i <= len; i++){
            if(Math.pow(2, j) == i){
                ans++;
                j++;
            }
        }
        return ans;
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

    public int transfer(String s){
        int sum = 0;
        for(int i = 0; i < s.length(); i++){
            sum += (s.charAt(i) - '0') * Math.pow(2, i);
        }
        return sum;
    }

    public String HammingDecode(String bit){
        int po = findNumofParBit(bit.length());
        generateBin(0, po);
        for(int i = 0; i < bit.length(); i++){
            text += bit.charAt(i) + " ";
            ham[i + 1][0] = (bit.charAt(i) - '0');
        }
        text += "\nChecking parity bits: \n";
        String s = "";
        for(int i = 1; i <= po; i++) {
            text += "p" + String.valueOf(i) + ": ";
            int sum = 0;
            for (int g = 1; g <= bit.length(); g++) {
                text += String.valueOf(ham[g][0]) + " * " + String.valueOf(ham[g][i]);
                sum += ham[g][0] * ham[g][i];
                if (g != bit.length()) {
                    text += " + ";
                } else {
                    text += " = ";
                }
            }
            if (sum % 2 != 0) {
                text += String.valueOf((sum % 2)) + " incorrect\n";
            } else {
                text += String.valueOf((sum % 2)) + " correct\n";
            }
            s += String.valueOf((sum % 2));
        }
        text += "Error in position: " + String.valueOf(transfer(s));
        if(bit.charAt(transfer(s) - 1) == '0'){
            StringBuilder t = new StringBuilder(bit);
            t.setCharAt(transfer(s) - 1, '1');
            bit = String.valueOf(t);
        }else{
            StringBuilder t = new StringBuilder(bit);
            t.setCharAt(transfer(s) - 1, '0');
            bit = String.valueOf(t);
        }
        text += "\nCorrected bitstring: " + bit;
        text += "\nDecoded bitstring: ";
        int j = 0;
        for(int i = 0; i < bit.length(); i++){
            if(Math.pow(2, j) != (i + 1)){
                text += bit.charAt(i);
            }else{
                j++;
            }
        }
        text += "\n";
        return bit;
    }


    public void hammingDec(String origi, int per){
        int r = origi.length() / sizeOfBin;
        String s = "";
        int tmp = 0;
        for(int i = 0; i < origi.length(); i++){
            s += origi.charAt(i);
            if((i + 1) % r == 0){
                blocks[tmp++] = s;
                s = "";
            }
        }
        for(int i = 0; i < r; i++){
            randOfBit.add(i);
        }
        for(int i = 0; i < sizeOfBin; i++){
            randOfBlock.add(i);
        }
        Collections.shuffle(randOfBlock);
        errorGen(per, sizeOfBin);

        for(int i = 0; i < (per * sizeOfBin) / 100; i++) {
            System.out.println(blocksError[i]);
            blocksError[i] = HammingDecode(blocksError[i]);
        }
        String dec = "";
        Boolean finded = false;
        for(int i = 0; i < sizeOfBin; i++){
            finded = false;
            for(int k = 0; k < (sizeOfBin * per) / 100; k++){
                if(i == randOfBlock.get(k)){
                    dec += blocksError[k];
                    finded = true;
                    System.out.println("finded");
                }
            }
            if(!finded){
                dec += blocks[i];
            }
        }
        text += "String with no errors: " + dec + "\nDecoded string: ";
        int j = 0;
        for(int i = 0; i < dec.length(); i++){
            if(i % r == 0){
                j = 0;
            }
            if(Math.pow(2, j) != (i % r) + 1){
                text += dec.charAt(i);
            }else{
                j++;
            }
        }
    }
}
//000101100001111110
//000101100001111110
//11010011100110110100111111110010110