package de.benfir.k8s.plugins;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import io.quarkus.qute.TemplateInstance;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@TopCommand()
@Command(
  name = "generate",
  mixinStandardHelpOptions = true,
  subcommands = {
    GeneratePvcCommand.class,
    GenerateStorageClassCommand.class,
  },
  version = "0.0.1"
)
public class GenerateCommand
{
  @CommandLine.Option(
    names = {"--dry-run"},
    description = "should generated manifest be shown (true) or be applied (false) to k8s?",
    defaultValue = "true",
    showDefaultValue = CommandLine.Help.Visibility.ALWAYS,
    scope = CommandLine.ScopeType.INHERIT
  )
  boolean dryrun;
  
  int execute(String str){
    if( this.dryrun ){
      System.out.println( str );
      System.out.println();
    } else {
      try( KubernetesClient kc = new DefaultKubernetesClient() ) {
        kc.resourceList( str ).createOrReplace();
      }
    }
    
    return 0;
  }
}
