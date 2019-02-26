package com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition;

import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Point;

public class MinLengthStopCondition implements StopCondition {

  private final double minLength;

  public MinLengthStopCondition(double minLength) {
    this.minLength = minLength;
  }

  @Override
  public boolean isStop(Point pointA, Point pointB, int level) {
    return Math.sqrt((pointB.x - pointA.x) * (pointB.x - pointA.x) + (pointB.y - pointA.y) * (pointB.y - pointA.y)) < minLength;
  }
}