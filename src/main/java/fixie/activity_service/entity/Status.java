package fixie.activity_service.entity;

import java.util.ArrayList;
import java.util.List;

public class Status {
    public static final String OPEN = "OPN";
    public static final String PROGRESS = "PRG";
    public static final String FINISHED = "FIN";
    public static final String CANCELLED = "CAN";

    public static List<String> getPossibleStatuses() {
        ArrayList<String> statuses = new ArrayList<>();
        statuses.add(OPEN);
        statuses.add(PROGRESS);
        statuses.add(FINISHED);
        statuses.add(CANCELLED);

        return statuses;
    }
}
