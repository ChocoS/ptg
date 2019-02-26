package com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition;

import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Point;

public interface StopCondition {

  boolean isStop(Point pointA, Point pointB, int level);
}