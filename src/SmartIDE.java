/**
 * https://www.hackerrank.com/contests/regex-practice-1/challenges/ide-identifying-comments
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SmartIDE {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains("//")) {
                builder.append(line.substring(line.indexOf("//")) + "\n");
            } else if (line.contains("/*")) {
                if (line.contains("*/"))
                    builder.append(line.substring(line.indexOf("/*"), line.indexOf("*/") + 2) + "\n");
                else {
                    builder.append(line.substring(line.indexOf("/*")) + "\n");
                    while (sc.hasNextLine()) {
                        String mcLine = sc.nextLine();
                        if (!mcLine.contains("*/")) builder.append(mcLine + "\n");
                        else {
                            builder.append(mcLine.substring(0, mcLine.indexOf("*/") + 2) + "\n");
                            break;
                        }
                    }
                }
            }
        }
        System.out.print(builder.toString());
    }

    public static void main2(String[] args) {
        //Get code
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        for (String line; sc.hasNextLine() && (line = sc.nextLine()) != null; sb.append(line + "\n")) {
        }
        String code = sb.toString();

        //Run the regex
        Matcher m = Pattern.compile(
                "//.*?(?=\\n)|/\\*.*?\\*/",
                Pattern.DOTALL | Pattern.MULTILINE
        ).matcher(code);

        //Print out matches
        sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group() + "\n");
        }
        System.out.print(sb);
    }

}
