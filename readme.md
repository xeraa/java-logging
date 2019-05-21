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

* Build the Java application: `$ gradle build`
* Bring up the Elastic Stack: `$ docker-compose up --build`
* Rerun the Java application if necessary: `$ docker restart <ID of the Java app>`
* Remove the Elastic Stack (and its volumes): `$ docker-compose down -v`


## Demo

1. Take a look at the code — which pattern are we building with log statements here?


### Parse

1. Copy a log line and start parsing it with the Grok Debugger in Kibana, for example with the pattern
   `^\[%{TIMESTAMP_ISO8601:timestamp}\]%{SPACE}%{LOGLEVEL:level}` — show
   [https://github.com/logstash-plugins/logstash-patterns-core/blob/master/patterns/grok-patterns](https://github.com/logstash-plugins/logstash-patterns-core/blob/master/patterns/grok-patterns)
   to get started. The rest of the pattern can be copied from *logstash.conf*.
1. Point to [https://github.com/elastic/ecs](https://github.com/elastic/ecs) for the naming conventions.
1. Show the Data Visualizer in Machine Learning by uploading the LOG file. The output is actually quite good already,
   but we are sticking to our manual rules for now.
1. Find the log statements in Kibana's Discover view for the *parse* index.
1. Show the pipeline in Kibana's Monitoring view as well as the other components in Monitoring.
1. How many log events should we have? 40. But we have 42 entries instead. Even though 42 would generally be the perfect
   number, here it's not.
1. See the `_grokparsefailure` in the tag field. Enable the multiline rules in Filebeat. It should automatically
   refresh and when you run the application again it should now only collect 40 events.
1. Show that this is working as expected now and drill down to the errors to see which emoji we are logging.
1. Create a vertical bar chart visualization on the `level` field. Further break it down into `session`.


### Send

1. Show that the logs are missing from the first run, since no connection to Logstash had been established yet.
1. Rerun the application and see that it is working now. And we have already seen the main downside of this approach.
1. Finally, you would need to rename the fields to match ECS in a Logstash filter.


### Structure

1. Run the application and show the data in the *structure* index.
1. Show the Logback configuration for JSON, since it is a little more complicated than the others.


### Containerize

1. Show the metadata we are collecting now.
1. See why the `console` output works here, but we should turn off the colorization (otherwise the parsing breaks).
1. Turn on the ingest pipeline and show how everything is working. Then rebuild the Java app and restart Docker Compose.
1. See why we needed the grok failure rule, because of the startup error from sending to Logstash directly.
1. Filter to the right container name and point out the hinting that stops the multiline statements from being broken
up.
