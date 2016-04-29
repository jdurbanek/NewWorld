package project.mobile.newworld;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
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
    private String date;

    public Day(String newDate){

        /*
        Format formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.format(newDate);
        */
        this.date = newDate;
    }
    public Day(String newDate,int steps, double distance, double time) {
        this.date = newDate;
        this.steps = steps;
        this.distance = distance;
        this.time = time;
    }
    public int getSteps(){
        return steps;
    }
    public double getDistance(){
        return distance;
    }
    public String getDate(){
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
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(double time){
        this.time = time;
    }


}
