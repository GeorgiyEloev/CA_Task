package tasksForCA.task_1_n.task2;

public class Task2 {

  public static void main(String[] args) {
    int[] arr1 = {1, 5, 1, 2, 3};
    int[] arr2 = {31, 13, 31, 123, 13};
    int[] arr3 = {4, 645, 654, 3, 1};
    String path = "/Users/user/Desktop/stude/CA_Task/untitled/src/tasksForCA/task_1_n/task2/file.txt";

    Thread save1 = new Thread(new SaveAsThread(arr1, path));
    Thread save2 = new Thread(new SaveAsThread(arr2, path));
    Thread save3 = new Thread(new SaveAsThread(arr3, path));

    save1.start();
    save2.start();
    save3.start();
  }
}
