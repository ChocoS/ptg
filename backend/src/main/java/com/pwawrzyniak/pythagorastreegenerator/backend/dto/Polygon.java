package com.pwawrzyniak.pythagorastreegenerator.backend.dto;

import java.util.List;

public class Polygon {

  public List<Point> points;

  public Polygon(List<Point> points) {
    this.points = points;
  }

  public double baseDistance() {
    return distance(points.get(0), points.get(1));
  }

  private static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }

  @Override
  public String toString() {
    return "Polygon(" + points + ")";
  }
}