package de.benfir.k8s.plugins;

import io.quarkus.qute.TemplateInstance;
import picocli.CommandLine;

@CommandLine.Command( name = "pvc" )
public class GeneratePvcCommand implements Runnable
{
  @CommandLine.ParentCommand
  GenerateCommand parent;
  
  @CommandLine.Parameters( description = "resource name" )
  String name;
  
  @CommandLine.Option(
    names = {"-n", "--namespace"},
    description = "namespace",
    defaultValue = "default"
  )
  String namespace;
  
  @CommandLine.Option(
    names = {"--size", "-s"},
    description = "requested storage size",
    required = true
  )
  String storageRequest;
  
  @CommandLine.Option(
    names = {"--storageClass", "--storageclass"},
    description = "storage class"
  )
  String storageClass;
  
  @Override public void run()
  {
    PvcParams pvcParams = new PvcParams();
    pvcParams.name = this.name;
    pvcParams.namespace = this.namespace;
    pvcParams.storageRequest = storageRequest;
    pvcParams.storageClass = storageClass;
    pvcParams.accessMode = "ReadWriteOnce";
    
    TemplateInstance ti = Templates.pvc( pvcParams );
    String str = ti.render();
    
    this.parent.execute( str );
  }
  
}
