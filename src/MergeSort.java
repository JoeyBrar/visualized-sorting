import java.util.ArrayList;

public class MergeSort {
    private ArrayList<Integer> arr;
    private int comparing;
    private int comparingTo;
    private ArrayList<ArrayList<Integer>> snapshots;

    public MergeSort(ArrayList<Integer> arr) {
        this.arr=arr;
        snapshots = new ArrayList<ArrayList<Integer>>();
    }

    public void mergeSort(int indexStart, int indexEnd) {
        if (indexStart < indexEnd && (indexEnd - indexStart) >= 1) {
            int middleElement = (indexEnd + indexStart) / 2;

            mergeSort(indexStart, middleElement);
            mergeSort(middleElement + 1, indexEnd);

            merge(indexStart, middleElement, indexEnd);
        }
    }

    public void merge(int indexStart, int indexMiddle, int indexEnd) {
        ArrayList<Integer> tempArray = new ArrayList<>();

        int getLeftIndex = indexStart;
        int getRightIndex = indexMiddle + 1;

        while (getLeftIndex <= indexMiddle && getRightIndex <= indexEnd) {

            if (arr.get(getLeftIndex) <= arr.get(getRightIndex)) {
                tempArray.add(arr.get(getLeftIndex));
                snapshots.add(tempArray);
                getLeftIndex++;
            } else {
                tempArray.add(arr.get(getRightIndex));
                snapshots.add(tempArray);
                getRightIndex++;
            }
        }

        while (getLeftIndex <= indexMiddle) {
            tempArray.add(arr.get(getLeftIndex));
            snapshots.add(tempArray);
            getLeftIndex++;
        }

        while (getRightIndex <= indexEnd) {
            tempArray.add(arr.get(getRightIndex));
            snapshots.add(tempArray);
            getRightIndex++;
        }

        for (int i = 0; i < tempArray.size(); indexStart++) {
            arr.set(indexStart, tempArray.get(i++));

        }

    }
    public ArrayList<ArrayList<Integer>> getSnapshots() {
        System.out.println(snapshots);
        return removeDuplicates(snapshots);
    }
    public ArrayList<ArrayList<Integer>> removeDuplicates(ArrayList<ArrayList<Integer>> snapshots) {
        for(int i=0;i<snapshots.size();i++) {
            ArrayList<Integer> compareWith = snapshots.get(i);
            for (int j=1;j<snapshots.size();j++) {
                ArrayList<Integer> compareTo = snapshots.get(j);
                if(compareWith.equals(compareTo)) snapshots.remove(compareTo);
            }
        }
        return snapshots;
    }
}

