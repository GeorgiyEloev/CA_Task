package tasksForCA.task_1_n.task3;

import java.util.concurrent.Semaphore;

public class CarThread implements Runnable {

  private int number;

  private static final Semaphore SEMAPHORE_1 = new Semaphore(10, true);
  private static final Semaphore SEMAPHORE_2 = new Semaphore(2, true);
  private static final Semaphore SEMAPHORE_3 = new Semaphore(10, true);

  public CarThread(int number) {
    this.number = number;
  }

  public void startRace() {
    try {
      step1();
      Thread.sleep((int) (Math.random() * 100));

      step2();
      Thread.sleep((int) (Math.random() * 100));

      step3();
      Thread.sleep((int) (Math.random() * 100));

      System.out.printf("Автомобиль №%d финиш.\n", number);

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void step1() {
    try {
      SEMAPHORE_1.acquire();

      System.out.printf("Автомобиль №%d едет по первой части дороги.\n", number);

      Thread.sleep(2000);

      SEMAPHORE_1.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void step2() {
    try {
      SEMAPHORE_2.acquire();

      System.out.printf("Автомобиль №%d едет в тунеле.\n", number);

      Thread.sleep(100);

      SEMAPHORE_2.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void step3() {
    try {
      SEMAPHORE_3.acquire();

      System.out.printf("Автомобиль №%d едет по второй части дороги.\n", number);

      Thread.sleep(2000);

      SEMAPHORE_3.release();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void run() {
    startRace();
  }
}
