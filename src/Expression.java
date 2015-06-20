/*
[+, 1, 2, 3, [*, 2, 3], 6]
[+ 1 2 3 [* 2.123 123.123] 123213]
eval = 1 + 2 + 3 + (2 * 3) + 6 = 18
 */

import java.util.ArrayList;
import java.util.List;

public class Expression {
    char op;
    List<Object> numOrExp;
    List<Integer> evaled;

    Expression() {
        numOrExp = new ArrayList<>();
        evaled = new ArrayList<>();
    }

    public int eval() {
        int res = 0;
        for (Object obj : numOrExp) {
            if (obj instanceof Integer)
                evaled.add((Integer) obj);
            else evaled.add(((Expression) obj).eval());
        }
        switch (op) {
            case '+':
                res = 0;
                for (Integer num : evaled) res += num;
                break;
            case '-':
                if (evaled.size() > 0) {
                    res = evaled.get(0);
                    for (int i = 1; i < evaled.size(); i++) res -= evaled.get(i);
                }
                break;
            case '*':
                res = 1;
                for (Integer num : evaled) res *= num;
                break;
            case '/':
                if (evaled.size() > 0) {
                    res = evaled.get(0);
                    for (int i = 1; i < evaled.size(); i++) res /= evaled.get(i);
                }
                break;
            default:
                break;
        }
        System.out.println("res = " + res);
        return res;
    }

    public void parse(String expression) {
        String[] exp = expression.split(",");
        for (String s : exp) System.out.print(s + " ");
        System.out.println(exp[0]);
        System.out.println(exp[0].charAt(0));
        System.out.println(exp[0].charAt(1));
        System.out.println();
        parseHelper(exp, 0, exp.length - 1);
    }

    private void parseHelper(String[] exp, int start, int end) {
        if (start >= end - 1) return;
        op = exp[start].trim().charAt(1);
        int i = start + 1;
        while (i < end) {
            String cur = exp[i].trim();
            if (cur.startsWith("[")) {
                int j = i + 1;
                int leftCount = 0;
                while (!exp[j].contains("]") || leftCount != 0) {
                    if (exp[j].contains("[")) leftCount++;
                    if (exp[j].contains("]") && leftCount > 0) leftCount--;
                    j++;
                }
                Expression subExp = new Expression();
                subExp.parseHelper(exp, i, j);
                numOrExp.add(subExp);
                i = j + 1;
            } else {
                System.out.println(i);
                numOrExp.add(Integer.parseInt(cur));
                i++;
            }
        }
        String last = exp[end].trim();
        if (last.length() > 1)
            numOrExp.add(Integer.parseInt(last.substring(0, last.length() - 1)));
        return;
    }

    public static void main(String[] args) {
        String expression = "[+, 1, 2, 3, [*, 2, 3, [+, 3, [/, 9, 3], 3], ], 6]";
        Expression exp = new Expression();
        exp.parse(expression);
        System.out.println(exp);
        System.out.println(exp.eval());
    }
}
