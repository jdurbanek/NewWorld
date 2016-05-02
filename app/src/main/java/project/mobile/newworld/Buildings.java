package project.mobile.newworld;

/**
 * Created by Anthony on 4/27/2016.
 */
public class Buildings {
    public int mainBuilding;
    public int wall;
    public int barracks;
    public int farm;

    public Buildings(){


        this.mainBuilding = 1;
        this.barracks = 1;
        this.wall = 1;
        this.farm = 1;
    }

    public int getMainBuilding(){
        return this.mainBuilding;
    }

    public int getBarracks(){
        return this.barracks;
    }

    public int getWall(){
        return this.wall;
    }

    public int getFarm(){
        return this.farm;
    }

    public void upgradeMainBuilding(){
        this.mainBuilding++;
    }
    public void upgradeBarracks(){
        this.barracks++;
    }
    public void upgradeWall(){
        this.wall++;
    }
    public void upgradeFarm(){
        this.farm++;
    }


    public void setMBLevel(int ammount){
        this.mainBuilding = ammount;
    }
    public void setBarracksLevel(int ammount){
        this.barracks = ammount;
    }
    public void setFarmLevel(int ammount){
        this.farm = ammount;
    }


}
