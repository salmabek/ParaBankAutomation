package AddUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class NameGenerator {



    // Generate a unique first name using UUID
    public static String uniqueNameUUID(String baseName) {
        String suffix = UUID.randomUUID().toString().substring(0, 10);
        return baseName + suffix;
    }

    // Generate unique email using UUID
    public static String uniqueEmailUUID(String baseName, String domain) {
        String suffix = UUID.randomUUID().toString().substring(0, 10);
        return baseName + suffix + "@" + domain;
    }
}

