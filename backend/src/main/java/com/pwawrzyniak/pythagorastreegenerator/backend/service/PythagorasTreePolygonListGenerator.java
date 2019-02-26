package com.pwawrzyniak.pythagorastreegenerator.backend.service;

import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Point;
import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Polygon;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.Random01Sampler;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.StopCondition;

import java.util.ArrayList;
import java.util.List;

public class PythagorasTreePolygonListGenerator {

  public static List<Polygon> generate(Point pointA, Point pointB, Random01Sampler random01Sampler, StopCondition stopCondition) {
    List<Polygon> result = new ArrayList<>();
    generate(pointA, pointB, random01Sampler, result, 0, stopCondition);
    return result;
  }

  private static void generate(Point pointA, Point pointB, Random01Sampler random01Sampler, List<Polygon> result, int level, StopCondition stopCondition) {
    if (stopCondition.isStop(pointA, pointB, level)) {
      return;
    }
    Polygon square = createSquare(pointA, pointB);
    result.add(square);
    Polygon rightTriangle = createRightTriangle(square.points.get(3), square.points.get(2), random01Sampler);
    result.add(rightTriangle);
    generate(rightTriangle.points.get(0), rightTriangle.points.get(2), random01Sampler, result, level + 1, stopCondition);
    generate(rightTriangle.points.get(2), rightTriangle.points.get(1), random01Sampler, result, level + 1, stopCondition);
  }

  private static Polygon createRightTriangle(Point pointA, Point pointB, Random01Sampler random01Sampler) {
    List<Point> points = new ArrayList<>();
    points.add(pointA);
    points.add(pointB);
    points.add(rotate(middleOf(pointA, pointB), pointB, Math.PI * random01Sampler.nextSample()));

    return new Polygon(points);
  }

  private static Point middleOf(Point pointA, Point pointB) {
    return new Point((pointA.x + pointB.x) / 2, (pointA.y + pointB.y) / 2);
  }

  private static Polygon createSquare(Point pointA, Point pointB) {
    List<Point> points = new ArrayList<>();
    points.add(pointA);
    points.add(pointB);
    points.add(rotate(pointB, pointA, 3 * Math.PI / 2));
    points.add(rotate(pointA, pointB, Math.PI / 2));

    return new Polygon(points);
  }

  private static Point rotate(Point center, Point point, double angle) {
    return new Point(
        Math.cos(angle) * (point.x - center.x) - Math.sin(angle) * (point.y - center.y) + center.x,
        Math.sin(angle) * (point.x - center.x) + Math.cos(angle) * (point.y - center.y) + center.y
    );
  }
}