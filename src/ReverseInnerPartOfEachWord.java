import java.util.Random;

/**
 *
 */
public class ReverseInnerPartOfEachWord {
    public String reverseInner(String str) {
        char[] charr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        // Shuffle each word's inner chars
        int start = 0, end = 0;
        Random rand = new Random();
        while (start < charr.length) {
            while (Character.isSpaceChar(charr[start])) {
                sb.append(charr[start++]);
            }
            end = start;
            while (end + 1 < charr.length && !Character.isSpaceChar(charr[end + 1])) end++;
            // Shuffle (start + 1 ~ end - 1)
            sb.append(charr[start]);
            int index = start + 1;
            System.out.println("index = " + index);
            System.out.println("end = " + end);
            while (index < end) {
                int shuf = rand.nextInt(end - 1 - index + 1) + index;
                char tmp = charr[shuf];
                charr[shuf] = charr[index];
                charr[index] = tmp;
                sb.append(charr[index++]);
            }
            sb.append(charr[end]);
            start = end + 1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "you are beautiful";
        ReverseInnerPartOfEachWord sol = new ReverseInnerPartOfEachWord();
        System.out.println(sol.reverseInner(str));
    }
}
