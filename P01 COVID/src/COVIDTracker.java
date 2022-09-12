//////////////// P01 COVID //////////////////////////
//
// Title:    COVIDTracker
// Course:   CS 300 Fall 2020
//
// Author:   Jerry Yu
// Email:    jcyu4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
public class COVIDTracker {	
 /**
 * Adds ID to the appropriate test array if there is room.
 * @param pos The current array of positive tests
 * @param neg The current array of negative tests
 * @param id The tested individual’s unique identifier String
 * @param isPos The result of the test
 * @return true if the new record was added, false otherwise
 */
 public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos) {
  if(isPos == true) { //sees if test is positive
	for(int i = 0; i < pos.length; i++) { 
	 if(pos[i] == null) { //sees if array has space
	  pos[i] = id; //if it does have space then it adds the ID to the array
	  return true; //true is returned because id was successfully added
	 }	
	}
  }
  else if(isPos == false){ //sees if test is negative
   for(int i = 0; i < neg.length; i++) { 
    if(neg[i] == null) { //sees if array has space
	 neg[i] = id; //if it does have space then it adds the ID to the array
	 return true; //true is returned because id was successfully added
	}
   }
  }
  return false; //false is returned because ID was unable to be added
 }
 /**
 * Removes ID from the appropriate array if found and compacts array so that there are no gaps.
 * @param pos The current array of positive tests
 * @param neg The current array of negative tests
 * @param id The tested individuals unique identifier String
 * @return true if the new record was removed, false otherwise
 */
 public static boolean removeIndividual(String[] pos, String[] neg,String id) {
  boolean removeSuccess = false;
  int maxPos = pos.length - 1;
  int maxNeg = neg.length - 1;
  for(int i = 0; i < pos.length; i++) {
   if(pos[i] == id) {
    pos[i] = null;
	removeSuccess = true;
    }
   }
   for(int i = 0; i < neg.length; i++)	{
	if(neg[i] == id) {
	 neg[i] = null;
	 removeSuccess = true;
    }	
   }
   for(int i = 0; i < maxPos; i++) {
    for(int j = 0; j < pos.length - i; j++) {
	 if(pos[i] == null && pos[i+j] != null) {
      pos[i] = pos[i+j];
      pos[i+j] = null;
     }
	}
   }
   for(int i = 0; i < maxNeg; i++) {
	for(int j = 0; j < neg.length - i; j++) {
	 if(neg[i] == null && neg[i+1] != null) {
	  neg[i] = neg[i+1];
	  neg[i+1] = null;
	 }
	}
   }
   return removeSuccess;
  }
 /**
  * Reads both arrays and calculates statistics.
  * @param pos The current array of positive tests
  * @param neg The current array of negative tests
  * @return string with statistics from both arrays: Total Tests, Total Individuals Tested, 
  *     Percent Positive Tests, Percent Negative Tests
  */
 public static String getPopStats(String[] pos, String[] neg) {
  double totalTests = 0.0;
 
  double numPos = 0.0;
  int posCount = 0;		
  int LENGTH = pos.length + neg.length;
  String[] allTests = new String[LENGTH];
  for(int i = 0; i < pos.length; i++) {
   if(pos[i] != null) {
    totalTests++;
	numPos++;
	posCount++;
	allTests[i] = pos[i];
   }
  }
  for(int i = 0; i < neg.length; i++) {
   if(neg[i] != null) {
	totalTests++;
	allTests[i + posCount] = neg[i];
   }
  }
  double numUnique = 0.0;
  double numPosUnique = 0.0;
  String[] tempAll = new String[LENGTH];
  String[] tempPos = new String[pos.length];
  for(int i = 0; i < allTests.length; i++) {
   tempAll[i] = allTests[i];
  }
  for(int i = 0; i < allTests.length; i++) {
   for(int j = i+1; j < allTests.length; j++) {
	if(allTests[j] == allTests[i]) {
	 tempAll[j] = null;
	}
   }
  }
  for(int i = 0; i < tempAll.length; i++) {
   if(tempAll[i] != null) {
    numUnique++;
   }
  }
  for(int i = 0; i < pos.length; i++) {
   tempPos[i] = pos[i];
  }
  for(int i = 0; i < pos.length; i++) {
   for(int j = i+1; j < pos.length; j++) {
	if(pos[j] == pos[i]) {
	 tempPos[j] = null;
	}	
   }
  }
  for(int i = 0; i < tempPos.length; i++) {
   if(tempPos[i] != null) {
	numPosUnique++;
   }
  }
  double percentPos = 0.0;
  double percentUniquePos = 0.0; 
  int totaltests = (int) totalTests;
  int numunique = (int) numUnique;
  if(totalTests > 0) {
   percentPos = (numPos / totalTests) * 100;
  }
  else {
   percentPos = 0.0;
  }
  if(numUnique > 0) {
   percentUniquePos = (numPosUnique / numUnique) * 100;
  }
  else {
   numUnique = 0.0;
  }
  return "Total Tests: " + totaltests + "\nTotal Individuals Tested: " 
      + numunique + "\nPercent Positive Tests: " + percentPos + 
      "%\nPercent Positive Individuals: " + percentUniquePos + "%";    
 }
 /**
  * Reads both arrays, searches for given id and finds statistics for individual.
  * @param pos The current array of positive tests
  * @param neg The current array of negative tests
  * @param id The tested individuals unique identifier String
  * @return string with individual statistics: Total Tests, Number of Positive Tests, and
  *     Number of Negative Tests
  */
 public static String getIndividualStats(String[] pos, String[] neg, String id) {
  int totalTests = 0;
  int posTests = 0;
  int negTests = 0;
  for(int i = 0; i < pos.length; i++) {
   if(pos[i] == id) {
	totalTests++;
	posTests++;
   }
  }
  for(int i = 0; i < neg.length; i++) {
   if(neg[i] == id) {
	totalTests++;
	negTests++;
   }
  }
  return "Total Tests: " + totalTests + "\nPositive: " + posTests + "\nNegative: " + negTests;
 }
}
