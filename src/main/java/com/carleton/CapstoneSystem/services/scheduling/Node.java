package com.carleton.CapstoneSystem.services.scheduling;

import java.util.ArrayList;

public class Node {
    private ArrayList<WeekDay> rightSide;
    private ArrayList<WeekDay> leftSide;
    private Node parent;
    private ArrayList<Node> children;
    private int stage;
    private int cost;


    public Node(ArrayList<WeekDay> rightSide,ArrayList<WeekDay> leftSide,Node parent){
        this.setRightSide(rightSide);
        this.setLeftSide(leftSide);
        this.parent=parent;
        children = new ArrayList<Node>();


    }
    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addChild(Node node){
        children.add(node);
    }
    public ArrayList<Node> getChildren(){
        return children;
    }
    public ArrayList<WeekDay> getLeftSide() {
        return leftSide;
    }
    public void setLeftSide(ArrayList<WeekDay> leftSide) {
        this.leftSide = leftSide;
    }
    public ArrayList<WeekDay> getRightSide() {
        return rightSide;
    }
    public void setRightSide(ArrayList<WeekDay> rightSide) {
        this.rightSide = rightSide;
    }

    public int getStage() {
        return stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }
    public int getCost(){
        return cost;
    }
    public int setCost(int h){
        cost= stage+h;
        return cost;
    }
}
