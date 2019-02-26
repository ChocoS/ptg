package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.stopcondition.StopCondition;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractStopConditionComponent extends VerticalLayout {

  private final String name;

  protected AbstractStopConditionComponent(String name) {
    this.name = name;
    add(new Label(name));
    setPadding(false);
    setSpacing(false);
  }

  public String getName() {
    return name;
  }

  public abstract StopCondition getStopCondition();
}