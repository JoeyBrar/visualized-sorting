import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.ArrayList;

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
    private final JButton[] buttons = {mSort, iSort, sSort, bSort, qSort, bkSort, rSort};
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 500, 25);
    private final JLabel sliderDescription = new JLabel("Adjust number range & sorting speed:");
    private JLabel displayedArr;
    private double barWidth, displaySpeed;
    private int maxBarHeight = 580;

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
        this.barWidth = 1240/n; //1440, but we take 100 from left and right sides
        this.displaySpeed = 1/n; //in ms? needs adjustment
        this.displayedArr.setText(this.arrToStr(this.arr));
        this.o.add(displayedArr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public double calculateBarHeightRelativeToMaxBarHeight(double n, double biggest) {
        return maxBarHeight * (n/biggest);
    }

}

/*
TODO:
    -Test to see if boundaries work
    -Display number bars
    -Finish 1 sorting alg
    -Set speed of alg using slider
    -Finish button for said alg
    -Add colors for selected, compared, and changed nums
    -Add rest of sorting algs
    -Change random slider alg?
    -Final touches, add description of each alg?
 */