package com.anmol.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 */
public class ThreeSum {
    public static void main(String[] args) {
        threeSum(new int[]{3, 0, -2, -1, 1, 2});
    }

    /**
     * Approach:
     * <p>
     * If we sort the array, that way we know the triplet will lie in ascending order.
     * <p>
     * So we can take 3 pointers-
     * <p>
     * First pointer can loop from 0th till thirdLast element of the array.
     * <p>
     * Second pointer can go from firstIndex to secondLast element of the array.
     * <p>
     * Now what about third? Let that go from other side, i.e. start from lastElement.
     * <p>
     * Now sum(first + second + third). We have 3 cases:
     * <p>
     * 1. < 0 (So increase index of second element- as next element will have higher value, as Array is sorted).
     * <p>
     * 2. > 0 (So decrease index of last element- as previous element will have lower value, as Array is sorted).
     * <p>
     * 3. = 0 (We got the triplet, save it. Increase MedIndex and dec lastIndex, to include other triplets with same first Element).
     *
     * @param nums Array to find the triplets from.
     * @return List  of all tripets with sum = 0.
     */
    public static List<List<Integer>> threeSum(final int[] nums) {
        Arrays.sort(nums);
        final List<List<Integer>> tripletsList = new ArrayList<>();
        // Need 3 pointers.
        // First from normal loop.
        // Remaining 2 will be between (FirstIndex+1 -> lastIndexOfArray)
        for (int x = 0; x < nums.length - 2; x++) {
            final int firstNum = nums[x];
            int indexOfMiddleElement = x + 1;
            int indexOfLastElement = nums.length - 1;
            while (indexOfMiddleElement < indexOfLastElement) {
                final int sum = nums[indexOfMiddleElement] + nums[indexOfLastElement] + firstNum;
                if (sum > 0) {
                    indexOfLastElement--;
                } else if (sum < 0) {
                    indexOfMiddleElement++;
                } else {
                    // Found a triplet.
                    final List<Integer> triplet = new ArrayList<>(3);
                    triplet.add(firstNum);
                    triplet.add(nums[indexOfMiddleElement]);
                    triplet.add(nums[indexOfLastElement]);
                    // Add only if it was not added before, to avoid adding same triplet twice
                    if (!tripletsList.contains(triplet)) {
                        tripletsList.add(triplet);
                    }
                    indexOfMiddleElement++;
                    indexOfLastElement--;
                }
            }
        }
        return tripletsList;
    }
}
