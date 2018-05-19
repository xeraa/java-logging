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
