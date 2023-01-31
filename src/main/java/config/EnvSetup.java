package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvSetup {
  private static EnvSetup instance;
  public Properties prop;

  // Read Properties File And Stores The Values in a Properties Object
  private void readProperties() throws IOException {
    InputStream input = new FileInputStream("env.properties");
    prop = new Properties();
    prop.load(input);
  }

  // Read the Env Setup
  private EnvSetup() {
  }

  // If Instance Is Null Then Create a New Instance, Read The Properties File and
  // Return The Instance
  // Function Returns The Instance Of The EnvSetup Class
  public static EnvSetup getInstance() throws IOException {
    if (instance == null) {
      instance = new EnvSetup();
      instance.readProperties();
    }
    return instance;
  }

}