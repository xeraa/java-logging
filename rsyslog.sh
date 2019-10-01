#!/bin/ash

# start rsyslogd
/usr/sbin/rsyslogd

# keep creating log messages forever
while true ; do sleep 5 ; echo "This is a log message" | logger ; done
