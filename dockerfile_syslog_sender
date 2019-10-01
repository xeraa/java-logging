FROM alpine:3.10.2
RUN apk update && apk add rsyslog
COPY rsyslog.conf /etc/rsyslog.conf
COPY rsyslog.sh /sbin/run-rsyslog
ENTRYPOINT [ "/sbin/run-rsyslog" ]
