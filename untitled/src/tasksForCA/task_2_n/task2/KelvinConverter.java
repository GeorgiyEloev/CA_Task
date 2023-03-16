package tasksForCA.task_2_n.task2;

public class KelvinConverter implements Converter {
  @Override
  public double getConvertedValue(double celsius) {
    return celsius + 273.15;
  }
}