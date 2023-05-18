import java.util.ArrayList;

public class InsertionSort {
    private ArrayList<Integer> arr;

    public InsertionSort(ArrayList<Integer> arr) {
        this.arr = arr;
    }

    public boolean isSorted() {
        return this.arr.equals(sort(this.arr, this.arr.size()));
    }

    public ArrayList<Integer> sort(ArrayList<Integer> arr, int n) {
        for (int i=1;i<=n;i++) {
            int next = arr.get(i);
            // find the insertion location while moving all larger element up
            int j = i;
            while (j>0&&arr.get(j - 1)>next) {
                int temp = arr.get(j);
                arr.set(j, arr.get(j-1));
                arr.set(j-1, temp);
                j--;
            }
            // insert the element
            arr.set(j, next);
        }
        return arr;
    }

    public ArrayList<Integer> getArr() {
        return arr;
    }

}
