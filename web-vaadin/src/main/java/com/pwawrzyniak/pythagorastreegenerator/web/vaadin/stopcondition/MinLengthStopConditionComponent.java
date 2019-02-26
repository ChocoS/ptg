package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.MinLengthStopCondition;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.StopCondition;
import com.vaadin.flow.component.textfield.TextField;

public class MinLengthStopConditionComponent extends AbstractStopConditionComponent {

  private TextField minLengthTextField = new TextField("min length", "10", "");

  public MinLengthStopConditionComponent() {
    super("Min length stop condition");
    add(minLengthTextField);
  }

  @Override
  public StopCondition getStopCondition() {
    return new MinLengthStopCondition(Double.parseDouble(minLengthTextField.getValue()));
  }
}