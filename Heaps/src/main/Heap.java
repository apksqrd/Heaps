package main;

import java.util.ArrayList;

/**
 * 2
 * 5 3
 * 6 8 7 4
 */
public class Heap {
    private ArrayList<Integer> intArray;

    public Heap(Integer[] unsorted) {
        intArray = new ArrayList<>();
        for (Integer integer : unsorted) {
            insert(integer);
        }
    }

    public void insert(int addedValue) {
        intArray.add(addedValue);
        swimUp(intArray.size() - 1);
    }

    /**
     * Looks at the value at index and it's parent and if they should be switched,
     * switches them and then it calls swim up for the new index of value
     * (previousily parent's index).
     * 
     * @param index
     */
    private void swimUp(int index) {
        if (index == 0) {
            // at the very top already
            return;
        }

        int parentIndex = (index - 1) / 2;
        if (swap(parentIndex, index)) {
            swimUp(parentIndex);
        }
    }

    /**
     * @param upperIndex parent
     * @param lowerIndex child
     * @return true if the swap was needed in the first place
     */
    private boolean swap(int upperIndex, int lowerIndex) {
        if (intArray.get(upperIndex) <= intArray.get(lowerIndex)) {
            return false; // already in place
        }

        uncheckedSwap(upperIndex, lowerIndex);

        return true;
    }

    private void uncheckedSwap(int i, int j) {
        int temp = intArray.get(i);
        intArray.set(i, intArray.get(j));
        intArray.set(j, temp);
    }

    public int pop() {
        uncheckedSwap(0, intArray.size() - 1);
        int i = intArray.remove(intArray.size() - 1);
        sinkDown(0);
        return i;
    }

    private void sinkDown(int parentIndex) {
        // already at the bottom or only has one child
        if (2 * parentIndex + 2 >= intArray.size()) {
            // means there is only 0 or 1 child
            if (2 * parentIndex + 1 >= intArray.size()) {
                // no child, done sinking
                return;
            }

            // attempt swap between parent and left child
            swap(parentIndex, 2 * parentIndex + 1);

            return;
        }

        int smallerChildIndex; // can be left or right if values equal
        if (intArray.get(2 * parentIndex + 1) > intArray.get(2 * parentIndex + 2)) {
            smallerChildIndex = 2 * parentIndex + 2;
        } else {
            smallerChildIndex = 2 * parentIndex + 1;
        }

        if (swap(parentIndex, smallerChildIndex)) {
            sinkDown(smallerChildIndex);
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < intArray.size(); i++) {
            if (isPowOf2(i + 1)) {
                s += '\n';
            }
            s += intArray.get(i) + " ";
        }
        return s;
    }

    public boolean isPowOf2(int i) {
        if (i == 0) {
            return false;
        }

        while (i % 2 == 0) {
            i = i / 2;
        }
        // System.out.println(i);
        return (i == 1);
    }

    public boolean isEmpty() {
        return intArray.isEmpty();
    }

    public static ArrayList<Integer> heapSort(Integer[] unsorted) {
        ArrayList<Integer> sorted = new ArrayList<>();
        Heap heap = new Heap(unsorted);
        while (!heap.isEmpty()) {
            sorted.add(heap.pop());
        }
        return sorted;
    }
}