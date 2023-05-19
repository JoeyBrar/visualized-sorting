import java.util.ArrayList;

public class SelectionSort {
    private ArrayList<Integer> arr;

    public SelectionSort(ArrayList<Integer> arr) {
        this.arr = arr;
    }

    public boolean isSorted() {
        return this.arr.equals(sort(this.arr, this.arr.size()));
    }

    public ArrayList<Integer> sort(ArrayList<Integer> arr, int n) {
        System.out.println(arr.size() + ", " + n);
        for (int i=0;i<n-1;i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(minElementIndex)>arr.get(j)) {
                    minElementIndex = j;
                }
            }
            if (minElementIndex != i) {
                int temp = arr.get(i);
                arr.set(i, minElementIndex);
                arr.set(minElementIndex, temp);
            }
        }
        return arr;
    }

    public ArrayList<Integer> getArr() {
        return arr;
    }

}


