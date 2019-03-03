package com.umich.tunisij.environment;

import java.util.*;
import java.util.stream.Collectors;

public class MazeContext {

    protected Maze maze = new Maze(8, 11);

    protected static final int GOAL_ROW = 0;
    protected static final int GOAL_COLUMN = 10;

    public MazeContext() {
        setMazeState();
    }

    public List<Map.Entry<Integer, Integer>> getAdjacentPositions(Map.Entry<Integer, Integer> node) {
        List<Map.Entry<Integer, Integer>> adjacentPositions = new ArrayList<>();
        adjacentPositions.add(Map.entry(node.getKey(), node.getValue() - 1)); //west
        adjacentPositions.add(Map.entry(node.getKey() - 1, node.getValue())); //north
        adjacentPositions.add(Map.entry(node.getKey(), node.getValue() + 1)); //east
        adjacentPositions.add(Map.entry(node.getKey() + 1, node.getValue())); //south


        adjacentPositions = adjacentPositions.stream().filter(position -> {
            if (position.getKey() < 0 || position.getValue() < 0 || position.getKey() >= maze.getHeight() || position.getValue() >= maze.getLength()) {
                return false;
            }
            String value = maze.getMaze()[position.getKey()][position.getValue()].getValue();
            if (value.equals("[]") || value.equals("GG")) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return adjacentPositions;
    }

    public Node getNode(Map.Entry<Integer, Integer> position) {
        return maze.getNode(position);
    }

    public String toString() {
        return maze.toString();
    }

    public void setMazeState() {
        maze = new Maze(8, 11);
        maze.setGoalPosition(GOAL_ROW, GOAL_COLUMN);
        setWallPositions();
    }

    public void setWallPositions() {
        List<Map.Entry<Integer, Integer>> wallPositions = new ArrayList<>();
        wallPositions.add(Map.entry(2, 4));
        wallPositions.add(Map.entry(2, 5));
        wallPositions.add(Map.entry(2, 6));
        wallPositions.add(Map.entry(3, 4));
        wallPositions.add(Map.entry(3, 7));
        wallPositions.add(Map.entry(4, 3));
        wallPositions.add(Map.entry(4, 4));
        wallPositions.add(Map.entry(4, 7));
        wallPositions.add(Map.entry(5, 6));
        wallPositions.add(Map.entry(5, 7));
        maze.setWallPositions(wallPositions);
    }
}