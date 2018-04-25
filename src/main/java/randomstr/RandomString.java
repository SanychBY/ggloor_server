package randomstr;

import java.util.Random;

/**
 * Created by ssaan on 07.04.2017.
 */
public class RandomString {
    public static String getRandomString(Integer size)
    {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890-_".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
