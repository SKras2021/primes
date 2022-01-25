import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class primeFractal2D{

public static void main(String[] args) {
    //testFrame.setResizable();
    int tillPrime = Integer.parseInt(args[0]);
    int size = Integer.parseInt(args[1]);
    int window = Integer.parseInt(args[2]);
    primeFractal2D prm2 = new primeFractal2D();
    ArrayList<Integer> path = prm2.getSequence(tillPrime);
    int k = 0;
    int x1 = window/2;
    int x2 = window/2;
    int y1 = window/2;
    int y2 = window/2;
    ArrayList<Integer> x = new ArrayList<Integer>();
    ArrayList<Integer> y = new ArrayList<Integer>();
    for(int i = 0; i < path.size(); i++){
        switch(k){
            case 0:
                //up
                y2 = y1 + path.get(i);
                break;
                case 1:
                //right
                x2 = x1 + path.get(i);
                break;
                case 2:
                //down
                y2 = y1 - path.get(i);
                break;
                case 3:
                //left
                x2 = x1 - path.get(i);
                //reset
                k = -1;
                break;
                default:
                //Something is wrong
                x1 = 0;
                x2 = 0;
                y1 = 0;
                y2 = 0;
        }
        //Refited for HTML so code looks a bit wierd
        k++;
        Color randomColor = new Color(0, 0, 0);
        x.add(x1);
        x.add(x2);
        y.add(y1);
        y.add(y2);
        x1 = x2;
        y1 = y2;
        }
    WriteHTML whtml = new WriteHTML(x, y, size, window);
    whtml.fillPrimes();
}

//2 and prime change based on input
public ArrayList<Integer> getSequence(int lastPrime) {
        int n = lastPrime;
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        int max = (int) Math.sqrt(n);

        for (int i = 2; i <= max; i++) {
            if (primes[i] == true) {
                for (int j = 0; j <= n; j++) {
                    int index = i * i + j * i;
                    if (index >= n) {
                        break;
                    }
                    primes[index] = false;
                }
            }
        }
        ArrayList<Integer> prm = new ArrayList<Integer>();
        ArrayList<Integer> fprm = new ArrayList<Integer>();

        for (int i = 2; i < n; i++) {
            if (primes[i] == true) {
                prm.add(i);
            }
        }

        fprm.add(2);
        for (int i = 0; i < prm.size() - 1; i++) {
            fprm.add(prm.get(i + 1) - prm.get(i));
        }
        return fprm;
    }
}