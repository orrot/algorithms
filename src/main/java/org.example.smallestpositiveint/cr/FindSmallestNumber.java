package org.example.smallestpositiveint.cr;

import java.util.Arrays;

public class FindSmallestNumber {

    public static void main(String[] args) {
        int arr[] = {0, 1, 10};

        System.out.println("Given Array");
        System.out.println(Arrays.toString(arr));

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        //System.out.println(findMissingNo(arr, arr.length));
        System.out.println(smallestMissing(arr, 0, arr.length - 1));

    }

    // for repeated O(n)
    static int findMissingNo(int[] arr, int n) {
        int val;
        int nextval;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0 || arr[i] > n)
                continue;
            val = arr[i];
            while (arr[val - 1] != val) {
                nextval = arr[val - 1];
                arr[val - 1] = val;
                val = nextval;
                if (val <= 0 || val > n)
                    break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    // binary search for non repeated values O(logn)
    static int smallestMissing(int[] A, int left, int right) {
        if (left > right) {
            return left;
        }
        int mid = left + (right - left) / 2;
        if (A[mid] == mid) {
            return smallestMissing(A, mid + 1, right);
        } else {
            return smallestMissing(A, left, mid - 1);
        }
    }


}
