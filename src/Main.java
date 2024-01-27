/**
 * Main implements the application user interface for athletes. The user will be prompted with a menu after the application is started
 * The user (athlete) should select an option of the menu:
 * User can enter their symptoms, display symptoms summary, and view the risky condition indicator
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;



public class Main {

    public static Scanner scan = new Scanner(System.in);
    /****Implementation of PART A*******
     * This method request the user to input the pain severity score for the 22 symptoms
     * Data will be stored in an ArrayList
     * This method returns an ArrayList with all the pain severity scores entered by the user
     * The pain severity scores can be 0-6 (none to severe)
     */
    static String[] symptoms;
    static List<Integer>[] arraySymptomsAssessment=new List[5];
    static List<Integer>[] modifiedArraySymptomsAssessment=new List[5];
    static ArrayList<String> calculatedOverallRatingArrayList = new ArrayList<String>(); //arraylist for storing overallRating


    static int i=0;
    static ArrayList<Integer> totalSymptomsArrayList = new ArrayList<Integer>(); //arraylist for storing totalSymptoms

    /****************************PART A IMPLEMENTATION START*****************************/
    public static ArrayList<Integer> SymptomsAssessment(){
        ArrayList<Integer> symptomSeverityScoreList = new ArrayList<Integer>(); //arraylist for storing severity scores of the symptoms

        symptoms = new String[] {"Headache", "Pressure in head", "Neck pain", "Nausea or vomiting", "Dizziness", "Blurred vision", "Balance problems", "Sensitivity to light", "Sensitivity to noise",
                "Feeling slowed down", "Feeling like 'in a fog'", "Don't feel right", "Difficulty concentrating", "Difficulty remembering", "Fatigue or low energy",
                "Confusion", "Drowsiness", "Trouble falling asleep", "More emotional", "Irritability", "Sadness", "Nervous or anxious"};

        for (String s:symptoms){
             System.out.print("Please enter your pain severity score for "+ s +" (none(0), mild(1-2), moderate(3-4), severe(5-6)): ");
            int severityScoreEntry = scan.nextInt();
            if (severityScoreEntry >= 0 && severityScoreEntry <= 6){
                symptomSeverityScoreList.add(severityScoreEntry);
            }
            else{
                System.out.print("Invalid Input. Please try again (0 - 6)");
                System.out.print("Please enter your pain severity score for "+ s +" (none(0), mild(1-2), moderate(3-4), severe(5-6)): ");
                severityScoreEntry = scan.nextInt();
                symptomSeverityScoreList.add(severityScoreEntry);
            }
        }
        return symptomSeverityScoreList;
    }
    /****************************PART A IMPLEMENTATION END*****************************/

    /****************************PART B IMPLEMENTATION START*****************************/
    public static int calculateTotalSymptoms(List<Integer> symptomsAssessmentList)
    {
        int sum=0;
        for (int a : symptomsAssessmentList){
            if(a!=0)
                sum +=1;
        }
        return sum;
    }

    public static int calculateSeverityScore(List<Integer> symptomAssesmentList)
    {
        int sum=0;
        for (int a: symptomAssesmentList){
            sum +=a;
        }
        return sum;
    }


    public static String calculateOverallRating( ArrayList<Integer> totalSymptoms, ArrayList<Integer> severityScore)
    {
        String response;
        int size = totalSymptoms.size();

      int totalSymptomsDifference= Math.abs(totalSymptoms.get(size - 1) -totalSymptoms.get(size-2));
      int severityScoreDifference= Math.abs(severityScore.get(size - 1) -severityScore.get(size-2));

        if(totalSymptomsDifference<3 && severityScoreDifference<10)
            response="No Difference";
        else if(totalSymptomsDifference<3 && severityScoreDifference>=10){
            response="Unsure";
        }
        else if(totalSymptomsDifference>=3 || severityScoreDifference>=15){
            response="Very Different";
        }
        else{
            response="Default";
        }

        return response;
    }
    /****************************PART B IMPLEMENTATION END*****************************/

    /****************************MAIN APPLICATION START**************************************/
    public static void main(String[] args) {

        do{
            System.out.printf("\tWELCOME TO THE SPORT CONCUSSION ASSESSMENT SYSTEM!");
            System.out.println();
            System.out.println("\t ****************************************************");
            System.out.println();
            System.out.println("\t Please select one of the following options: ");
            System.out.println();
            System.out.println("\t 1. Enter Symptoms");
            System.out.println("\t 2. Display Symptoms Summary");
            System.out.println("\t 3. View my Risky Condition Indicator");
            System.out.println("\t 4. Exit");
            System.out.print("\t Enter your choice (1-4)");
            int choice = scan.nextInt();

            switch(choice) {
                /****************************PART A START*****************************/
                case 1: // Request user to input pain severity score for the 22 symptoms & store in-session data for the last 5 symptoms assessment

                    System.out.println();
                    System.out.println("\t Enter symptoms");
                    ArrayList<Integer> symptomsData = SymptomsAssessment();
                    int arrayLength = arraySymptomsAssessment.length;
                    if (i < 5) {
                        arraySymptomsAssessment[i] = symptomsData;
                    } else {
                        i--;
                        for (int i = 0, k = 0; i < arrayLength; i++) {

                            // if the index is
                            // the removal element index
                            if (i == 0) {
                                continue;
                            }

                            // if the index is not
                            // the removal element index
                            modifiedArraySymptomsAssessment[k++] = arraySymptomsAssessment[i];
                        }

                        modifiedArraySymptomsAssessment[i] = symptomsData;
                    }
                    i++;

                    break;
                /****************************PART A END*****************************/
                /****************************PART B START*****************************/
                case 2: // Display Symptoms Summary containing totalNumberOfSymptoms, symptomSeverityScore, overallRating
                    int totalSymptoms = 0;
                    int symptomSeverityScore = 0;
                    int length = 0;
                    String overallRating;
                    ArrayList<Integer> calculatedTotalSymptomsArrayList = new ArrayList<Integer>(); //arraylist for storing totalSymptoms
                    ArrayList<Integer> calculatedSeverityScoreArrayList = new ArrayList<Integer>(); //arraylist for storing symptomSeverityScore
                   // ArrayList<String> calculatedOverallRatingArrayList = new ArrayList<String>(); //arraylist for storing overallRating

                    System.out.println();
                    System.out.println("Symptoms Summary:");
                    System.out.println("***************************");
                    if (i < 5) {
                        for (int x = 0; x < arraySymptomsAssessment.length; x++) {
                            if (arraySymptomsAssessment[x] != null) {
                                length++;
                                totalSymptoms = calculateTotalSymptoms(arraySymptomsAssessment[x]);
                                calculatedTotalSymptomsArrayList.add(totalSymptoms);
                                symptomSeverityScore = calculateSeverityScore(arraySymptomsAssessment[x]);
                                calculatedSeverityScoreArrayList.add(symptomSeverityScore);
                                if (length > 1) {
                                    overallRating = calculateOverallRating(calculatedTotalSymptomsArrayList, calculatedSeverityScoreArrayList);
                                    calculatedOverallRatingArrayList.add(overallRating);
                                }
                            }
                        }
                    }

                    System.out.println("ArrayList of Sum of TotalSymptoms");
                    System.out.println(calculatedTotalSymptomsArrayList);
                    System.out.println("ArrayList of SeverityScore");
                    System.out.println(calculatedSeverityScoreArrayList);
                    System.out.println("ArrayList of overall Rating");
                    System.out.println(calculatedOverallRatingArrayList);

                    break;

                /****************************PART B END*****************************/
                /****************************PART C START*****************************/
                case 3: // View my Risky Condition Indicator

                    int lastGameNumber= calculatedOverallRatingArrayList.size();
                    if(lastGameNumber>0)
                    {
                        System.out.println(lastGameNumber);

                        System.out.println(calculatedOverallRatingArrayList.get(lastGameNumber));

                        String response=calculatedOverallRatingArrayList.get(lastGameNumber);
                        if(response=="No Difference"){
                            System.out.println("Risk Condition Indicator: Green");
                        }
                        else if(response == "Unsure"){
                            System.out.println("Risk Condition Indicator: Yellow");
                        }
                        else if(response == "Very Different"){
                            System.out.println("Risk Condition Indicator: Red");
                        }
                        else{
                            System.out.println("No Risk Condition Indicator is available");
                        }
                    }
                    else{
                       System.out.println("No Risk Condition Indicator is available");
                    }

                    break;
                /****************************PART C START*****************************/
                case 4: //Exit
                    System.out.println();
                    System.out.println("\t See you next time!");
                    System.exit(0);
                    break;

                default:
                    System.out.println();
                    System.out.println("\t Invalid entry, please try again");
            }
        } while(true);
    }

}

