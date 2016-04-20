package project.mobile.newworld;

/**
 * Created by Josh on 4/19/2016.
 */
public class Resource {
    private int water;
    private int food;
    private double health;
    private int walls;
    private int weapons;
    private int settlers;


    public Resource(){
        this.water = 0;
        this.food = 0;
        this.health = 1000;
        this.walls = 1;
        this.weapons = 0;
        this.settlers = 1;
    }

    /*
        checks if the walls have the required resources to level up
        if yes, it upgrades and returns true
        else returns false.
     */
    public boolean levelUpWalls(int water, int food){
        if(water > 10 && food > 10){
            this.walls++;
            return true;
        }
        return false;
    }

    /*
    the player can gain health depending on how much food and water they are willing to use.
     */
    public void gainHealth(int food, int water){

    }

    public int getWater(){
        return this.water;
    }

    public int getFood(){
        return this.food;
    }

    public double getHealth(){
        return this.health;
    }

    public int getWalls(){
        return this.walls;
    }

    public int getWeapons(){
        return this.weapons;
    }

    public int getSettlers(){
        return this.settlers;
    }

    public void gatherWater(int water){
        this.water += water;
    }

    public void gatherFood(int food){
        this.food += food;
    }
}
