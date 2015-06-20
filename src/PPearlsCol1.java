import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import java.util.Random;

/**
 * Created by King on 4/16/15.
 */
public class PPearlsCol1 {
    public void genereateNums(int k, int n, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BitSet bs = new BitSet(n);
        Random random = new Random();
        int count = 0;
//        int hitExist = 0;
        while (count < k) {
            int rand = random.nextInt(n);
            if (!bs.get(rand)) {
//                System.out.println(hitExist);
//                hitExist = 0;
                bs.set(rand);
                bw.write(String.valueOf(rand));
                bw.newLine();
                count++;
            }
        }
        bw.close();
        fw.close();
//        System.out.println(hitExist);
    }

    public static void main(String[] args) {
        PPearlsCol1 sol = new PPearlsCol1();
        try {
            long startTime = System.nanoTime();
//            sol.genereateNums(500, 2000, "temp.txt");
            sol.genereateNums(5000000, 5000000, "temp.txt");
            long endTime = System.nanoTime();
            System.out.println("Running time = " + (endTime - startTime) / 1000000 + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
