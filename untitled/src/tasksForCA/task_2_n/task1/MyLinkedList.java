package tasksForCA.task_2_n.task1;


import java.lang.reflect.Array;
import java.util.*;

public class MyLinkedList<E> implements List<E> {

  private int size = 0;

  transient Item<E> first;

  transient Item<E> last;

  public MyLinkedList() {
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean contains(Object o) {
    int index = indexOf(o);
    return index >= 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new Itr();
  }

  @Override
  public Object[] toArray() {
    Object[] array = new Object[size];

    int i = 0;
    for (Item<E> item = first; item != null; item = item.next) {
      array[i] = item.value;
      i++;
    }

    return array;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    if (a.length < size)
      a = (T[]) Array.newInstance(
              a.getClass().getComponentType(), size);

    int i = 0;
    Object[] res = a;
    for (Item<E> item = first; item != null; item = item.next) {
      res[i] = item.value;
      i++;
    }

    if (a.length > size)
      a[size] = null;

    return a;
  }

  @Override
  public boolean add(E item) {
    Item<E> oldLast = last;
    Item<E> newItem = new Item<>(oldLast, item, null);
    last = newItem;
    if (oldLast != null) {
      oldLast.next = newItem;
    } else {
      first = newItem;
    }
    size++;
    return true;
  }

  private E remove(Item<E> item) {
    Item<E> prev = item.prev;
    Item<E> next = item.next;
    if (prev == null) {
      next.prev = null;
      first = next;
    } else {
      prev.next = next;
    }

    if (next == null) {
      prev.next = null;
      last = prev;
    } else {
      next.prev = prev;
    }
    E value = item.value;
    item = null;
    size--;
    return value;
  }

  @Override
  public boolean remove(Object o) {
    for (Item<E> item = first; item != null; item = item.next) {
      if (o.equals(item.value)) {
        remove(item);
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    if (c.size() == 0) {
      return false;
    }

    for (E item : c) {
      add(item);
    }
    return true;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    if (c.size() == 0) {
      return false;
    }
    Item<E> itemNext = getNode(index);

    for (E item : c) {
      addInIndex(item, itemNext);
    }

    return true;
  }

  private boolean addInIndex(E value, Item<E> indexItem) {
    Item<E> oldPrev = indexItem.prev;
    Item<E> newItem = new Item<>(oldPrev, value, indexItem);
    if (oldPrev == null) {
      first = newItem;
    } else {
      oldPrev.next = newItem;
    }

    indexItem.prev = newItem;

    size++;
    return true;
  }

  private Item<E> getNode(int index) {
    int i = 0;
    for (Item<E> item = first; item != null; item = item.next) {
      if (i == index) {
        return item;
      }
      i++;
    }
    return null;
  }

  @Override
  public void clear() {
    for (Item<E> item = first; item != null; ) {
      Item<E> next = item.next;
      item.value = null;
      item.next = null;
      item.prev = null;
      item = next;
    }
    first = null;
    last = null;
    size = 0;
  }

  @Override
  public E get(int index) {
    int i = 0;
    for (Item<E> item = first; item != null; item = item.next) {
      if (i == index) {
        return item.value;
      }
      i++;
    }

    return null;
  }

  @Override
  public E set(int index, E value) {
    Item<E> item = getNode(index);
    if (item != null) {
      item.value = value;
      return item.value;
    }
    return null;
  }

  @Override
  public void add(int index, E value) {
    Item<E> item = getNode(index);
    if (item != null) {
      addInIndex(value, item);
    }
  }

  @Override
  public E remove(int index) {
    int i = 0;
    for (Item<E> item = first; item != null; item = item.next) {
      if (i == index) {
        return remove(item);
      }
      i++;
    }

    return null;
  }

  @Override
  public int indexOf(Object o) {
    int index = 0;

    for (Item<E> item = first; item != null; item = item.next) {
      if (o.equals(item.value)) {
        return index;
      }
      index++;
    }

    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    int index = size;

    for (Item<E> item = last; item != null; item = item.prev) {
      if (o.equals(item.value)) {
        return index;
      }
      index--;
    }

    return -1;
  }

  @Override
  public ListIterator<E> listIterator() {
    return new ListItr();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return new ListItr(index);
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    ArrayList<E> list = new ArrayList<>();

    int i = 0;
    for (Item<E> item = first; item != null; item = item.next) {
      if (i >= fromIndex && i <= toIndex) {
        list.add(item.value);
      }
      i++;
    }

    return list;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    boolean flag = false;

    for (Item<E> item = first; item != null; item = item.next) {
      if (!c.contains(item.value)) {
        remove(item);
        flag = true;
      }
    }

    return flag;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    boolean flag = false;

    for (Item<E> item = first; item != null; item = item.next) {
      if (c.contains(item.value)) {
        remove(item);
        flag = true;
      }
    }

    return flag;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    boolean flag = true;

    for (Object item : c) {
      if (!contains(item)) {
        flag = false;
      }
    }

    return flag;
  }


  private static class Item<E> {
    E value;
    Item<E> next;
    Item<E> prev;

    Item(Item<E> prev, E value, Item<E> next) {
      this.value = value;
      this.next = next;
      this.prev = prev;
    }
  }

  private class Itr implements Iterator<E> {

    private int index = 0;

    @Override
    public boolean hasNext() {
      return get(index) != null;
    }

    @Override
    public E next() {
      if (index < size()) {
        E item = (E) get(index);
        index++;
        return item;
      }
      return null;
    }
  }


  private class ListItr implements ListIterator<E> {

    private int index = 0;

    public ListItr() {
    }

    public ListItr(int index) {
      this.index = index;
    }


    @Override
    public boolean hasNext() {
      return get(index) != null;
    }

    @Override
    public E next() {
      if (index < size()) {
        E item = (E) get(index);
        index++;
        return item;
      }
      return null;
    }

    @Override
    public boolean hasPrevious() {
      if (index >= 0) {
        return get(index) != null;
      }
      return false;
    }

    @Override
    public E previous() {
      if (index >= 0) {
        E item = (E) get(index);
        index--;
        return item;
      }
      return null;
    }

    @Override
    public int nextIndex() {
      return index;
    }

    @Override
    public int previousIndex() {
      return index - 1;
    }

    @Override
    public void remove() {
      MyLinkedList.this.remove(index);
      index--;
    }

    @Override
    public void set(E o) {
      MyLinkedList.this.set(index, o);
    }

    @Override
    public void add(E o) {
      MyLinkedList.this.add(index, o);
      index++;
    }
  }
}
