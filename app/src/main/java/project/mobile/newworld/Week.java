package project.mobile.newworld;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Bradley on 4/13/2016.
 * week object that stores stats of an entire week
 */
public class Week {
    private ArrayList<Day> days = new ArrayList<>();
    private Date startDate;
    private int totalSteps;
    private double totalDistance;
    private double totalTime;

    public Week(){
        startDate = new Date();
    }
    public ArrayList<Day> getDays(){
        return days;
    }
    public Date getDate(){
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
    public void addDay(Day day){
        if(days.size()>=6)
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



}
