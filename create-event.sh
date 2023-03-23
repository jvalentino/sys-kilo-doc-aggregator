#!/bin/sh
kafka-console-producer \
  --broker-list localhost:9092 \
  --topic doc \
  --property "parse.key=true" \
  --property "key.separator=:" < event.txt

