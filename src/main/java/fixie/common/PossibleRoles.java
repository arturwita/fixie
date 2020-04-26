package fixie.common;

import java.util.ArrayList;
import java.util.List;

public class PossibleRoles {
    public static String MANAGER_MNEMO = "MAN";
    public static String WORKER_MNEMO = "WORK";
    public static String ADMIN_MNEMO = "ADM";
    public static String CLIENT_MNEMO = "CLI";

    public static List<String> getPossibleRoles(){
        ArrayList<String> roles = new ArrayList<>();
        roles.add(MANAGER_MNEMO);
        roles.add(WORKER_MNEMO);
        roles.add(ADMIN_MNEMO);
        roles.add(CLIENT_MNEMO);

        return roles;
    }
}
