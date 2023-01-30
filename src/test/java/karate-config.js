function fn() {
  // Function To Read Env.Properties File
  var EnvSetup = function(property) { var javaConfig = Java.type('config.EnvSetup'); return javaConfig.getinstance().prop.getProperty(property) }

  // Env Types
  var env = karate.env; // get system property 'karate.env'
  var defaultEnv = 'TEST' // Default environment to run the karate tests

  console.log("ENV defined in karate.env: ", env)
  console.log("Checking if ENV read in karate.env  is empty");

  if ( env === "" || env === "<ENV>" || env == null ){
    env = EnvSetup('ENV')
    console.log('The Karate Env Selected Is: ', env );
  }

  // DO HERE IF/SWITCH FOR MULTIPLE ENV CONFIGURATIONS
  var config = {
    PRODUCT_URL: HOSTNAME + EnvSetup('PRODUCT_PATH')
  }

  karate.config('ssl', false);
  karate.config('connectTimeout', 30000);
  karate.config('readTimeout', 30000);
  karate.config('logPrettyRequest', true);
  karate.config('logPrettyResponse', true);
  karate.config('driver', {type: 'chrome'});

  return config;
}