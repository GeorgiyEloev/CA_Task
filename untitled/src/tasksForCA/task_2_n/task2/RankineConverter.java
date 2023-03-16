package tasksForCA.task_2_n.task2;

public class RankineConverter implements Converter {
  @Override
  public double getConvertedValue(double celsius) {
    return (celsius + 273.15) * 9 / 5;
  }
}
