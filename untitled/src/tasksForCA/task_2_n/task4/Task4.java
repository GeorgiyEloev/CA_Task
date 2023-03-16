package tasksForCA.task_2_n.task4;


import java.util.ArrayList;
import java.util.Arrays;

public class Task4 {

  public static void main(String[] args) {

    String[] fruits = {"ывауц", "кцуцуккуц", "цкууцк", "кук", "кerук", "кeук", "куrк"};
    Filter filter = s -> {
      String str = (String) s;
     return str.length() > 5;
    };
    String[] filteredFruits = (String[]) filter(fruits, filter);
    System.out.println(Arrays.toString(filteredFruits));

  }

  public static Object[] filter(Object[] array, Filter filter) {
    ArrayList<Object> result = new ArrayList<>();
    for (Object item : array) {
      if (filter.apply(item)) {
        result.add(item);
      }
    }
    return result.toArray(Arrays.copyOf(array, result.size()));
  }
}
