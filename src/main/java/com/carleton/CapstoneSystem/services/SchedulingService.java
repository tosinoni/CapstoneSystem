package com.carleton.CapstoneSystem.services;


import com.carleton.CapstoneSystem.models.*;
import com.carleton.CapstoneSystem.services.scheduling.Node;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

@Service
public class SchedulingService {



    private int numberOfPopulation= WeekDay.values().length;
    private int mutateNumber=0;
    private int iterations=0;



    public ScheduleDay geneticAlgorithm(ArrayList<ArrayList<ScheduleDay>> days){
        Node initialNode = new Node();
        initialNode.setPresentationDay(new ScheduleDay(Month.MARCH,WeekDay.MONDAY,12,new ArrayList<Time>()));
        initialNode.getPresentationDay().setPresentationTime(Time.NINE);


        //create a list of nodes which will hold the weights
        ArrayList<Node> nodes= new ArrayList<Node>();
        populate(nodes,numberOfPopulation);

        double old_accuracy = 0;
        double new_accuracy = 10;
        evaluate(initialNode,days);

        new_accuracy =initialNode.getEvaluateNumber();

        while(new_accuracy>0 && iterations<150){
            old_accuracy = new_accuracy;

            //calculate fitness - step 2 GA - selection
            //---------------------------------------------------------
            for(int j=0;j<nodes.size();j++){
                evaluate(nodes.get(j),days);
            }

            //create reproduction pool - step 2 GA - selection
            //--------------------------------------------------------
            Collections.sort(nodes);//now the first two nodes are the best ones.
            Node best = nodes.get(0);

            Node best_2=nodes.get(1);

            new_accuracy = best.getEvaluateNumber();
            System.out.println(best.getPresentationDay().getWeekDay()+" "+best.getPresentationDay().getDayDate()+" "+best.getPresentationDay().getPresentationTime());
            if(new_accuracy==0){
                System.out.println("we er");
                return best.getPresentationDay();
            }
            initialNode=best;
            //cross produce - step 3 GA - mating
            //----------------------------------------------------------------------------
            nodes.clear();
            if(best_2!=null) {
                nodes.add(mergeMean(best, best_2));
                nodes.add(mergeBias(best,best_2));
                nodes.add(mergeBias(best_2,best));
            }

            //mutate - step 3 GA - mutation
            //---------------------------------------------------------------------------
            for(int j=0;j<nodes.size();j++){
                mutate(nodes.get(j));
            }
            iterations++;
        }
        return initialNode.getPresentationDay();



    }

    private Node mergeBias(Node best_2, Node best) {
        ScheduleDay presentationDay= best.getPresentationDay();
        presentationDay.setPresentationTime(Time.MergeBias(best.getPresentationDay().getPresentationTime(),best_2.getPresentationDay().getPresentationTime()));

        return new Node(best);

    }


    private void evaluate(Node initialNode,ArrayList<ArrayList<ScheduleDay>> userDays) {
        int max=0;
        for(ArrayList<ScheduleDay> days: userDays) {
            int min = 100;
            for (ScheduleDay presentationDay : days) {
                int currentMin = initialNode.getPresentationDay().compareTo(presentationDay);
                currentMin += findClosestDistance(initialNode.getPresentationDay().getPresentationTime(), presentationDay.getTimes());
                if(currentMin<min){
                    min=currentMin;
                }

            }
            if(min>max){
                max=min;
            }

        }

        initialNode.setEvaluateNumber(max);
    }

    private int findClosestDistance(Time presentationTime,ArrayList<Time> times) {
        int min=100;
        for(Time time:times){
            int currentMin=Math.abs(time.compareTo(presentationTime));
            if(currentMin<min){
                min=currentMin;
            }
        }
        return min;
    }


    private Node mergeMean(Node best, Node best_2) {
        ScheduleDay presentationDay= best.getPresentationDay();
        presentationDay.setPresentationTime(Time.Mean(best.getPresentationDay().getPresentationTime(),best_2.getPresentationDay().getPresentationTime()));

        return new Node(best);
    }


    private void populate(ArrayList<Node> nodes, int numberOfPopulation2) {
        for(int i=0;i<numberOfPopulation;i++){
            Node newNode = new Node();
            ScheduleDay scheduleDay = new ScheduleDay();
            scheduleDay.setMonth(Month.MARCH);
            scheduleDay.setWeekDay(WeekDay.values()[i]);
            scheduleDay.setPresentationTime(getRandomTime());
            newNode.setPresentationDay(scheduleDay);
            nodes.add(newNode);
        }

    }
    private void mutate(Node node) {
        Random r = new Random();
        double p = r.nextDouble();
        if(p>0.5){
                ScheduleDay presentationDay=node.getPresentationDay();
            if(mutateNumber%2==0){
              node.getPresentationDay().setPresentationTime((node.getPresentationDay().getPresentationTime()).advance());
                if(node.getPresentationDay().equals(presentationDay)){
                    node.getPresentationDay().setWeekDay(node.getPresentationDay().getWeekDay().advance());

                }
            }else{
                node.getPresentationDay().setPresentationTime((node.getPresentationDay().getPresentationTime()).advance());
                if(presentationDay.equals(node.getPresentationDay())){
                    node.getPresentationDay().setWeekDay(node.getPresentationDay().getWeekDay().goBack());

                }
            }
            }
        mutateNumber++;
    }

    public Time getRandomTime() {
        Random r = new Random();
        int i = r.nextInt(Time.values().length);
        return Time.values()[i];
    }

    public ScheduleDay createPresentationTime(ArrayList<ArrayList<ScheduleDay>> scheduleDays) {
        return geneticAlgorithm(scheduleDays);
    }
}

