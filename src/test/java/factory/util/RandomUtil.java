package factory.util;

import java.util.UUID;

public class RandomUtil {

    public static long randomLong() {
        long leftLimit = 1L;
        long rightLimit = Long.MAX_VALUE;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static UUID randomUUID() {
        return UUID.randomUUID();
    }

}
