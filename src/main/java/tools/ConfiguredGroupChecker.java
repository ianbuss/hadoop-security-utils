package tools;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeys;
import org.apache.hadoop.security.Groups;

import java.util.List;

import static tools.CheckerUtils.*;

public class ConfiguredGroupChecker {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.printf("%s user [user [...]]\n", JniUnixGroupChecker.class.getName());
      System.exit(-1);
    }

    Configuration configuration = new Configuration();
    String impl = configuration.get(CommonConfigurationKeys.HADOOP_SECURITY_GROUP_MAPPING);
    Groups mapping = new Groups(configuration);

    System.out.println("Using " + impl);

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
