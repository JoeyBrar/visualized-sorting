import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");
        Display d = new Display(getData());

    }

    public static ArrayList<Integer> getData() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("./assets/data.txt"));
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while(reader.hasNext()) {
            String data = reader.nextLine();
            String[] nums = data.split(",");
            for(String i : nums) {
                arr.add(Integer.parseInt(i));
            }
        }
        return arr;
    }
}