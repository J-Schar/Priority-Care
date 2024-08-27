import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 *
 */
public class PriorityCareTester {

  /**
   * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
   * higher triage level is compared to a lower triage level, regardless of patient order of
   * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
   * returns a negative integer when a lower triage level is compared to a higher triage level,
   * regardless of patient order of arival.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToDifferentTriage() {
    PatientRecord.resetCounter();
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.RED);
    PatientRecord p2 = new PatientRecord('M', 49, TriageLevel.GREEN);

    {
      int expected = -1;
      int actual = p1.compareTo(p2);
      if (expected != actual) {
        return false;
      }
    }

    {
      int expected = 1;
      int actual = p2.compareTo(p1);
      if (expected != actual) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests whether patients in the same triage level are compared based on their order of arrival.
   * Patients of the same triage level with a lower arrival number compared to patients with a
   * higher arrival number should return a negative integer. The reverse situation should return a
   * positive integer.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
    PatientRecord.resetCounter();
    PatientRecord p = new PatientRecord('M', 50, TriageLevel.RED);
    PatientRecord q = new PatientRecord('M', 49, TriageLevel.RED);
    {
      int expected = -1;
      int actual = p.compareTo(q);
      if (expected != actual) {
        return false;
      }
    }

    {
      int expected = 1;
      int actual = q.compareTo(p);
      if (expected != actual) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests whether patients in the same triage level and with the same order of arrival are equal
   * (compareTo should return 0). Even though this case will not be possible in your priority queue,
   * it is required for testing the full functionality of the compareTo() method. Hint: you will
   * need to use the resetCounter() to create equivalent PatientRecords.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageSameArrival() {
    PatientRecord.resetCounter();
    PatientRecord p = new PatientRecord('M', 50, TriageLevel.RED);
    PatientRecord.resetCounter();
    PatientRecord q = new PatientRecord('M', 49, TriageLevel.RED);

    {
      int expected = 0;
      int actual = p.compareTo(q);
      if (expected != actual) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
   * the following tests: 
   *
   * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
   * IllegalArgumentException 
   * - Calling the PriorityCareAdmissions with a valid capacity should not throw any errors, and
   * should result in a new PriorityCareAdmissions which is empty, has size 0, a capacity equal to
   * the capacity that was passed as a parameter.
   *
   * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
   * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
   */
  public static boolean testConstructor() {
    PatientRecord.resetCounter();

    try {
      PriorityCareAdmissions p = new PriorityCareAdmissions(-1);
      return false;
    } catch (IllegalArgumentException e) {}

    PriorityCareAdmissions q = new PriorityCareAdmissions(5);

    {
      boolean expected = true;
      boolean actual = q.isEmpty();
      if (actual != expected) {
        return false;
      }
    }

    {
      int expected = 0;
      int actual = q.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      int expected = 5;
      int actual = q.capacity();
      if (expected != actual) {
        return false;
      }
    }

    return true;
  }

  

  /**
   * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
   * throws a NoSuchElementException.
   * 
   * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
   */
  public static boolean testPeekEmpty() {
    PatientRecord.resetCounter();

    try {
      PriorityCareAdmissions p = new PriorityCareAdmissions(5);
      p.peek();
      return false;
    } catch (NoSuchElementException e) {}

    return true;
  }

  /**
   * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
   * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
   * the PatientRecord from the queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekNonEmpty() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(3);
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.RED);
    PatientRecord p2 = new PatientRecord('M', 49, TriageLevel.GREEN);
    p.addPatient(p2);
    p.addPatient(p1);
    

    {
      PatientRecord expected = p1;
      PatientRecord actual = p.peek();
      if (!(expected.equals(actual))) {
        return false;
      }
    }

    {
      int expected = 2;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }


    return true;
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
   * ensuring the method 1) adds the PatientRecord and 2) increments the size.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientEmpty() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(3);
    PriorityCareAdmissions r = new PriorityCareAdmissions(1);

    PatientRecord n = null;
    PatientRecord m = new PatientRecord('M', 49, TriageLevel.RED);
    
    try {
      p.addPatient(n);
      return false;
    } catch (NullPointerException e) {}

    try {
      r.addPatient(m);
      r.addPatient(m);
      return false;
    } catch (IllegalStateException e) {}

    {
      int expected = 0;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.RED);
    p.addPatient(p1);

    {
      int expected = 1;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

    {
      PatientRecord expected = p1;
      PatientRecord actual = p.peek();
      if (!actual.equals(expected)) {
        return false;
      }
    }


    return true;
  }


  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
   * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
   * size. Try add at least 5 PatientRecords.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
   */
  public static boolean testAddPatientNonEmpty() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(6);
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.YELLOW);
    p.addPatient(p1);

    PatientRecord[] temp = p.arrayHeapCopy();

    PatientRecord n = null;
    
    try {
      p.addPatient(n);
      return false;
    } catch (NullPointerException e) {}

    {
      PatientRecord expected = p1;
      PatientRecord actual = temp[0];
      if (!expected.equals(actual)) {
        return false;
      }
    }

    {
      int expected = 1;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

  
    PatientRecord p2 = new PatientRecord('F', 35, TriageLevel.YELLOW);
    p.addPatient(p2);
    temp = p.arrayHeapCopy();

    {
      PatientRecord expected = p2;
      PatientRecord actual = temp[1];
      if (!expected.equals(actual)) {
        return false;
      }
    }

    {
      int expected = 2;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

    PatientRecord p3 = new PatientRecord('F', 29, TriageLevel.RED);
    p.addPatient(p3);
    temp = p.arrayHeapCopy();

    {
      PatientRecord expected = p3;
      PatientRecord actual = temp[0];
      if (!expected.equals(actual)) {
        return false;
      }
    }

    {
      int expected = 3;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

    PatientRecord p4 = new PatientRecord('M', 17, TriageLevel.RED);
    p.addPatient(p4);
    temp = p.arrayHeapCopy();

    {
      PatientRecord expected = p4;
      PatientRecord actual = temp[1];
      if (!expected.equals(actual)) {
        return false;
      }
    }

    {
      int expected = 4;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

    PatientRecord p5 = new PatientRecord('F', 100, TriageLevel.RED);
    p.addPatient(p5);
    temp = p.arrayHeapCopy();

    {
      PatientRecord expected = p5;
      PatientRecord actual = temp[4];
      if (!expected.equals(actual)) {
        return false;
      }
    }


    {
      int expected = 5;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

    PatientRecord p6 = new PatientRecord('M', 5, TriageLevel.GREEN);
    p.addPatient(p6);
    temp = p.arrayHeapCopy();

    {
      PatientRecord expected = p6;
      PatientRecord actual = temp[5];
      if (!expected.equals(actual)) {
        return false;
      }
    }

    {
      int expected = 6;
      int actual = p.size();
      if (expected != actual) {
        return false;
      }
    }

    PatientRecord p7 = new PatientRecord('M', 19, TriageLevel.RED);
   
    try {
      p.addPatient(p7);
      return false;
    } catch (IllegalStateException e) {}

    return true;
  }


  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
   * ensuring the method throws an IllegalStateException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientFull() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(3);
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.RED);
    p.addPatient(p1);
    p.addPatient(p1);
    p.addPatient(p1);

    try {
      p.addPatient(p1);
      return false;
    } catch (IllegalStateException e) {}

    return true;
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() with a null
   * PatientRecord and ensuring the method throws a NullPointerException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientNull() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = null;
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.RED);

    try {
      p.addPatient(p1);
      return false;
    } catch (NullPointerException e) {}

    return true;
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
   * queue.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
   *         false otherwise
   */
  public static boolean testRemoveBestRecordEmpty() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions q = new PriorityCareAdmissions(5);

    try {
      q.removeBestRecord();
      return false;
    } catch (NoSuchElementException e) {}


    return true;
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
   * of size one.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
   *         size is 0
   */
  public static boolean testRemoveBestRecordSizeOne() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(3);
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.RED);
    p.addPatient(p1);

    PatientRecord q = p.removeBestRecord();
    {
      int expected = 0;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      PatientRecord expected = p1;
      PatientRecord actual = q;
      if (!actual.equals(expected)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Tests the functionality of removeBestRecord() methods. 
   * 
   * The removeBestRecord() method must remove, and return the patient record with the highest
   * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
   * method is successfully called. 
   * 
   * Remove the best record from a queue whose size is at least 6. Consider cases where
   * percolate-down recurses on left and right.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testRemoveBestRecordNonEmpty() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(7);
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.YELLOW);
    PatientRecord p2 = new PatientRecord('F', 35, TriageLevel.YELLOW);
    PatientRecord p3 = new PatientRecord('F', 29, TriageLevel.RED);
    PatientRecord p4 = new PatientRecord('M', 17, TriageLevel.RED);
    PatientRecord p5 = new PatientRecord('F', 100, TriageLevel.RED);
    PatientRecord p6 = new PatientRecord('M', 5, TriageLevel.GREEN);
    
    p.addPatient(p1);
    p.addPatient(p2);
    p.addPatient(p3);
    p.addPatient(p4);
    p.addPatient(p5);
    p.addPatient(p6);

    PatientRecord aux = p.removeBestRecord();

    {
      int expected = 5;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      PatientRecord expected = p3;
      PatientRecord actual = aux;
      if (!actual.equals(expected)) {
        return false;
      }
    }

    aux = p.removeBestRecord();

    {
      int expected = 4;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      PatientRecord expected = p4;
      PatientRecord actual = aux;
      if (!actual.equals(expected)) {
        return false;
      }
    }


    aux = p.removeBestRecord();

    {
      int expected = 3;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      PatientRecord expected = p1;
      PatientRecord actual = aux;
      if (!actual.equals(expected)) {
        return false;
      }
    }

    aux = p.removeBestRecord();

    {
      int expected = 2;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      PatientRecord expected = p5;
      PatientRecord actual = aux;
      if (!actual.equals(expected)) {
        return false;
      }
    }

  
    aux = p.removeBestRecord();

    {
      int expected = 1;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      PatientRecord expected = p6;
      PatientRecord actual = aux;
      if (!actual.equals(expected)) {
        return false;
      }
    }

    aux = p.removeBestRecord();

    {
      int expected = 0;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    {
      PatientRecord expected = p2;
      PatientRecord actual = aux;
      if (!actual.equals(expected)) {
        return false;
      }
    }


    return true;
  }

  /**
   * Tests the functionality of the clear() method.
   * Should implement at least the following scenarios: 
   * - clear can be called on an empty queue with no errors 
   * - clear can be called on a non-empty queue with no errors 
   * - After calling clear(), the queue should contain zero PatientRecords. 
   * - After calling clear(), the size should be 0
   *
   * @return true if PriorityCareAdmissions.clear() functions properly
   */
  public static boolean testClear() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(3);

    p.clear();
    {
      int expected = 0;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }

    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.YELLOW);
    PatientRecord p2 = new PatientRecord('F', 35, TriageLevel.YELLOW);
    p.addPatient(p1);
    p.addPatient(p2);

    p.clear();
    {
      int expected = 0;
      int actual = p.size();
      if (actual != expected) {
        return false;
      }
    }
    
    return true;
  }


  /**
   * Tests toString() method of PriorityCareAdmissions class.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testToString() {
    PatientRecord.resetCounter();
    PriorityCareAdmissions p = new PriorityCareAdmissions(3);
    PatientRecord p1 = new PatientRecord('M', 50, TriageLevel.RED);
    PatientRecord p2 = new PatientRecord('M', 49, TriageLevel.GREEN);
    p.addPatient(p2);
    p.addPatient(p1);

    {
      String expected = "25002: 50M (RED) - not seen" +
           "\n" + "24904: 49M (GREEN) - not seen" + "\n";

      String actual = p.toString();
      if (!actual.equals(expected)) {
        return false;
      }
    }
    
    return true;
  }


  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {

    return testPatientRecordCompareToDifferentTriage()
        && testPatientRecordCompareToSameTriageDifferentArrival()
        && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
        && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
        && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
        && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
        && testToString();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToDifferentTriage: "
        + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
        + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
        + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
    System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
    System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
    System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
  }

}
