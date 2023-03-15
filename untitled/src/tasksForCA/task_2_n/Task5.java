package tasksForCA.task_2_n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Task5 {

  public static void main(String[] args) {
    ArrayList<String> arr = new ArrayList<>(Arrays.stream(new String[]{"erer", "sad", "qwe", "sad", "qwe", "sad", "qwe", "sad", "sad", "sad"}).toList());

    ArrayList<String> arr1 = (ArrayList<String>) removeDuplicates(arr);

    for (String item : arr1) {
      System.out.println(item);
    }

  }

  public static <T> Collection<T> removeDuplicates(Collection<T> collection) {
    Collection<T> result = new ArrayList<>();

    for (T item : collection) {
      if (!result.contains(item)) {
        result.add(item);
      }
    }

    return result;
  }

}
