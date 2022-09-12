//////////////// P01 COVID //////////////////////////
//
// Title:    COVIDTrackerTester
// Course:   CS 300 Fall 2020
//
// Author: 	 Jerry Yu
// Email: 	 jcyu4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: 		NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
public class COVIDTrackerTester {
 /**
 * Calls all test methods and prints them
 */
 public static void main(String[] args) {
  System.out.println("testAddTest(): " + testAddTest());
  System.out.println("testRemoveIndividual():" + testRemoveIndividual());
  System.out.println("testGetPopStats(): " + testGetPopStats());
  System.out.println("testGetIndividualStats(): " + testGetIndividualStats());
 }
 /**
 * Adds ID to the appropriate test array if there is room.
 * @param pos The current array of positive tests
 * @param neg The current array of negative tests
 * @param id The tested individual’s unique identifier String
 * @param isPos The result of the test
 * @return true if the new record was added, false otherwise
 */
 public static boolean testAddTest() {
  String[] pos = new String[2];
  String[] neg = new String[2];
  // two empty arrays -> true; also checking that arrays were updated properly
  if(!COVIDTracker.addTest(pos, neg, "A0001", false)||neg[0] == null) {
   return false;
  }
  if(!COVIDTracker.addTest(pos, neg, "A0001", true)||pos[0] == null) {
   return false;
  }
  // two arrays with space -> true
  if(!COVIDTracker.addTest(pos, neg, "A0002", false)||neg[1] == null) {
   return false;
  }
  // one full array but adding to one with space -> true
  if(!COVIDTracker.addTest(pos, neg, "A0003", true)||pos[1] == null) {
   return false;
  }
  // one array with space but adding to full one -> false
  String[] pos2 = new String[2];
  if(COVIDTracker.addTest(pos2, neg, "A0004", false)) {
   return false;
  }
  // two full arrays -> false
  if(COVIDTracker.addTest(pos, neg, "A0005", true)) {
   return false;
  }
  return true;
 }
 /**
 * Removes id from the appropriate test array if found and compacts array so that there are no gaps.
 * @param pos The current array of positive tests
 * @param neg The current array of negative tests
 * @param id The tested individual’s unique identifier String
 * @return true if the id was removed false otherwise
 */
 public static boolean testRemoveIndividual() {
  String[] pos = new String[]{"A0001", "A0004", "A0001", null};
  String[] neg = new String[]{"A0001", "A0002", "A0003", "A0005"};
  String[] pos2 = new String[]{"A0001", "A0001", "A0002", "A0003"};
  String[] neg2 = new String[]{"A0002", null, null, null};
  //removes from both arrays -> true; also checks that arrays were updated properly
  COVIDTracker.removeIndividual(pos, neg, "A0001");
  if(pos[0] != "A0004"||pos[1] != null||pos[2] != null||neg[3] != null) {
   return false;
  }
  //removes from in between 2 values checks that array compacts
  COVIDTracker.removeIndividual(pos, neg, "A0003");
  if(neg[0] != "A0002"||neg[1] != "A0005"||neg[2] != null||neg[3] != null) {
   return false;
  }
  //Checks if array can compact 2 empty spaces
  COVIDTracker.removeIndividual(pos2, neg2, "A0001");
  if(pos2[2] != null||pos2[3] != null) {
   return false;
  }
  //id does not exist -> false
  if(COVIDTracker.removeIndividual(pos, neg, "A0008")) {
   return false;
  }
  return true;
 }
 /**
 * Reads both arrays and calculates statistics.
 * @param pos The current array of positive tests
 * @param neg The current array of negative tests
 * @return string with statistics from both arrays: Total Tests, Total Individuals Tested, 
 *     Percent Positive Tests, Percent Negative Tests
 */
 public static boolean testGetPopStats() {
  //creates 2 arrays to test
  String[] pos = new String[5];
  String[] neg = new String[5];
  COVIDTracker.addTest(pos, neg, "A0001", false);
  COVIDTracker.addTest(pos, neg, "A0001", true);
  COVIDTracker.addTest(pos, neg, "A0002", false);
  COVIDTracker.addTest(pos, neg, "A0003", false);
  COVIDTracker.addTest(pos, neg, "A0004", true);
  COVIDTracker.addTest(pos, neg, "A0005", false);
  COVIDTracker.addTest(pos, neg, "A0001", true);
  //creates 2 empty arrays to test
  String[] pos2 = new String[] {null, null, null};
  String[] neg2 = new String[] {null, null, null};
  //tests the array with data  
  if(!COVIDTracker.getPopStats(pos, neg).equals("Total Tests: 7\n" + 
      "Total Individuals Tested: 5\n" + 
       "Percent Positive Tests: 42.857142857142854%\n" + 
       "Percent Positive Individuals: 40.0%")){
   return false; 	  
  }
  if(!COVIDTracker.getPopStats(pos2, neg2).equals("Total Tests: 0\n" +
      "Total Individuals Tested: 0\n" +
      "Percent Positive Tests: 0.0%\n" +
      "Percent Positive Individuals: 0.0%")) {
   return false;
  }
  return true;
 }
 /**
 * Reads both arrays, searches for given id and finds statistics for individual.
 * @param pos The current array of positive tests
 * @param neg The current array of negative tests
 * @param id The tested individuals unique identifier String
 * @return string with individual statistics: Total Tests, Number of Positive Tests, and
 *     Number of Negative Tests
 */
 public static boolean testGetIndividualStats() {
  //Create 2 arrays to test
  String[] pos = new String[4];
  String[] neg = new String[4];
  String[] pos2 = new String[2];
  String[] neg2 = new String[2];
  COVIDTracker.addTest(pos, neg, "A0001", false);
  COVIDTracker.addTest(pos, neg, "A0001", true);
  COVIDTracker.addTest(pos, neg, "A0002", false);
  COVIDTracker.addTest(pos, neg, "A0003", false);
  COVIDTracker.addTest(pos, neg, "A0004", true);
  COVIDTracker.addTest(pos, neg, "A0005", false);
  COVIDTracker.addTest(pos, neg, "A0001", true);
  //tests finding existing ID
  if(!COVIDTracker.getIndividualStats(pos, neg, "A0001").equals("Total Tests: 3\n" + 
       "Positive: 2\nNegative: 1")) {
   return false;
  }
  //tests finding ID that does not exist
  if(!COVIDTracker.getIndividualStats(pos, neg, "A0006").equals("Total Tests: 0\n" + 
      "Positive: 0\nNegative: 0")) {
   return false;
  }
  if(!COVIDTracker.getIndividualStats(pos2, neg2, "A0001").equals("Total Tests: 0\n" + 
      "Positive: 0\nNegative: 0")) {
   return false;
  }
  return true;
 }
}
