import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Display implements ChangeListener, ActionListener {
    private ArrayList<Integer> arr;
    private final JFrame o = new JFrame();
    private final JButton mSort = new JButton("Merge Sort");
    private final JButton iSort = new JButton("Insertion Sort");
    private final JButton sSort = new JButton("Selection Sort");
    private final JButton bSort = new JButton("Bubble Sort");
    private final JButton rSort = new JButton("Bogo Sort");
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 60, 25); //1410
    private final JLabel displayedArr;
    private double displaySpeed;
    JPanel panel = new JPanel();
    private final JTextField arrLength;
    Graphics2D g2;
    ArrayList<Rectangle2D> arrRects;
    InsertionSort iSortObj;
    BubbleSort bSortObj;
    SelectionSort sSortObj;
    MergeSort mSortObj;
    BogusSort rSortObj;

    public Display(ArrayList<Integer> arr) {
        this.arr = arr;
        this.displayedArr = new JLabel(this.arrToStr(this.arr));

        final JButton[] buttons = {bSort, sSort, iSort, mSort, rSort};

        int xPos = 0, yPos = 0, width = 100, height = 40;
        for (JButton i : buttons) {
            i.setBounds(xPos, yPos, width, height);
            i.addActionListener(this::actionPerformed);
            o.add(i);
            xPos += 100;
        }

        final JLabel sliderDescription = new JLabel("Adjust number range & sorting speed:");
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

        arrLength = new JTextField("arr len");
        arrLength.setBounds(265, 40, 60, 40);
        arrLength.addActionListener(this::actionPerformed);
        o.add(arrLength);

        arrRects = new ArrayList<>();
    }

    public ArrayList<Integer> randomize(int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (n < 10) {
                arr.add((int) (Math.random() * 9) + 1);
            } else {
                arr.add((int) (Math.random() * n) + 1);
            }
        }
        return arr;
    }

    public String arrToStr(ArrayList<Integer> arr) {
        String result = "";
        for (int i : arr) {
            result += i + ", ";
        }
        return result;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int n = ((JSlider) e.getSource()).getValue();
        this.arr = randomize(n);
        this.displaySpeed = 10000.0 / n;
        this.displayedArr.setText(this.arrToStr(this.arr));
        this.o.add(this.displayedArr);
        displayState(this.arr);
        arrLength.setText(String.valueOf(slider.getValue()));
        this.o.add(arrLength);
    }

    public void displayState(ArrayList<Integer> arr) {
        Rectangle bounds = panel.getBounds();
        g2.clearRect(15, 200, bounds.width + 1, bounds.height + 1);
        int x = 15, y = 200;
        int biggest = -1;
        for (int i : arr) {
            if (biggest < i) biggest = i;
        }
        for (int i : arr) {
            Rectangle2D r = new Rectangle2D.Double(x, y, 1410.0 / arr.size(), calculateBarHeightRelativeToMaxBarHeight(i, biggest));
            arrRects.add(r);
            g2.fill(r);
            x += 1410.0 / arr.size();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NumberFormatException {
        if (e.getSource().equals(bSort)) {
            bSortObj = new BubbleSort(arr);
            for (int i = 1; i <= this.arr.size(); i++) {
                System.out.println(i);
                this.arr = bSortObj.sort(arr, i);
                displayState(this.arr);
                this.displayedArr.setText(this.arrToStr(this.arr));
                this.o.add(this.displayedArr);

                try {
                    TimeUnit.MILLISECONDS.sleep((long) displaySpeed);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource().equals(sSort)) {
            sSortObj = new SelectionSort(arr);
            for (int i = 1; i <= this.arr.size(); i++) {
                System.out.println(i);
                this.arr = sSortObj.sort(arr, i);
                displayState(this.arr);
                this.displayedArr.setText(this.arrToStr(this.arr));
                this.o.add(this.displayedArr);

                try {
                    TimeUnit.MILLISECONDS.sleep((long) displaySpeed);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if (e.getSource().equals(iSort)) {
            iSortObj = new InsertionSort(arr);
            for (int i = 1; i <= this.arr.size(); i++) {
                System.out.println(i);
                this.arr = iSortObj.sort(arr, i);
                displayState(this.arr);
                this.displayedArr.setText(this.arrToStr(this.arr));
                this.o.add(this.displayedArr);

                try {
                    TimeUnit.MILLISECONDS.sleep((long) displaySpeed);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        } else if (e.getSource().equals(mSort)) {
            mSortObj = new MergeSort(arr);
            mSortObj.mergeSort(0, arr.size()-1);
            ArrayList<ArrayList<Integer>> snapshots = mSortObj.getSnapshots();
            for(ArrayList<Integer> i : snapshots) {
                displayState(i);
                try {
                    TimeUnit.MILLISECONDS.sleep((long) displaySpeed);
                    TimeUnit.MILLISECONDS.sleep((long) displaySpeed);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource().equals(rSort)) {
            rSortObj = new BogusSort();
            ArrayList<Integer> rSortArr = arr;
            while(!rSortObj.isSorted(rSortArr)) {
                arr = rSortObj.sortOnce(arr);
                displayState(arr);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else {
            int n = Integer.parseInt(arrLength.getText());
            this.arr = randomize(n);
            this.displaySpeed = 10000.0 / n;
            this.displayedArr.setText(this.arrToStr(this.arr));
            this.o.add(this.displayedArr);
            displayState(this.arr);
            if (n <= 60 && n >= 0) {
                slider.setValue(n);
            } else if (n <= 0) {
                return;
            } else {
                slider.setValue(60);
            }
//                if(n>1410||n<1) {
//                    ;
//                } else {
//                    slider.setValue(n);
//                }
            this.o.add(slider);
        }
    }

    public double calculateBarHeightRelativeToMaxBarHeight(double n, double biggest) {
        return 580 * (n / biggest);
    }
}
