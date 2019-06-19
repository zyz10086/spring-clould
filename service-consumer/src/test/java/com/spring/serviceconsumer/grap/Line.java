package com.spring.serviceconsumer.grap;

public class Line {

    /**
     * 起始点
     */
    private Node begin;
    /**
     * 终点
     */
    private Node end;

    /**
     * 权重
     */
    private int weight;

    public Line(Node begin,Node end,int weight){
        this.begin=begin;
        this.end=end;
        this.weight=weight;
    }

    public Node getBegin() {
        return begin;
    }

    public void setBegin(Node begin) {
        this.begin = begin;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }





}
