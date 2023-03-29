#!/bin/bash

NAME=sys-kilo-doc-aggregator
VERSION=latest
HELM_NAME=sys-doc-aggregator

helm delete --wait $HELM_NAME || true
minikube image rm $NAME:$VERSION
rm -rf ~/.minikube/cache/images/arm64/$NAME_$VERSION || true
docker build --no-cache . -t $NAME
minikube image load $NAME:$VERSION
# minikube cache reload
