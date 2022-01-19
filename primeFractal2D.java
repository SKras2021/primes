import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class primeFractal2D{

public static void main(String[] args) {
    Runnable runnable = new Runnable() {
    public void run() {
    JFrame testFrame = new JFrame("Prime fractal - 2D");
    FrameDragListener frameDragListener = new FrameDragListener(testFrame);
    testFrame.setUndecorated(true);
    testFrame.addMouseListener(frameDragListener);
    testFrame.addMouseMotionListener(frameDragListener);
    testFrame.pack();
    testFrame.setLocationRelativeTo(null);
    testFrame.setVisible(true);
    testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //testFrame.setResizable();
    int sizeX = 2000;
    int sizeY = sizeX;
    final LinesComponent comp = new LinesComponent();
    comp.setPreferredSize(new Dimension(sizeX, sizeY));
    testFrame.getContentPane().add(comp, BorderLayout.CENTER);
    JPanel buttonsPanel = new JPanel();
    JTextField tf1 = new JTextField("num of lines");
    JButton newLineButton = new JButton("New Line");
    JButton clearButton = new JButton("Clear");
    buttonsPanel.add(newLineButton);
    buttonsPanel.add(clearButton);
    buttonsPanel.add(tf1);
    testFrame.getContentPane().add(buttonsPanel, BorderLayout.NORTH);
    primeFractal2D prm2 = new primeFractal2D();
    newLineButton.addActionListener(new ActionListener() {
    //Color randomColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        @Override
        public void actionPerformed(ActionEvent e) {
            comp.clearLines();
            ArrayList<Integer> path = prm2.getSequence(Integer.parseInt(tf1.getText()));

            int k = 0;

            int intx = sizeX/2;
            int inty = sizeX/2;
            int x1 = sizeX/2;
            int x2 = sizeX/2;
            int y1 = sizeX/2;
            int y2 = sizeX/2;

            for(int i = 0; i < path.size(); i++){
                switch(k){
                    case 0:
                    //up
                    inty+=path.get(i);
                    y1 = inty;
                    y2 = y1 + path.get(i);
                    break;
                    case 1:
                    //right
                    intx+=path.get(i);
                    x1 = intx;
                    x2 = x1 + path.get(i);
                    break;
                    case 2:
                    //down
                    inty-=path.get(i);
                    y1 = inty;
                    y2 = y1 - path.get(i);
                    break;
                    case 3:
                    //left
                    intx-=path.get(i);
                    x1 = intx;
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
                    System.out.println("Error, flawed shape generated");
                }
                k++;
                Color randomColor = new Color(0, 0, 0);
                comp.addLine(x1, y1, x2, y2, randomColor);
            }
    }
    });
    clearButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            comp.clearLines();
        }
    });
    testFrame.pack();
    testFrame.setVisible(true);
}
};
SwingUtilities.invokeLater(runnable);
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
//Frame drag listener
public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
}
}