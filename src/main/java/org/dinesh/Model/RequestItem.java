package org.dinesh.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestItem {
    @JsonProperty("candidateId")
    private String candidateId;

    @JsonProperty("row")
    private int row;

    @JsonProperty("column")
    private int column;

    @JsonProperty("color")
    private String color;

    @JsonProperty("direction")
    private String direction;

    // Constructors, getters, and setters
    // ...

    public RequestItem() {
    }

    public RequestItem(RequestItem requestItem) {
        this.candidateId = requestItem.candidateId;
        this.row = requestItem.row;
        this.column = requestItem.column;
        this.color = requestItem.color;
        this.direction = requestItem.direction;
    }

    public RequestItem(String candidateId) {
        this.candidateId = candidateId;
    }

    public RequestItem(String candidateId, int row, int column) {
        this.candidateId = candidateId;
        this.row = row;
        this.column = column;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
