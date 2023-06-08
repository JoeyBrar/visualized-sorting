import java.util.ArrayList;
import java.lang.Math;
public class BogusSort {
    public boolean isSorted(ArrayList<Integer> arr) {
        for (int i=1;i<arr.size();i++){
            if (arr.get(i)<arr.get(i-1)) return false;
        }
        return true;
    }

    public ArrayList<Integer> sortOnce(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int pos = (int) (Math.random() * arr.size()-1);
            int temp = arr.get(i);
            arr.set(i, arr.get(pos));
            arr.set(pos, temp);
        }
        return arr;
    }
}
