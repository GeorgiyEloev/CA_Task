package tasksForCA.task_1_n.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {
  static int maxFromArr = 0;
  static int minFromArr = 0;

  public static void main(String[] args) throws InterruptedException {

    var arr = getArray("/Users/user/Desktop/stude/CA_Task/untitled/src/tasksForCA/task_1_n/task1/output-onlinenumbertools.txt");


    Thread thread_max = new Thread(new Runnable() {
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

    Thread thread_min = new Thread(new Runnable() {
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

    thread_max.start();
    thread_min.start();

    thread_max.join();
    thread_min.join();

    System.out.println(maxFromArr);
    System.out.println(minFromArr);
  }

  static ArrayList<Integer> getArray(String path) {
    File file = new File(path);
    var arr = new ArrayList<Integer>();

    try {
      Scanner scan = new Scanner(file);

      String str = scan.nextLine();
      String[] arrStr = str.split(", ");
      for (int i = 0; i < arrStr.length; i++) {
        arr.add(Integer.parseInt(arrStr[i]));
      }
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    return arr;
  }
}

