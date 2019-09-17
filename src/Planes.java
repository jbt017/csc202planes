import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Scanner Class code adapted from stackoverflow
public class Planes {
    public static void main(String[] args) throws FileNotFoundException {
        // Setup file and create Scanner object
        File file=new File("/home/justinb/Documents/latech2/PlanesPart1/PlanesPart1/src/planes.txt");
        Scanner sc=new Scanner(file);
        sc.useDelimiter("\\s*,\\s*");

        // Skip header line in text file added
        sc.nextLine();

        //Create Array
        PlaneObj[] objarray = new PlaneObj[28];

        //Read file and create plane objects, sorting them in to an array
        for (int i = 0; i < 28; i++){

            String tempplanenum = sc.next();
            int tplanenum = Integer.parseInt(tempplanenum);
            String tplanedest = sc.next();
            String temptraveldays = sc.next();
            int ttraveldays = Integer.parseInt(temptraveldays);
            String tplanemeal = sc.next();
            String temprownum = sc.next();
            int trownum = Integer.parseInt(temprownum);
            String tempnumperrow = sc.nextLine().replace(", ", "");
            int tnumperrow = Integer.parseInt(tempnumperrow);

            //Create new plane instance
            PlaneObj nplane = new PlaneObj(tplanenum, tplanedest, ttraveldays, tplanemeal, trownum, tnumperrow);

            //Add plane object to array
            objarray[i] = nplane;

        }

        //Call Method to Sort filled Array of Planes
        BubbleSort.ArraySort(objarray);


        //main loop for user input
        while(true){
            System.out.println("Welcome to the Airline Plane information terminal.");
            Scanner inputobj = new Scanner(System.in); //user input
            System.out.println("To see a list of planes please type 'List' or type 'Search' to request information regarding a specific flight number.  Type Quit to close the program.");

            String maininput = inputobj.nextLine().toLowerCase();

            if (maininput.equals("quit")){
                System.out.println("Exiting Program.");
                break;
            }
            else if(maininput.equals("list")){
                for (int i = 0; i < objarray.length; i++) {
                    System.out.println("\n" + objarray[i] + "\n");
                }
            }
            else if (maininput.equals("search")){
                System.out.println("To search, please enter the 4-digit Plane ID Number: \n");
                if (maininput.equals("quit")){
                    System.out.println("Exiting Program.");
                }
                else{
                    String searchable = inputobj.nextLine();
                    try{
                        int searchnum = Integer.parseInt(searchable);
                        BinarySearch.ArraySearch(objarray, searchnum);
                    }
                    catch(Exception e){
                        System.out.println("An error occurred. Only 4-digit numbers are acceptable input for searches.");
                    }
                }
            }
        }
    }
}

class PlaneObj{
    // Instance Variables
    private int PlaneNum;
    private String PlaneDest;
    private int TravelDays;
    private String PlaneMeal;
    private int RowNum;
    private int NumPerRow;

    //Default Constructor for Class
    public PlaneObj(){
        this.PlaneNum = 99;
        this.PlaneDest = "Mercadia";
        this.TravelDays = 99;
        this.PlaneMeal = "no";
        this.RowNum = 99;
        this.NumPerRow = 99;
    }

    // Constructor Declaration of Class
    public PlaneObj(int PlaneNum, String PlaneDest, int TravelDays, String PlaneMeal, int RowNum, int NumPerRow){
        this.PlaneNum = PlaneNum;
        this.PlaneDest = PlaneDest;
        this.TravelDays = TravelDays;
        this.PlaneMeal = PlaneMeal;
        this.RowNum = RowNum;
        this.NumPerRow = NumPerRow;
    }

    //Getter PlaneNum
    public int getPlaneNum(){
        return PlaneNum;
    }

    //Setter PlaneNum
    public void setPlaneNum(int var){
        this.PlaneNum = var;
    }

    //Getter PlaneDest
    public String getPlaneDest(){
        return PlaneDest;
    }

    //Setter PlaneDest
    public void setPlaneDest(String var){
        this.PlaneDest = var;
    }

    //Getter TravelDays
    public int getTravelDays(){
        return TravelDays;
    }

    //Setter TravelDays
    public void setTravelDays(int var){
        this.TravelDays = var;
    }

    //Getter PlaneMeal
    public String getPlaneMeal(){
        return PlaneMeal;
    }

    //Setter PlaneNum
    public void setPlaneMeal(String var){
        this.PlaneMeal = var;
    }

    //Getter RowNum
    public int getRowNum(){
        return RowNum;
    }

    //Setter RowNum
    public void setRowNum(int var){
        this.RowNum = var;
    }

    //Getter NumPerRow
    public int getNumPerRow(){
        return NumPerRow;
    }

    //Setter NumPerRow
    public void setNumPerRow(int var){
        this.NumPerRow = var;
    }

    //String Override
    public String toString(){
        String tempdate, tempnum, tempseats;
        tempdate = "Sunday";
        tempnum = Integer.toString(this.PlaneNum);
        int seatstaken = this.NumPerRow * this.RowNum;
        tempseats = Integer.toString(seatstaken);
        switch(this.TravelDays){
            case 1: tempdate = "Sunday";
                break;
            case 2: tempdate = "Monday";
                break;
            case 3: tempdate = "Tuesday";
                break;
            case 4: tempdate = "Wednesday";
                break;
            case 5: tempdate = "Thursday";
                break;
            case 6: tempdate = "Friday";
                break;
            case 7: tempdate = "Saturday";
                break;
        }
        return "|Plane Number|: " + tempnum + " |Destination|: " + this.PlaneDest + " |Day of Travel|: " + tempdate + " |Seats Taken|: " + tempseats
                + " |Meal|: " + this.PlaneMeal;

    }
}


//Bubble Sort, implemented from javapoint.com
class BubbleSort{
    public static void ArraySort(PlaneObj[] array){
    int n = array.length;
    PlaneObj temp;
    for (int i=0; i < n; i++){
        for (int j = 1; j < (n-i); j++){
            if(array[j-1].getPlaneNum() > array[j].getPlaneNum()){
                //swap
                temp = array[j-1];
                array[j-1] = array[j];
                array[j] = temp;

            }
        }
    }
    }

}

//Binary Search adapted from baeldung.com
class BinarySearch{
    public static void ArraySearch(PlaneObj[] planes, int idnum){
        int first = 0;
        int last = 27;
        int mid = (first + last)/2;
        while( first <= last ){
            if ( planes[mid].getPlaneNum() < idnum ){
                first = mid + 1;
            }else if ( planes[mid].getPlaneNum() == idnum ){
                System.out.println("\nData Found:\n\n" + planes[mid] + "\n");
                break;
            }else{
                last = mid - 1;
            }
            mid = (first + last)/2;
        }
        if ( first > last ){
            System.out.println("Plane ID not here.");
        }
    }

}
