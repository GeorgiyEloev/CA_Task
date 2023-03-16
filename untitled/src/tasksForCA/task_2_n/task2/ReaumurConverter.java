package tasksForCA.task_2_n.task2;

public class ReaumurConverter implements Converter {
  @Override
  public double getConvertedValue(double celsius) {
    return celsius * 4 / 5;
  }
}
