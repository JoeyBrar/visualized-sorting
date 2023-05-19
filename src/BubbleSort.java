import java.util.ArrayList;

public class BubbleSort {
    private ArrayList<Integer> arr;

    public BubbleSort(ArrayList<Integer> arr) {
        this.arr = arr;
    }

    public boolean isSorted() {
        return this.arr.equals(sort(this.arr, this.arr.size()));
    }

    public ArrayList<Integer> sort(ArrayList<Integer> arr, int n) {
        System.out.println(arr.size() + ", " + n);
        for (int i=0;i<n;i++) {
            for(int j=1; j < (n-i); j++){
                if(arr.get(j-1)>arr.get(j)){
                    int temp = arr.get(j-1);
                    arr.set(j-1, j);
                    arr.set(j, temp);
                }
            }
        }
        return arr;
    }

    public ArrayList<Integer> getArr() {
        return arr;
    }

}
