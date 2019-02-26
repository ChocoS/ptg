package com.pwawrzyniak.pythagorastreegenerator.web.vaadin.distribution;

import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.CustomDistribution;
import com.pwawrzyniak.pythagorastreegenerator.backend.service.distribution.Random01Sampler;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomDistributionComponent extends AbstractDistributionComponent {

  private TextField anglesListTextField = new TextField("integer angles list (0, 90)", "45", "");
  private TextField weightListTextField = new TextField("integer weight list", "1", "");

  public CustomDistributionComponent() {
    super("Custom distribution");
    add(anglesListTextField, weightListTextField);

    MemoryBuffer buffer = new MemoryBuffer();
    Upload upload = new Upload(buffer);
//    upload.setAcceptedFileTypes("text/plain");

    upload.addStartedListener(event -> {
      System.out.println("started");
    });
    upload.addFailedListener(event -> {
      System.out.println("failed");
    });
    upload.addSucceededListener(event -> {
      System.out.println("success!");
//      BufferedReader reader = new BufferedReader(new InputStreamReader(buffer.getInputStream(buffer.getFiles().iterator().next())));
      BufferedReader reader = new BufferedReader(new InputStreamReader(buffer.getInputStream()));
      try {
        anglesListTextField.setValue(reader.readLine());
        weightListTextField.setValue(reader.readLine());
      } catch (Exception e) {
        System.out.println("Could not read file");
      }
    });

//    add(upload);
  }

  @Override
  public Random01Sampler getSampler() {
    return new CustomDistribution(getIntegers(anglesListTextField.getValue()), getIntegers(weightListTextField.getValue()));
  }

  private List<Integer> getIntegers(String commaSeparatedIntegerListString) {
    return Arrays.stream(commaSeparatedIntegerListString.split(",")).mapToInt(string -> Integer.parseInt(string.trim())).boxed().collect(Collectors.toList());
  }
}