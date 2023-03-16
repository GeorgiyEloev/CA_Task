package tasksForCA.task_2_n.task1;


import java.util.ArrayList;

public class Task1 {
  public static void main(String[] args) {

    ArrayList<String> arrayList = new ArrayList<>();

    arrayList.add("Str7");
    arrayList.add("Str8");
    arrayList.add("Str9");

    MyLinkedList<String> myLinkedList = new MyLinkedList<>();

    myLinkedList.add("Str1");
    myLinkedList.add("Str2");
    myLinkedList.add("Str3");
    myLinkedList.add("Str4");
    myLinkedList.add("Str5");
    myLinkedList.add("Str6");
    myLinkedList.remove("Str5");
    myLinkedList.remove("Str4");

    Object[] arr = myLinkedList.toArray();

    boolean check = myLinkedList.contains("Str5");

    myLinkedList.addAll(arrayList);

    String str = myLinkedList.get(3);

    myLinkedList.set(3, "Str10");
    myLinkedList.add(4, "Str11");
    String delStr =myLinkedList.remove(6);


    myLinkedList.addAll(1, arrayList);
  }

}
