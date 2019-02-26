package com.pwawrzyniak.pythagorastreegenerator.backend.service;

import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Point;
import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Polygon;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImageCreator {

  public static InputStream createImageInputStream(List<Polygon> polygons) {
    System.setProperty("java.awt.headless", "false");
    GraphicsConfiguration gfxConfig = GraphicsEnvironment.
        getLocalGraphicsEnvironment().getDefaultScreenDevice().
        getDefaultConfiguration();

    int minX = countMinX(polygons);
    int maxX = countMaxX(polygons);
    int minY = countMinY(polygons);
    int maxY = countMaxY(polygons);
    BufferedImage bufferedImage = gfxConfig.createCompatibleImage(maxX - minX, maxY - minY);
    Graphics2D g2d = bufferedImage.createGraphics();

    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, maxX - minX, maxY - minY);
    g2d.setColor(Color.BLACK);

    Color brown = new Color(139, 69, 19);
    Color green = new Color(10, 139, 11);
    polygons.sort((p1, p2) -> p1.baseDistance() - p2.baseDistance() > 0 ? 1 : p1.baseDistance() - p2.baseDistance() < 0 ? -1 : 0);
    polygons.forEach(polygon -> {
      g2d.setColor(determineColor(polygon, brown, green));
      g2d.fillPolygon(getXPointsShifted(polygon, minX), getYPointsShifted(polygon, minY), polygon.points.size());
    });

    g2d.dispose();

    // Flip the image vertically
    AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
    tx.translate(0, -bufferedImage.getHeight(null));
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    bufferedImage = op.filter(bufferedImage, null);

    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
      ImageIO.write(bufferedImage, "png", os);
      return new ByteArrayInputStream(os.toByteArray());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static Color determineColor(Polygon polygon, Color first, Color second) {
    if (polygon.baseDistance() > 10) {
      return first;
    } else {
      return second;
    }
  }

  private static int countMinX(List<Polygon> polygons) {
    double result = polygons.get(0).points.get(0).x;
    for (Polygon polygon : polygons) {
      for (Point point : polygon.points) {
        if (point.x < result) {
          result = point.x;
        }
      }
    }
    return (int) result;
  }

  private static int countMaxX(List<Polygon> polygons) {
    double result = polygons.get(0).points.get(0).x;
    for (Polygon polygon : polygons) {
      for (Point point : polygon.points) {
        if (point.x > result) {
          result = point.x;
        }
      }
    }
    return (int) result;
  }

  private static int countMinY(List<Polygon> polygons) {
    double result = polygons.get(0).points.get(0).y;
    for (Polygon polygon : polygons) {
      for (Point point : polygon.points) {
        if (point.y < result) {
          result = point.y;
        }
      }
    }
    return (int) result;
  }

  private static int countMaxY(List<Polygon> polygons) {
    double result = polygons.get(0).points.get(0).y;
    for (Polygon polygon : polygons) {
      for (Point point : polygon.points) {
        if (point.y > result) {
          result = point.y;
        }
      }
    }
    return (int) result;
  }

  private static int[] getYPointsShifted(Polygon polygon, int shift) {
    int[] result = new int[polygon.points.size()];
    for (int i = 0; i < polygon.points.size(); i++) {
      result[i] = (int) polygon.points.get(i).y - shift;
    }
    return result;
  }

  private static int[] getXPointsShifted(Polygon polygon, int shift) {
    int[] result = new int[polygon.points.size()];
    for (int i = 0; i < polygon.points.size(); i++) {
      result[i] = (int) polygon.points.get(i).x - shift;
    }
    return result;
  }
}