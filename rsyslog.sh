#!/bin/ash

# Start rsyslogd
/usr/sbin/rsyslogd

# Keep creating log messages forever
COUNTER = 0
while true
do sleep 5
  $((COUNTER++))
  echo "This is syslog message $COUNTER" | logger
done
