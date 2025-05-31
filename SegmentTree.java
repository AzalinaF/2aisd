package SegmentTreeJava;

import java.util.*;

import java.util.*;

public class SegmentTree {
    private int[] tree;
    private int n;

    private long insertTime = 0;
    private long searchTime = 0;
    private long deleteTime = 0;
    private int insertOperations = 0;
    private int searchOperations = 0;
    private int deleteOperations = 0;

    public SegmentTree(int[] nums) {
        n = nums.length;
        tree = new int[2 * n];
        buildTree(nums);
    }

    private void buildTree(int[] nums) {
        for (int i = 0; i < n; i++) {
            tree[n + i] = nums[i];
        }
        for (int i = n - 1; i > 0; --i) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int pos, int val) {
        long start = System.nanoTime();
        int ops = 0;

        pos += n;
        tree[pos] = val;
        ops++;

        for (pos /= 2; pos > 0; pos /= 2) {
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1];
            ops++;
        }

        long end = System.nanoTime();
        insertTime += (end - start);
        insertOperations += ops;
    }

    public void insert(int pos, int val) {
        update(pos, val); // просто вызов update
    }

    public int search(int index) {
        return query(index, index);
    }

    public int query(int l, int r) {
        long start = System.nanoTime();
        int ops = 0;
        int sum = 0;

        l += n;
        r += n;
        while (l <= r) {
            if (l % 2 == 1) {
                sum += tree[l++];
                ops++;
            }
            if (r % 2 == 0) {
                sum += tree[r--];
                ops++;
            }
            l /= 2;
            r /= 2;
            ops++;
        }

        long end = System.nanoTime();
        searchTime += (end - start);
        searchOperations += ops;
        return sum;
    }

    public void delete(int pos) {
        long start = System.nanoTime();
        int ops = 0;

        pos += n;
        tree[pos] = 0;
        ops++;

        for (pos /= 2; pos > 0; pos /= 2) {
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1];
            ops++;
        }

        long end = System.nanoTime();
        deleteTime += (end - start);
        deleteOperations += ops;
    }

    public void printStatistics() {
        double avgInsertTime = insertTime / 10000.0;
        double avgInsertOps = insertOperations / 10000.0;

        double avgSearchTime = searchTime / 100.0;
        double avgSearchOps = searchOperations / 100.0;

        double avgDeleteTime = deleteTime / 1000.0;
        double avgDeleteOps = deleteOperations / 1000.0;

        System.out.printf("Average insert time: %.2f ns, operations: %.2f\n", avgInsertTime, avgInsertOps);
        System.out.printf("Average search time: %.2f ns, operations: %.2f\n", avgSearchTime, avgSearchOps);
        System.out.printf("Average delete time: %.2f ns, operations: %.2f\n", avgDeleteTime, avgDeleteOps);
    }
}
