import java.util.PriorityQueue;

class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // Stores the left half of the data (lower values)
    private PriorityQueue<Integer> minHeap; // Stores the right half of the data (higher values)

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max heap (reverse order)
        minHeap = new PriorityQueue<>(); // Min heap (default order)
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // Balance the heaps if necessary
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("No elements in the data structure.");
        }

        if (maxHeap.size() == minHeap.size()) {
            // Even number of elements, take the average of the two middle values
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // Odd number of elements, the middle value in maxHeap is the median
            return maxHeap.peek();
        }
    }
}