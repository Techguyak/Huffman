import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    public ArrayList<T> heap;
    public Heap() {
        heap = new ArrayList<>();
    }
    public void Swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    public int Parent(int i){
        return (i-1)/2;
    }
    public int left(int i){
        return 2*i+1;
    }
    public int right(int i){
        return 2*i+2;
    }

    public void insert(T value){
        heap.add(value);
        upside(heap.size()-1);
    }
    public void upside(int index){
        if(index == 0) return;
        int parent = Parent(index);
        if(heap.get(parent).compareTo(heap.get(index)) > 0) {
            Swap(parent, index);
            upside(parent);
        }
    }
    public T remove() {
        if(heap.isEmpty()) return null;
        T temp = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if(!heap.isEmpty()) {
            heap.set(0,last);
            downside(0);
        }
        return temp;
    }
    public void downside(int index) {
        int min = index;
        int left = left(index);
        int right = right(index);

        if (left<heap.size() && heap.get(left).compareTo(heap.get(min))< 0) {
            min = left;
        }
        if (right<heap.size() && heap.get(right).compareTo(heap.get(min)) < 0) {
            min = right;
        }
        if (min != index) {
            Swap(min, index);
            downside(min);
        }
    }
    public ArrayList<T> heapSort() throws Exception {
        if(heap.isEmpty()) {
            throw new Exception("Heap is empty");
        }
        ArrayList<T> sorted = new ArrayList<>();
        while (!heap.isEmpty()) {
            sorted.add(this.remove());
        }
        return sorted;
    }
    public static void main(String[] args) throws Exception {
        Heap<Integer> heap = new Heap<>();
        heap.insert(7);
        heap.insert(3);
        heap.insert(5);
        heap.insert(1);
        heap.insert(9);
        heap.insert(2);
        heap.insert(8);

        System.out.println(heap.heapSort().toString());
    }
}

// Heap is a data structure that maintains a binary tree with the property that each parent node is less than or equal to its children (for a min-heap) or greater than or equal to its children (for a max-heap).

//  This implementation is a min-heap, which means that the smallest element is always at the root of the tree. 

//time complexity of insert and remove is O(log n) and time complexity of heapSort is O(n log n)

// priority queue is a data structure that allows you to store elements with a priority and retrieve the element with the highest priority. A heap can be used to implement a priority queue, where the priority of an element is determined by its value (for a min-heap) or by its position in the heap (for a max-heap).