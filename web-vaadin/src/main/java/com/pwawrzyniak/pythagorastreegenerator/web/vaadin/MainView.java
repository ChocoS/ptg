package com.pwawrzyniak.pythagorastreegenerator.web.vaadin;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.PythagorasTreeGenerator;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution.AbstractDistributionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution.BinomialDistributionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution.CustomDistributionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution.TruncatedTo01InvertedNormalDistributionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution.TruncatedTo01NormalDistributionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution.UniformDistributionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition.AbstractStopConditionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition.MaxLevelAndMinLengthStopConditionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition.MaxLevelOrMinLengthStopConditionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition.MaxLevelStopConditionComponent;
import com.pwawrzyniak.pythagorastreegenerator.web.vaadin.stopcondition.MinLengthStopConditionComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

@Route("main")
@UIScope
public class MainView extends HorizontalLayout {

  private Image image = null;
  private TextField basicSquareLengthTextField = new TextField("Basic square length", "100", "");
  private ComboBox<String> distributionComboBox = new ComboBox<>();
  private ComboBox<String> stopConditionComboBox = new ComboBox<>();
  private Div distributionContainer = new Div();
  private Div stopConditionContainer = new Div();
  private AbstractDistributionComponent selectedDistributionComponent;
  private AbstractStopConditionComponent selectedStopConditionComponent;
  private Div imageContainer = new Div();
  private Notification selectDistributionNotification = new Notification("Select distribution", 3000, Notification.Position.TOP_CENTER);
  private Notification selectStopConditionNotification = new Notification("Select stop condition", 3000, Notification.Position.TOP_CENTER);

  public MainView() {
    initializeStopConditions();
    initializeDistributions();

    VerticalLayout leftMenuVerticalLayout = new VerticalLayout();
    leftMenuVerticalLayout.add(new Button("Go", event -> refreshImage()), basicSquareLengthTextField,
        stopConditionComboBox, stopConditionContainer, distributionComboBox, distributionContainer);
    leftMenuVerticalLayout.setWidth("250px");
    leftMenuVerticalLayout.setSpacing(false);
//    leftMenuVerticalLayout.setPadding(false);

    add(leftMenuVerticalLayout, imageContainer);
    setVerticalComponentAlignment(Alignment.BASELINE);

    setSpacing(false);
    setPadding(false);
  }

  private void initializeDistributions() {
    Map<String, AbstractDistributionComponent> distributionComponentMap =
        Stream.of(new TruncatedTo01NormalDistributionComponent(),
            new BinomialDistributionComponent(),
            new TruncatedTo01InvertedNormalDistributionComponent(),
            new CustomDistributionComponent(),
            new UniformDistributionComponent())
            .collect(Collectors.toMap(AbstractDistributionComponent::getName, identity()));

    List<String> distributionNameList = new ArrayList<>(distributionComponentMap.keySet());
    Collections.sort(distributionNameList);
    distributionComboBox.setItems(distributionNameList);
    distributionComboBox.addValueChangeListener(event -> {
      if (selectedDistributionComponent != null) {
        distributionContainer.remove(selectedDistributionComponent);
        selectedDistributionComponent = null;
      }
      if (event.getValue() != null) {
        selectedDistributionComponent = distributionComponentMap.get(event.getValue());
        distributionContainer.add(selectedDistributionComponent);
      }
    });
    distributionComboBox.setValue(distributionNameList.get(0));
  }

  private void initializeStopConditions() {
    Map<String, AbstractStopConditionComponent> stopConditionComponentMap =
        Stream.of(new MaxLevelStopConditionComponent(), new MinLengthStopConditionComponent(),
            new MaxLevelAndMinLengthStopConditionComponent(), new MaxLevelOrMinLengthStopConditionComponent())
            .collect(Collectors.toMap(AbstractStopConditionComponent::getName, identity()));

    List<String> stopComponentNameList = new ArrayList<>(stopConditionComponentMap.keySet());
    Collections.sort(stopComponentNameList);
    stopConditionComboBox.setItems(stopComponentNameList);
    stopConditionComboBox.addValueChangeListener(event -> {
      if (selectedStopConditionComponent != null) {
        stopConditionContainer.remove(selectedStopConditionComponent);
        selectedStopConditionComponent = null;
      }
      if (event.getValue() != null) {
        selectedStopConditionComponent = stopConditionComponentMap.get(event.getValue());
        stopConditionContainer.add(selectedStopConditionComponent);
      }
    });
    stopConditionComboBox.setValue(stopComponentNameList.get(0));
  }

  private void refreshImage() {
    if (selectedDistributionComponent == null) {
      if (!selectDistributionNotification.isOpened()) {
        selectDistributionNotification.open();
      }
      return;
    }
    if (selectedStopConditionComponent == null) {
      if (!selectStopConditionNotification.isOpened()) {
        selectStopConditionNotification.open();
      }
      return;
    }
    if (image != null) {
      imageContainer.remove(image);
    }
    int basicSquareLength = Integer.valueOf(basicSquareLengthTextField.getValue());
    image = new Image(new StreamResource("PythagorasTree.png", () -> PythagorasTreeGenerator.asInputStream(basicSquareLength, selectedStopConditionComponent.getStopCondition(), selectedDistributionComponent.getSampler())), "alt");
    imageContainer.add(image);
  }
}