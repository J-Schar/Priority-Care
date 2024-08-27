import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the PatientRecord at the root should be the smallest PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the PatientRecord.compareTo() method to compare
 * PatientRecords. 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
  private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority
                                 // queue
  private int size; // size of this priority queue


  /**
   * Creates a new empty PriorityCareAdmissions queue with the given capacity
   * 
   * @param capacity Capacity of this PriorityCareAdmissions queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public PriorityCareAdmissions(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    this.queue = new PatientRecord[capacity];
  }

  /**
   * Checks whether this PriorityCareAdmissions queue is empty
   * 
   * @return {@code true} if this PriorityCareAdmissions queue is empty
   */
  public boolean isEmpty() {
    if (size() == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the size of this PriorityCareAdmissions queue
   * 
   * @return the total number of PatientRecords stored in this PriorityCareAdmissions queue
   */
  public int size() {
    return size;
  }

  /**
   * Returns the capacity of this PriorityCareAdmissions queue
   * 
   * @return the capacity of this PriorityCareAdmissions queue
   */
  public int capacity() {
    return queue.length;
  }


  /**
   * Removes all the elements from this PriorityCareAdmissions queue
   */
  public void clear() {
    for (int i = 0; i < queue.length; i++) {
      queue[i] = null;
    }
    size = 0;
  }


  /**
   * Returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e. the
   * PatientRecord having the the highest priority.
   * 
   * @return the PatientRecord at the root of this PriorityCareAdmissions queue
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Warning: Empty Admissions Queue!");
    }
    return queue[0];
  }


  /**
   * Adds the given PatientRecord to this PriorityCareAdmissions queue at the correct position based
   * on the min-heap ordering. This queue should maintain the min-heap invariant, so that the
   * PatientRecord at each index is less than or equal to than the PatientRecords in its child
   * nodes. PatientRecords should be compared using the PatientRecord.compareTo() method.
   * 
   * @param p PatientRecord to add to this PriorityCareAdmissions queue
   * @throws NullPointerException  if the given PatientRecord is null
   * @throws IllegalStateException with a the exact error message "Warning: Full Admissions Queue!"
   *                               if this PriorityCareAdmissions queue is full
   */
  public void addPatient(PatientRecord p) {
    if (p == null) {
      throw new NullPointerException();
    }

    if (size() == capacity()) {
      throw new IllegalStateException("Warning: Full Admissions Queue!");
    }
    
    size++;
    queue[size - 1] = p;
    percolateUp(size - 1);
    
  }
  
  /**
   * private helper method that swaps two elements of the PatientRecord queue
   * @params PatientRecord[] queue, int i, int j
   */
  private void swap(PatientRecord[] queue, int i, int j) {
    if (queue[i] == null || queue[j] == null) {
      return;
    }
    PatientRecord temp = queue[j];
    queue[j] = queue[i];
    queue[i] = temp;
  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
   * priority queue by percolating a leaf up the heap. If the element at the given index does not
   * violate the min-heap invariant (it is greater than its parent), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, swap the element with its parent and
   * continue percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateUp(int i) {
    if (i < 0 || i >= size()) {
      throw new IndexOutOfBoundsException();
    }

    if (i == 0) {
      return;
    }


    if (queue[i].compareTo(queue[(i-1)/2]) >= 0) {
      return;
    }

    swap(queue, i, (i-1)/2);
    percolateUp((i-1)/2);
  }

  /**
   * Removes and returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e.
   * the PatientRecord having the highest priority (the minimum one).
   * 
   * @return the PatientRecord in this PriorityCareAdmissions queue at the root of this priority
   *         queue.
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord removeBestRecord() {
    if (size == 0) {
      throw new NoSuchElementException("Warning: Empty Admissions Queue!");
    }

    PatientRecord temp = queue[0];
    queue[0] = queue[size - 1];
    queue[size - 1] = null;
    percolateDown(0);
    size--;

    return temp;
  }

  // /**
  //  * helper method that returns minimal child of specified PatientRecord in queue
  //  * @params PatientRecord[] queue and int i
  //  * @return minimal child of this PatientRecord if given PatientRecord, and null otherwise
  //  */
  // private PatientRecord minChild(int i, PatientRecord[] queue) {
  //   if (queue[2*i + 1] == null && queue[2*i + 2] == null) {
  //     return null;
  //   }

  //   if (queue[2*i + 1] != null && queue[2*i + 2] == null) {
  //     return queue[2*i + 1];
  //   }

  //   if (queue[2*i + 1] == null && queue[2*i + 2] != null) {
  //     return queue[2*i + 2];
  //   }

  //   PatientRecord leftChild = queue[2*i + 1];
  //   PatientRecord rightChild = queue[2*i + 2];

  //   if (leftChild.compareTo(rightChild) < 0) {
  //     return leftChild;
  //   }

  //   return rightChild;
  // }


  /**
   * helper method that returns the index minimal child of specified PatientRecord in queue
   * @params PatientRecord[] queue and int i
   * @return minimal child of this PatientRecord if given PatientRecord, and null otherwise
   */
  private int minChildIndex(int i, PatientRecord[] queue) {
    if (queue[2*i + 1] != null && queue[2*i + 2] == null) {
      return 2*i + 1;
    }

    if (queue[2*i + 1] == null && queue[2*i + 2] != null) {
      return 2*i + 2;
    }

    if (queue[2*i + 1] == null && queue[2*i + 2] == null) {
      return 0;
    }

    PatientRecord leftChild = queue[2*i + 1];
    PatientRecord rightChild = queue[2*i + 2];

    if (leftChild.compareTo(rightChild) < 0) {
      return 2*i + 1;
    }

    return 2*i + 2;
  }

  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap of the priority queue
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap ordering property (it is smaller than its smallest child), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateDown(int i) {
    if (i < 0 || i >= size()) {
      throw new IndexOutOfBoundsException();
    }

    if (minChildIndex(i, queue) == 0) {
      return;
    }

    swap(queue, i, minChildIndex(i, queue));

    percolateDown(minChildIndex(i, queue));
  }


  /**
   * Returns a deep copy of this PriorityCareAdmissions queue containing all of its elements in the
   * same order. This method does not return the deepest copy, meaning that you do not need to
   * duplicate PatientRecords. Only the instance of the heap (including the array and its size) will
   * be duplicated.
   * 
   * @return a deep copy of this PriorityCareAdmissions queue. The returned new priority care
   *         admissions queue has the same length and size as this queue.
   */
  public PriorityCareAdmissions deepCopy() {
    PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
    deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
    deepCopy.size = this.size;
    return deepCopy;
  }

  /**
   * Returns a deep copy of the array-heap of this PriorityCareAdmissions queue <BR/>
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the ParientRecords in this queue
   */
  protected PatientRecord[] arrayHeapCopy() {
    return Arrays.copyOf(this.queue, this.queue.length);
 
  }
  /**
   * Returns a String representing this PriorityCareAdmissions queue, where each element
   * (PatientRecord) of the queue is listed on a separate line, in order from smallest to greatest.
   * 
   * @return a String representing this PriorityCareAdmissions queue, and an empty String "" if this
   *         queue is empty.
   */
  public String toString() {
    PriorityCareAdmissions c = deepCopy();
    PatientRecord[] d = c.arrayHeapCopy();
    String temp = "";

    int s = c.size();
    while (s > 0) {
      temp += d[0].toString() + "\n";
      c.removeBestRecord();
      d = c.arrayHeapCopy();
      s--;
    }

    return temp;
  }

}
