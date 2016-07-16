# Wildfly-10x
JBoss Wildfly 10+ POC Projects.

# log4j-logstash
A [wildfly](http://wildfly.org/) project that highlights to [Logstash](https://www.elastic.co/products/logstash) logging using slf4j/log4j. the _log4j_ implementation is injected using the Wildfly module dependency (see _jboss-deployment-structure.xml_)
```xml
<module name="org.slf4j" />
```
You could also use directly the _org.slf4j.impl_ module dependency (which is referenced by the _org.slf4j_ module) or the _org.apache.log4j_ module dependency (this latter is a shorthand for the _org.jboss.log4j.logmanager_ JBoss logging module).

_All these module dependencies are not mandatory for the project to run correctly_

### Logstash logging configuration
The _log4j.properties_ config file is embedded within the war project in _src/main/resources_ for per deployment Wildfly configuration (simpler). The logstash is configured within the log4j config file using the apache _SocketAppender_ as follows (in _src/main/resources_)
```
log4j.appender.socket=org.apache.log4j.net.SocketAppender
log4j.appender.socket.Port=${logstash.port}
log4j.appender.socket.RemoteHost=${logstash.host}
```
The logstash _host_ and _port_ are set via EL variables in order to make the final deployable environment/run-free.

## Wildfly logstash startup options
The Logstash _host_ and _port_ are set on Wildfly startup using the _-D_ command line options (JVM System Properties variables)
```sh
$> standalone -Dlogstash.host=127.0.0.1 -Dlogstash.port=56445
```

#### The logstash config file example
Here is a basic logstash config file for log4j input plugin (with logstash and [elasticsearch](https://www.elastic.co/products/elasticsearch) run on localhost)
```
input {
  log4j {
     mode => "server"
     host  => "127.0.0.1"
    port => 56445
    type => "log4j"
 }
}

output {                                       
  elasticsearch {
  }           
}          
```
