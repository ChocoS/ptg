package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.Random01Sampler;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractDistributionComponent extends VerticalLayout {

  private final String name;

  protected AbstractDistributionComponent(String name) {
    this.name = name;
    add(new Label(name));
    setPadding(false);
    setSpacing(false);
  }

  public String getName() {
    return name;
  }

  public abstract Random01Sampler getSampler();
}