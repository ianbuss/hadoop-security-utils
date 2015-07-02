package tools;

import java.util.List;

public class CheckerUtils {

  public static String formatGroups(List<String> groups) {
    String groupString = "";
    for (String group : groups) {
      groupString += group + ",";
    }
    return groupString.replaceFirst(",$", "");
  }

}
