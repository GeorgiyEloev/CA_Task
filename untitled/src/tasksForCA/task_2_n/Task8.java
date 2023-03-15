package tasksForCA.task_2_n;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Task8 {
  public static void main(String[] args) {

    var map = new HashMap<Integer, String>();

    map.put(1,"ewrwer");
    map.put(2,"мсч");
    map.put(3,"ммммм");
    map.put(4,"ewrwer");
    map.put(5,"чсм");
    map.put(6,"ewrwer");
    map.put(7,"счм");
    map.put(8,"куе");

    Map<String, Integer> swapMap = swapKeyAndValue(map);
  }

  public static <V, K> Map<V,K> swapKeyAndValue(Map<K,V> map) {
    Set<K> keys = map.keySet();

    Map<V, K> swapMap = new HashMap<>();

    for (K key: keys) {
      swapMap.put(map.get(key), key);
    }

    return swapMap;
  }
}
