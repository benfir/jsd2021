package de.benfir.k8s.plugins;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import io.quarkus.qute.TemplateInstance;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.List;

@Command( name = "sc" )
public class GenerateStorageClassCommand implements Runnable
{
  @CommandLine.ParentCommand
  GenerateCommand parent;
  
  @CommandLine.Parameters( description = "resource name" )
  String name;
  
  @CommandLine.Option(
    names = {"--allowVolumeExpansion"},
    description = "shows whether the storage class allow volume expand",
    defaultValue = "false"
  )
  boolean allowVolumeExpansion;
  
  @CommandLine.Option(
    names = {"--provisioner"},
    description = "indicates the type of the provisioner",
    required = true
  )
  String provisioner;

  @CommandLine.Option(
    names = {"--reclaimPolicy"},
    description = "Dynamically provisioned PersistentVolumes of this storage class are created with this reclaimPolicy",
    defaultValue = "Delete"
  )
  String reclaimPolicy;
  
  @CommandLine.Option(
    names = {"--volumeBindingMode"},
    description = "indicates how PersistentVolumeClaims should be provisioned and bound.",
    defaultValue = "Immediate"
  )
  String volumeBindingMode;
  
  @CommandLine.Option(
    names = {"--mountOptions"},
    description = "mount options"
  )
  List<String> mountOptions;
  
  @Override public void run()
  {
    ScParams params = new ScParams(
      this.name,
      this.allowVolumeExpansion,
      this.mountOptions,
      "", // parameters
      this.provisioner,
      this.reclaimPolicy,
      this.volumeBindingMode
    );
  
    TemplateInstance ti = Templates.storageclass( params );
    String str = ti.render();
    
    this.parent.execute( str );
  }
  
}
