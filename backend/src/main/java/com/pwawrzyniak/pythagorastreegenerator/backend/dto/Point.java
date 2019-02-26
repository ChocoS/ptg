package com.pwawrzyniak.pythagorastreegenerator.backend.dto;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "Point(x: " + x + ", y: " + y + ")";
  }
}