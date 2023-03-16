package tasksForCA.task_2_n.task2;

public class FahrenheitConverter implements Converter {
  @Override
  public double getConvertedValue(double celsius) {
    return (celsius * 9 / 5) + 32;
  }
}
