package tasksForCA.task_2_n.task2;

public class NewtonConverter implements Converter {
  @Override
  public double getConvertedValue(double celsius) {
    return celsius * 33 / 100;
  }
}