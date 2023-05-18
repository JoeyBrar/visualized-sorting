import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Display implements ChangeListener, ActionListener {
    private ArrayList<Integer> arr;
    private final JFrame o = new JFrame();
    private final JButton mSort = new JButton("Merge Sort");
    private final JButton iSort = new JButton("Insertion Sort");
    private final JButton sSort = new JButton("Selection Sort");
    private final JButton bSort = new JButton("Bubble Sort");
    private final JButton qSort = new JButton("Quick Sort");
    private final JButton bkSort = new JButton("Bucket Sort");
    private final JButton rSort = new JButton("Radix Sort");
    private final JButton[] buttons = {bSort, iSort, sSort, mSort, qSort, bkSort, rSort};
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 60, 25);
    private final JLabel sliderDescription = new JLabel("Adjust number range & sorting speed:");
    private JLabel displayedArr;
    private double displaySpeed;
    private int maxBarHeight = 580;
    JPanel panel = new JPanel();
    Graphics2D g2 = null;
    ArrayList<Rectangle2D> arrRects;
    InsertionSort iSortObj;

    public Display(ArrayList<Integer> arr) {
        this.arr = arr;
        this.displayedArr = new JLabel(this.arrToStr(this.arr));

        int xPos = 0, yPos = 0, width = 100, height = 40;
        for(JButton i : buttons) {
            i.setBounds(xPos, yPos, width, height);
            i.addActionListener(this);
            o.add(i);
            xPos += 100;
        }

        sliderDescription.setBounds(15, 40, 700, height);
        slider.setBounds(0, 80, 700, height);
        slider.addChangeListener(this::stateChanged);
        displayedArr.setBounds(15, 120, 1440, height);
        o.add(sliderDescription);
        o.add(slider);
        o.add(displayedArr);

        o.setLayout(null);
        o.setSize(1440, 900);
        o.setVisible(true);

        panel.setBounds(15, 200, 1410, 680);
        o.add(panel);

        Graphics g = o.getGraphics();
        g2 = (Graphics2D) g;

        arrRects = new ArrayList<Rectangle2D>();
    }

    public ArrayList<Integer> randomize(int n) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=0;i<n;i++) {
            if(n<10) {
                arr.add((int) (Math.random()*9)+1);
            } else {
                arr.add((int) (Math.random()*n)+1);
            }
        }
        return arr;
    }

    public String arrToStr(ArrayList<Integer> arr){
        String result = "";
        for(int i : arr) {
            result += i + ", ";
        }
        return result;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int n = ((JSlider)e.getSource()).getValue();
        this.arr = randomize(n);
        this.displaySpeed = 10000/n;
        this.displayedArr.setText(this.arrToStr(this.arr));
        this.o.add(this.displayedArr);
        displayState(this.arr, this.g2);
    }

    public void displayState(ArrayList<Integer> arr, Graphics g) {
        Rectangle bounds = panel.getBounds();
        g2.clearRect(15, 200, bounds.width+1, bounds.height+1);
        int x = 15, y = 200;
        int biggest = -1;
        for(int i : arr) {
            if(biggest<i) biggest = i;
        }
        for(int i : arr) {
            Rectangle2D r = new Rectangle2D.Double(x, y, 1410.0 / arr.size(), calculateBarHeightRelativeToMaxBarHeight(i, biggest));
            arrRects.add(r);
            g2.fill(r);
            x += 1410.0 / arr.size();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(iSort)) {
            iSortObj = new InsertionSort(arr);
            for(int i=1;i<=this.arr.size();i++){
                System.out.println(i);
                this.arr = iSortObj.sort(arr, i);
                displayState(this.arr, this.g2);
                this.displayedArr.setText(this.arrToStr(this.arr));
                this.o.add(this.displayedArr);


                try {
                    TimeUnit.MILLISECONDS.sleep((long)displaySpeed);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        else if (e.getSource().equals(sSort)) {

        } else if (e.getSource().equals(mSort)) {

        } else if (e.getSource().equals(qSort)) {

        }
    }

    public double calculateBarHeightRelativeToMaxBarHeight(double n, double biggest) {
        return maxBarHeight * (n/biggest);
    }

}

/*
TODO:
    -Add number input to change arr length(same w/ speed)
    -Set speed of alg using slider
    -Add apcsa sorting algs
    -update readme
    -remove useless buttons, sort rest of buttons
    -Add colors for selected, compared, and changed nums
    -Final touches
    -add final algs and colors for said algs
 */