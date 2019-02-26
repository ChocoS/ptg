package com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition;

import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Point;

public class MaxLevelStopCondition implements StopCondition {

  private final int maxLevel;

  public MaxLevelStopCondition(int maxLevel) {
    this.maxLevel = maxLevel;
  }

  @Override
  public boolean isStop(Point pointA, Point pointB, int level) {
    return level > maxLevel;
  }
}