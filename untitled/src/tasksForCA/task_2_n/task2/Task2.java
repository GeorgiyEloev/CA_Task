package tasksForCA.task_2_n.task2;



public class Task2 {
  public static void main(String[] args) {
    double celsius = 300.0;

    System.out.println("Цельсия в Кельвины:" + new KelvinConverter().getConvertedValue(celsius));
    System.out.println("Цельсия в Фаренгейты:" + new FahrenheitConverter().getConvertedValue(celsius));
    System.out.println("Цельсия в Ранкины:" + new RankineConverter().getConvertedValue(celsius));
    System.out.println("Цельсия в Делилы:" + new DelisleConverter().getConvertedValue(celsius));
    System.out.println("Цельсия в Ньютоны:" + new NewtonConverter().getConvertedValue(celsius));
    System.out.println("Цельсия в Реомюры:" + new ReaumurConverter().getConvertedValue(celsius));


  }
}
