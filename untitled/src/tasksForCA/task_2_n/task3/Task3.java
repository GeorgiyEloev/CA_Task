package tasksForCA.task_2_n.task3;


public class Task3 {

  public static void main(String[] args) {

    MyStringBuilder builder = new MyStringBuilder();
    builder.append("Hello");
    builder.append(", world!");
    System.out.println(builder);
    builder.undo();
    System.out.println(builder);

  }
}
