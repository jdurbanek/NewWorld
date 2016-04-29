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
    private int wood;
    private int stone;
    private int metal;



    public Resource(){
        this.water = 0;
        this.food = 0;
        this.wood = 0;
        this.stone = 0;
        this.metal = 0;
        this.health = 1000;
        this.weapons = 0;
        this.settlers = 1;
    }

    /*
        checks if the walls have the required resources to level up
        if yes, it upgrades and returns true
        else returns false.
     */

    /*
    the player can gain health depending on how much food and water they are willing to use.
     */
    public void gainHealth(int food, int water){

    }


    public int getWater(){ return this.water;}

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

    public int getStone() {return this.stone;}
    public int getWood() {return this.wood;}
    public int getMetal() {return this.metal;}

    public void setWood(int ammount){
        this.wood = ammount;
    }
    public void setStone(int ammount){
        this.stone = ammount;
    }
    public void setMetal(int ammount){
        this.metal = ammount;
    }


    public int collectWood(){
        return this.wood+=100;
    }
    public int collectStone(){
        return this.stone += 100;
    }
    public int collectMetal(){
        return this.metal += 100;
    }






    //spending resources
    public int spendMetal(int ammount){
        return this.metal-ammount;
    }
    public int spendStone(int ammount){
        return this.stone-ammount;
    }
    public int spendWood(int ammount){
        return this.wood-ammount;
    }
}
