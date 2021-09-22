#!/bin/sh
./stop.sh

DOCKER=''
if command -v docker &> /dev/null
then
  DOCKER='docker'
fi
if command -v podman &> /dev/null
then
  DOCKER='podman'
fi
if [ -z "$DOCKER" ]
then
  echo "Could not find docker or podman. Aborting"
  exit 1
fi

mvn package -DskipTests
$DOCKER build -f src/main/docker/Dockerfile.jvm -t optaplanner/school-timetabling-jvm .
$DOCKER build . -t prometheus-ui
$DOCKER run --rm -dt -p 8080:8080 optaplanner/school-timetabling-jvm
$DOCKER run --rm -dt --network host -t prometheus-ui
$DOCKER run --rm -dt --network host -t grafana/grafana