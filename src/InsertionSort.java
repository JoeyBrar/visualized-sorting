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
        System.out.println(arr.size() + ", " + n);
        for (int i=1;i<n;i++) {
            int next = arr.get(i);
            int j = i-1;
            while (j>=0&&arr.get(j)>next) {
                arr.set(j+1, arr.get(j));
                j--;
            }
            arr.set(j+1, next);
        }
        return arr;
    }

    public ArrayList<Integer> getArr() {
        return arr;
    }

}
