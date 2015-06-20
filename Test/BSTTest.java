import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * JUnit test for BST
 */
public class BSTTest {
    @Test(timeout = 1000)
    public void testValidBST() throws Exception {
        List<Integer> list = new ArrayList<>();
        int[] inputArr = {3, 2, 1, 5, 4, 6};
        for (int i : inputArr) list.add(i);
        BST bst = new BST();
        assertTrue(bst.validBST(list));
    }
}