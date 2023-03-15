package tasksForCA.task_1_n.task2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SaveAsThread implements Runnable {

  private int[] arr;
  private String path;

  public SaveAsThread(int[] arr, String path) {
    this.arr = arr;
    this.path = path;
  }

  @Override
  public void run() {
    try {
      FileWriter pw = new FileWriter(path, true);
      System.out.println();

      pw.write(arrToString());

      pw.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private String arrToString() {
    String str = "";
    for (int i : arr) {
      str += i + ", ";
    }
    return str;
  }
}
