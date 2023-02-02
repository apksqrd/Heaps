package main;

public class Main {
    public static void main(String[] args) {
        Integer[] unsortedArray = new Integer[] { 5, 2, 4, 8, 7, 4, 0, 2, 9, -1, 2, 4, 8, 8, 1234, 4231, 2, 3, 4, 5, 6,
                2314, 6, 87, 4, -19, 834, -43, 25, -5432 };

        System.out.println(Heap.heapSort(unsortedArray));
    }
}