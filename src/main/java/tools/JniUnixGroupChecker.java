package tools;

import org.apache.hadoop.security.JniBasedUnixGroupsMapping;

import java.util.List;

import static tools.CheckerUtils.*;

public class JniUnixGroupChecker {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.printf("%s user [user [...]]\n", JniUnixGroupChecker.class.getName());
      System.exit(-1);
    }

    JniBasedUnixGroupsMapping mapping = new JniBasedUnixGroupsMapping();

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
