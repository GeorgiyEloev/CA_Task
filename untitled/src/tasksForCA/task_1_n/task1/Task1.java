package tasksForCA.task_1_n.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {
  static int maxFromArr = 0;
  static int minFromArr = 0;

  public static void main(String[] args) throws InterruptedException, IOException {

    ArrayList<Integer> arr = getArray("/Users/user/Desktop/stude/CA_Task/untitled/src/tasksForCA/task_1_n/task1/text.txt");

    Thread threadMax = new Thread(new Runnable() {
      @Override
      public void run() {
        int max = arr.get(0);
        for (Integer i : arr) {
          if (i > max) {
            max = i;
          }
        }
        maxFromArr = max;
      }
    });

    Thread threadMin = new Thread(new Runnable() {
      @Override
      public void run() {
        int min = arr.get(0);
        for (int i : arr) {
          if (i < min) {
            min = i;
          }
        }
        minFromArr = min;
      }
    });

    threadMax.start();
    threadMin.start();

    threadMax.join();
    threadMin.join();

    System.out.println(maxFromArr);
    System.out.println(minFromArr);
  }

  static ArrayList<Integer> getArray(String path) {
    File file = new File(path);
    var arr = new ArrayList<Integer>();

    try {
      Scanner scan = new Scanner(file);
      while (scan.hasNextInt()) {
        arr.add(scan.nextInt());
      }
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    return arr;
  }
}

