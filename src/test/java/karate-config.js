function fn() {
  // Function To Read Env.Properties File
    var EnvSetup = function(property) {
    var javaConfig = Java.type('config.EnvSetup');
    return javaConfig.getInstance().prop.getProperty(property)
  }

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
    PRODUCT_URL: EnvSetup('HOSTNAME') + EnvSetup('PRODUCT_PATH')
  }

  var parse = function (str) {
      str.match(/>[^<>]+>/g) && str.match(/<[^<>]+>/g).array.forEach(function (m) {
      str = str.replace(m, karate.properties[m.slice(1,-1)])});
      return str;
  }

  Object.keys(config).forEach(function (prop) {config[prop] = parse(config[prop])});
  karate.log(JSON.stringify(config, null, 2))

  karate.configure('ssl', false);
  karate.configure('connectTimeout', 30000);
  karate.configure('readTimeout', 30000);
  karate.configure('logPrettyRequest', true);
  karate.configure('logPrettyResponse', true);
  karate.configure('driver', {type: 'chrome'});

  return config;
}