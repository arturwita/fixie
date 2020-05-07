package fixie.common;

import java.util.ArrayList;
import java.util.List;

public class Roles {
    public static String MANAGER = "MAN";
    public static String WORKER = "WORK";
    public static String ADMIN = "ADM";
    public static String CLIENT = "CLI";
    public static String UNREGISTERED_CLIENT = "UCLI";

    public static List<String> getPossibleRoles() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add(MANAGER);
        roles.add(WORKER);
        roles.add(ADMIN);
        roles.add(CLIENT);
        roles.add(UNREGISTERED_CLIENT);

        return roles;
    }
}
