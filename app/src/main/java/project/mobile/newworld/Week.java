package project.mobile.newworld;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Bradley on 4/13/2016.
 * week object that stores stats of an entire week
 */
public class Week {
    private ArrayList<Day> days = new ArrayList<>();
    private String startDate;
    private int totalSteps;
    private double totalDistance;
    private double totalTime;

    public Week(String date){
        startDate = date;
    }
    public ArrayList<Day> getDays(){
        return days;
    }
    public String getDate(){
        return startDate;
    }
    public int getTotalSteps(){
        return totalSteps;
    }
    public double getTotalDistance(){
        return totalDistance;
    }
    public double getTotalTime(){
        return totalTime;
    }
    //should check dates to make sure they are within the week
    public void addDay(Day day){
        if(days.size() >= 7)
        {
            //error should never happen controlled when adding days to the week
            System.out.println("error this should never happen!");
        }
        else
        {
            days.add(day);
            totalTime += day.getTime();
            totalSteps += day.getSteps();
            totalDistance += day.getDistance();
        }
    }

    public void setStartDate(String start){
        this.startDate = start;
    }
    //this will be used to parse
    public String toString(){
        String retString = ""; //+ startDate;
        for(int i = 0; i <days.size(); i++){
            retString = retString + days.get(i).getDate() + "," + days.get(i).getSteps() + "," + days.get(i).getTime() + "," + days.get(i).getDistance() + " ";
        }
        return retString;
    }


}
