package com.pwawrzyniak.pythagorastreegenerator.backend.service;

import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Point;
import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Polygon;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.Random01Sampler;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.StopCondition;

import java.io.InputStream;
import java.util.List;

public class PythagorasTreeGenerator {

  public static InputStream asInputStream(int basicSquareLength, StopCondition stopCondition, Random01Sampler random01Sampler) {
    List<Polygon> polygons = PythagorasTreePolygonListGenerator.generate(new Point(0, 0), new Point(basicSquareLength, 0), random01Sampler, stopCondition);
    return ImageCreator.createImageInputStream(polygons);
  }
}