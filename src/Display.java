import javax.swing.*;
import java.util.ArrayList;

public class Display {
    private JFrame o = new JFrame();
    private JButton mSort = new JButton("Merge Sort");
    private JButton iSort = new JButton("Insertion Sort");
    private JButton sSort = new JButton("Selection Sort");
    private JButton bSort = new JButton("Bubble Sort");
    private JButton qSort = new JButton("Quick Sort");
    private JButton bkSort = new JButton("Bucket Sort");
    private JButton rSort = new JButton("Radix Sort");
    private JButton[] buttons = {mSort, iSort, sSort, bSort, qSort, bkSort, rSort};
    private JButton addData = new JButton("Change data set");
    private ArrayList<Integer> arr;

    public Display(ArrayList<Integer> arr) {
        this.arr = arr;

        //TODO make gui display the array to be sorted, and make it possible to change the array(modify data.txt)

        o.setLayout(null);
        int xPos = 0, yPos = 0, width = 100, height = 40;
        for(JButton i : buttons) {
            i.setBounds(xPos, yPos, width, height);
            o.add(i);
            xPos += 100;
        }
        addData.setBounds(0, 40, 120, height);
        o.add(addData);

        o.setSize(1440, 900);
        o.setVisible(true);

    }

}
