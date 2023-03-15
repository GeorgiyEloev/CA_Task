package tasksForCA.task_2_n.task6;

public class Task6 {

  public static void main(String[] args) {
    String[] arr = new String[]{"erer", "sad", "qwe", "sad", "qwe", "sad", "qwe", "sad", "sad", "sad"};

    var iterator = new IteratorArray(arr);

    while (iterator.hasNext()) {
      System.out.println((String) iterator.next());
    }
  }
}
