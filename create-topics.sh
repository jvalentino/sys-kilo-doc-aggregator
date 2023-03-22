#!/bin/sh
kafka-topics --bootstrap-server localhost:9092 \
  --create \
  --topic doc \
  --partitions 10 \
  --if-not-exists
