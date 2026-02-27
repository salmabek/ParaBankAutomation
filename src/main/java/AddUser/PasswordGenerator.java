package AddUser;
import java.security.SecureRandom;

public class PasswordGenerator {

    /* Characters allowed in the password */
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_+=";
    private static final String ALL = UPPER + LOWER + DIGITS + SYMBOLS;

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        StringBuilder password = new StringBuilder(length);

        /* Ensure at least one character of each type */
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));

        /* Fill remaining characters randomly */
        for (int i = 4; i < length; i++) {
            password.append(ALL.charAt(random.nextInt(ALL.length())));
        }

        /* Shuffle to avoid predictable pattern */
        return shuffleString(password.toString());
       }


      private static String shuffleString(String input) {
        char[] a = input.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        return new String(a);
    }

    /* Example usage */
    public static void main(String[] args) {
        String password = generatePassword(12);
        System.out.println("Random password: " + password);
    }
}
