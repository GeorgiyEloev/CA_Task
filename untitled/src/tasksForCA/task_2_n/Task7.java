package tasksForCA.task_2_n;

import java.util.HashMap;
import java.util.Map;

public class Task7 {

  public static void main(String[] args) {
    String[] arr = new String[]{"erer", "sad", "qwe", "sad", "qwe", "sad", "qwe", "sad", "sad", "sad"};

    arrayToMap(arr);

    System.out.println(arr[10]);
  }

  public static <K> Map<K, Integer> arrayToMap(K[] ks) {
    Map<K, Integer> map = new HashMap<>();

    for (K key : ks) {
      map.merge(key, 1, Integer::sum);
    }

    return map;
  }


}
