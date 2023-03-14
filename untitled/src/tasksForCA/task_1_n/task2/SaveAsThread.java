package tasksForCA.task_1_n.task2;

import java.io.*;
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
    File file = new File(path);
    try {
      FileWriter pw = new FileWriter(path, true);
      for (int i : arr) {
        pw.write(i + ", ");
      }
      pw.close();
    }  catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
