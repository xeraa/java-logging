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

1. Copy a log line and start parsing it with the Grok Debugger in Kibana, for example with the pattern
   `^\[%{TIMESTAMP_ISO8601:timestamp}\]%{SPACE}%{LOGLEVEL:level}`. The rest will be done with the *logstash.conf*.
1. Point to [https://github.com/elastic/ecs](https://github.com/elastic/ecs) for the naming conventions.
1. Show the Data Visualizer in Machine Learning by uploading the LOG file. The output is actually quite good already,
   but we are sticking with our manual rules for now.
1. Find the log statements in Kibana's Discover view for the *parse* index.
1. Show the pipeline in Kibana's Monitoring view as well as the other components in Monitoring.
1. How many log events should we have? 40. But we have 42 entries instead. Even though 42 would generally be the perfect
   number, here it's not.
1. See the `_grokparsefailure` in the tag field. Enable the multiline rules in Filebeat. It should automatically
   refresh and when you run the application again it should now only collect 40 events.
1. Show that this is working as expected now and drill down to the errors to see which emoji we are logging.
1. Create a vertical bar chart visualization on the `level` field. Further break it down into `session`.


### Send

1. Run the sunshine case and show the data in the *send* index.
1. Stop Logstash with `docker-compose stop logstash`.
1. Run the application and restart Logstash after that again with `docker-compose start logstash`.
1. Show the missing data for example by comparing it to the results in the *parse* index, which will be populated once
   Filebeat can reach Logstash again.
1. Finally, you would need to rename the fields to match ECS in a Logstash filter.


### Structure

1. Run the application and show the data in the *structure* index.
1. Show the Logback configuration for JSON, since it is a little more complicated than the others.

