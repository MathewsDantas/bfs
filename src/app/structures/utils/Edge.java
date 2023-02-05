package app.structures.utils;

public class Edge {
    private float cost;
    
    public Edge(float cost) {
        this.cost = cost;
    }

    public float getCost(){
        return this.cost;
    }

    public void setCost(float cost){
        this.cost = cost;
    }
}