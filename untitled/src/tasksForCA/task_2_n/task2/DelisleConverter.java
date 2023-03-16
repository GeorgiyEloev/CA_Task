package tasksForCA.task_2_n.task2;

public class DelisleConverter implements Converter {
  @Override
  public double getConvertedValue(double celsius) {
    return (100 - celsius) * 3 / 2;
  }
}
