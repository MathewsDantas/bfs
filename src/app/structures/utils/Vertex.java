package app.structures.utils;

public class Vertex {
    private float cost;
    private String label;
    
    public Vertex(float cost, String label) {
        this.cost = cost;
        this.label = label;
    }

    public float getCost(){
        return this.cost;
    }

    public void setCost(float cost){
        this.cost = cost;
    }

    public String getLabel(){
        return this.label;
    }

    public void setLabel(String label){
        this.label = label;
    }
}