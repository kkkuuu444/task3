import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class HMACCoder
{
    private static final String HMAC_ALGO = "HmacSHA3-256";
    private static String KEY;
    private static String HMAC;

    private static byte[] generateKey()
    {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    public static String bytesToHex(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for(byte b: bytes)
        {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void generateHmac(String source) throws NoSuchAlgorithmException, InvalidKeyException
    {
        byte[] key = generateKey();
        Mac signer = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(key, HMAC_ALGO);
        signer.init(keySpec);
        HMAC = bytesToHex(signer.doFinal(source.getBytes(StandardCharsets.UTF_8)));
        KEY = bytesToHex(key);
    }

    public static String getKEY() {
        return KEY;
    }

    public static String getHMAC() {
        return HMAC;
    }

}
