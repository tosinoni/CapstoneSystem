package com.carleton.CapstoneSystem.services.scheduling;

import com.carleton.CapstoneSystem.models.ScheduleDay;
import com.carleton.CapstoneSystem.models.WeekDay;

import java.util.ArrayList;

public class Node implements Comparable<Node>{


        private ScheduleDay presentationDay;
        private int evaluateNumber;


    public Node(){



    }
    public Node(Node n){
        this.presentationDay = new ScheduleDay(n.getPresentationDay());
        this.evaluateNumber= n.getEvaluateNumber();
    }


    public void setPresentationDay(ScheduleDay presentationDay) {
        this.presentationDay = presentationDay;
    }

    public int getEvaluateNumber() {
        return evaluateNumber;
    }

    public void setEvaluateNumber(int evaluationNumber) {
        this.evaluateNumber = evaluationNumber;
    }

    public ScheduleDay getPresentationDay() {
        return presentationDay;
    }


    @Override
    public int compareTo(Node o) {
        if(this.getEvaluateNumber()>o.getEvaluateNumber()){
            return 1;
        }else if(this.getEvaluateNumber()==o.getEvaluateNumber()){
            return 0;
        }else {
            return -1;
        }
    }
}
