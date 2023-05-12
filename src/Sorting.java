import java.util.ArrayList;
import javax.swing.*;

public class Sorting {
    private ArrayList<Integer> toSortList;
    private JFrame frame = new JFrame();

    public Sorting(ArrayList<Integer> toSortList) {
        this.toSortList = toSortList;
    }

    public boolean sortOnce(int i) {
        return false;

    }

    public double calculateDisplaySpeed() {
        return 0.0;
    }

    public void displaySort(ArrayList<Integer> arr){
        //display first case


        for(int i=0;i<arr.size();i++) {
            while(!sortOnce(i)) {
                //display
            }
        }
    }

}

