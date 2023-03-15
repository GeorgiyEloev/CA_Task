package tasksForCA.task_2_n.task6;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorArray {

  private Object[] arrDefault;

  private int index = 0;

  public IteratorArray(Object[] arrDefault) {
    this.arrDefault = arrDefault;
  }

  public boolean hasNext() {
    if (index < arrDefault.length ) {
      return true;
    }
    return false;
  }

  public <T> T next() {
    if (index < arrDefault.length ) {
      T item = (T) arrDefault[index];
      index++;
      return item;
    }
    return null;
  }

}
