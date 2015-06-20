import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 题目：给定很多time interval，找到所有有conflict的pair。
 * 数据结构，输入输出都是自己设计。
 */

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "(" + start + ", " + end + ")";
    }
}

class Pair<T> {
    T interval;
    List<T> conflicts;

    Pair(T interval, List<T> conflicts) {
        this.interval = interval;
        this.conflicts = conflicts;
    }

    @Override
    public String toString() {
        return "[ first = " + interval.toString() + ", second = " + conflicts.toString() + ']';
    }
}

class IntervalComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        if (o1.start < o2.start) return -1;
        else if (o1.start > o2.start) return 1;
        else {
            if (o1.end < o2.end) return -1;
            else if (o1.end > o2.end) return 1;
            else return 0;
        }
    }
}

public class AllConflictTimeIntervals {
    public List<Pair<Interval>> findAllConflictIntervals(List<Interval> allIntervals) {
        List<Pair<Interval>> res = new ArrayList<>();
        Collections.sort(allIntervals, new IntervalComparator()); //O(nlgn)
        for (int i = 0; i < allIntervals.size() - 1; i++) {
            for (int j = allIntervals.size() - 1; j > i; j--) {
                if (allIntervals.get(j).getStart() < allIntervals.get(i).getEnd()) {
                    res.add(new Pair<>(allIntervals.get(i), allIntervals.subList(i + 1, j + 1)));
                    break;
                }
            }
        }
        System.out.println(allIntervals);
        return res;
    }

    public static void main(String[] args) {
        List<Interval> allIntervals = new ArrayList<>();
        allIntervals.add(new Interval(1, 3));
        allIntervals.add(new Interval(2, 3));
        allIntervals.add(new Interval(3, 4));
        allIntervals.add(new Interval(4, 5));
        allIntervals.add(new Interval(1, 10));
        allIntervals.add(new Interval(3, 6));
        allIntervals.add(new Interval(9, 11));
        AllConflictTimeIntervals sol = new AllConflictTimeIntervals();
        System.out.println(sol.findAllConflictIntervals(allIntervals));
    }
}
