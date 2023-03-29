#!/bin/bash
cd /opt/fluent-bit/bin
./fluent-bit -c fluentbit.conf > fluentbit.log 2>&1 &

cd /usr/local
java -jar \
	-Dspring.data.cassandra.contact-points=cassandra \
	-Dspring.data.cassandra.password=cassandra \
	-Dspring.data.cassandra.username=cassandra \
	-Dspring.kafka.bootstrap-servers=kafka-service:9094 \
	sys-kilo-doc-aggregator-0.0.1.jar