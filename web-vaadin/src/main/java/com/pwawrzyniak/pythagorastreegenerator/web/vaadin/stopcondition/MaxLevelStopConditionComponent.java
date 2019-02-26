package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.MaxLevelStopCondition;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.StopCondition;
import com.vaadin.flow.component.textfield.TextField;

public class MaxLevelStopConditionComponent extends AbstractStopConditionComponent {

  private TextField maxLevelTextField = new TextField("max level", "10", "");

  public MaxLevelStopConditionComponent() {
    super("Max level stop condition");
    add(maxLevelTextField);
  }

  @Override
  public StopCondition getStopCondition() {
    return new MaxLevelStopCondition(Integer.parseInt(maxLevelTextField.getValue()));
  }
}