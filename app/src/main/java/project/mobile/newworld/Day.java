package project.mobile.newworld;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Bradley on 4/13/2016.
 * stores stats of a specific day
 */
public class Day {
    private int steps = 0;
    private double distance = 0;
    //time in minutes
    private double time;
    private Date date;

    public Day(Date date){
        this.date = date;
    }
    public int getSteps(){
        return steps;
    }
    public double getDistance(){
        return distance;
    }
    public Date getDate(){
        return date;
    }
    public double getTime(){
        return time;
    }
    public void setSteps(int steps){
        this.steps = steps;
    }
    public void setDistance(double distance){
        this.distance = distance;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setTime(double time){
        this.time = time;
    }


}
