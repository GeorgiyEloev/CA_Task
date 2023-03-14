package tasksForCA.task_1_n.task3;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task3 {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(100);


    for (int i = 0; i < 100; i++) {
      executorService.execute(new CarThread(i));
    }
  }
}
