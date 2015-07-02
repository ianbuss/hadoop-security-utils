package tools;

import org.apache.hadoop.security.ShellBasedUnixGroupsMapping;

import java.util.List;

import static tools.CheckerUtils.*;

public class ShellUnixGroupChecker {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.printf("%s user [user [...]]\n");
      System.exit(-1);
    }

    ShellBasedUnixGroupsMapping mapping = new ShellBasedUnixGroupsMapping();

    for (String user : args) {
      System.out.println("Looking up " + user);
      try {
        List<String> groups = mapping.getGroups(user);
        System.out.println(user + "(" + groups.size() + ") => " + formatGroups(groups));
      } catch (Exception e) {
        System.err.printf("Exception: %s\n", e);
      }
    }
  }

}
