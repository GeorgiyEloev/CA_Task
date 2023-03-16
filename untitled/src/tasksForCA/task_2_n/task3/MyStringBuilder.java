package tasksForCA.task_2_n.task3;

import java.util.ArrayList;
import java.util.List;

public class MyStringBuilder  {
  private final StringBuilder stringBuilder;
  private final List<String> history;

  public MyStringBuilder() {
    stringBuilder = new StringBuilder();
    history = new ArrayList<>();
  }

  public MyStringBuilder(CharSequence seq) {
    stringBuilder = new StringBuilder(seq);
    history = new ArrayList<>();
  }

  public MyStringBuilder(int capacity) {
    stringBuilder = new StringBuilder(capacity);
    history = new ArrayList<>();
  }

  public MyStringBuilder(StringBuilder stringBuilder, List<String> history) {
    this.stringBuilder = stringBuilder;
    this.history = history;
  }

  public MyStringBuilder append(CharSequence csq) {
    history.add(stringBuilder.toString());
    stringBuilder.append(csq);
    return this;
  }

  public MyStringBuilder append(CharSequence csq, int start, int end) {
    history.add(stringBuilder.toString());
    stringBuilder.append(csq, start, end);
    return this;
  }

  public MyStringBuilder append(char c) {
    history.add(stringBuilder.toString());
    stringBuilder.append(c);
    return this;
  }

  public MyStringBuilder delete(int start, int end) {
    history.add(stringBuilder.toString());
    stringBuilder.delete(start, end);
    return this;
  }

  public MyStringBuilder deleteCharAt(int index) {
    history.add(stringBuilder.toString());
    stringBuilder.deleteCharAt(index);
    return this;
  }

  public MyStringBuilder undo() {
    if (!history.isEmpty()) {
      stringBuilder.setLength(0);
      stringBuilder.append(history.remove(history.size() - 1));
    }
    return this;
  }

  public int length() {
    return stringBuilder.length();
  }

  public char charAt(int index) {
    return stringBuilder.charAt(index);
  }

  public CharSequence subSequence(int start, int end) {
    return stringBuilder.subSequence(start, end);
  }

  @Override
  public String toString() {
    return stringBuilder.toString();
  }
}
