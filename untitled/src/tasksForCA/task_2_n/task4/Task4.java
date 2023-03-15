package tasksForCA.task_2_n.task4;


import java.util.ArrayList;
import java.util.Arrays;

public class Task4 {

  public static void main(String[] args) {

    String[] fruits = {"ывауц", "кцуцуккуц", "цкууцк", "кук", "кerук", "кeук", "куrк"};
    Filter<String> filter = s -> s.length() > 5;
    String[] filteredFruits = filter(fruits, filter);
    System.out.println(Arrays.toString(filteredFruits));

  }

  public static <T> T[] filter(T[] array, Filter<T> filter) {
    ArrayList<T> result = new ArrayList<>();
    for (T item : array) {
      if (filter.apply(item)) {
        result.add(item);
      }
    }
    return result.toArray(Arrays.copyOf(array, result.size()));
  }
}
