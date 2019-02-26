package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition;

import com.pwawrzyniak.pythagorastreegenerator.backend.dto.Point;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.MaxLevelStopCondition;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.MinLengthStopCondition;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.StopCondition;
import com.vaadin.flow.component.textfield.TextField;

public class MaxLevelAndMinLengthStopConditionComponent extends AbstractStopConditionComponent {

  private TextField maxLevelTextField = new TextField("max level", "10", "");
  private TextField minLengthTextField = new TextField("min length", "10", "");

  public MaxLevelAndMinLengthStopConditionComponent() {
    super("Max level and min length stop condition");
    add(maxLevelTextField, minLengthTextField);
  }

  @Override
  public StopCondition getStopCondition() {
    return new StopCondition() {

      private StopCondition maxLevelStopCondition = new MaxLevelStopCondition(Integer.parseInt(maxLevelTextField.getValue()));
      private StopCondition minLengthStopCondition = new MinLengthStopCondition(Double.parseDouble(minLengthTextField.getValue()));

      @Override
      public boolean isStop(Point pointA, Point pointB, int level) {
        return maxLevelStopCondition.isStop(pointA, pointB, level) && minLengthStopCondition.isStop(pointA, pointB, level);
      }
    };
  }
}