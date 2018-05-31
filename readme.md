# Centralized Application Logs with the Elastic Stack

This repository gives an overview of five different logging patterns:

* **Parse**: Take the log files of your applications and extract the relevant pieces of information.
* **Send**: Add a log appender to send out your events directly without persisting them to a log file.
* **Structure**: Write your events in a structured file, which you can then centralize.
* **Containerize**: Keep track of short lived containers and configure their logging correctly.
* **Orchestrate**: Stay on top of your logs even when services are short lived and dynamically allocated on Kubernetes.

The [slides for this talk are available on Speaker Deck](https://speakerdeck.com/xeraa/centralized-logging-patterns).


## Dependencies

* **JDK 8+** and **Gradle** to run the Java code.
* **Docker** (and Docker Compose) to run all the required components of the Elastic Stack (Filebeat, Logstash,
Elasticsearch, and Kibana) and the containerized Java application.


## Usage

* Bring up the Elastic Stack: `$ docker-compose up`
* Run the Java application: `$ gradle run`
* Remove the Elastic Stack (and its volumes): `$ docker-compose down -v`


## Demo

1. Take a look at the code — which pattern are we building with log statements here?
1. Run it with `gradle run` and see the output in the console.


### Parse

1. Copy a log line and start disecting it with the Grok Debugger in Kibana, for example with the pattern
   `^\[%{TIMESTAMP_ISO8601:timestamp}\]%{SPACE}%{LOGLEVEL:level}`. The rest will be done with the *logstash.conf*.
1. Try to find the log statements in Kibana's Discover view for the *parse* index.
1. Show the last 15min and up to 3h into the future to see that the timestamp is wrong. Fix it by uncommenting the
   `timezone` field and restart Logstash with `docker-compose restart logstash`.
1. Also show the pipeline in Kibana's monitoring view.
1. Run the code again and see that the timezone is fine now. But we have 42 entries instead of 40 — though 42 would
   generally be the perfect number.
1. See the `_grokparsefailure` in the tag field. Enable the multiline rules in Filebeat and restart it with
   `docker-compose restart filebeat_for_logstash `.
1. Show that this is working as expected now and drill down to the errors to see which emoji we are logging.
1. Create a vertical bar chart visualization on the `level` field. Further break it down into `session`.


### Send

1. Run the sunshine case and show the data in the *send* index.
1. Stop Logstash with `docker-compose stop logstash`.
1. Run the application and restart Logstash after that again with `docker-compose start logstash`.
1. Show the missing data for example by comparing it to the results in the *parse* index, which will be populated once
   Filebeat can reach Logstash again.


### Structure

1. Run the application and show the data in the *structure* index.
1. Show the Logback configuration for JSON, since it is a little more complicated than the others.

