#!/bin/bash
cd /opt/fluent-bit/bin
./fluent-bit -c fluentbit.conf > fluentbit.log 2>&1 &

cd /usr/local
java -jar \
	-Dspring.data.cassandra.contact-points=cassandra \
	-Dkafka.bootstrap-servers=kafka:9092 \
	sys-kilo-doc-rest-0.0.1.jar