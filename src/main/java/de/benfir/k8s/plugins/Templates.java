package de.benfir.k8s.plugins;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import java.util.List;

@CheckedTemplate
public class Templates {
  public static native TemplateInstance pvc(PvcParams params);
  public static native TemplateInstance storageclass(ScParams params);
}

class PvcParams {
  public String name;
  public String namespace;
  public String accessMode;
  public String storageRequest;
  public String storageClass;
}

class ScParams {
  public ScParams(
    String  name,
    boolean allowVolumeExpansion,
    List<String> mountOptions,
    String  parameters,
    String  provisioner,
    String  reclaimPolicy,
    String  volumeBindingMode
  ){
    this.name = name;
    this.allowVolumeExpansion = allowVolumeExpansion;
    this.mountOptions = mountOptions;
    this.parameters = parameters;
    this.provisioner = provisioner;
    this.reclaimPolicy = reclaimPolicy;
    this.volumeBindingMode = volumeBindingMode;
  }
  public String  name;
  public boolean allowVolumeExpansion;
  public List<String> mountOptions;
  public String  parameters;
  public String  provisioner;
  public String  reclaimPolicy;
  public String  volumeBindingMode;
}
