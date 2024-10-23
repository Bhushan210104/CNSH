import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

public class HMAC {

    public static String calculateSHA256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String calculateHMAC(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmac = mac.doFinal(data.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hmac) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the data string: ");
        String data = scanner.nextLine();

        System.out.print("Enter the secret key: ");
        String secret = scanner.nextLine();

        String sha256Hash = calculateSHA256(data);
        System.out.println("SHA-256 Hash: " + sha256Hash);

        String hmac = calculateHMAC(data, secret);
        System.out.println("HMAC: " + hmac);

        scanner.close(); // Close the scanner
    }
}
