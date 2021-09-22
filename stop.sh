#!/bin/sh
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

POD_ID=$($DOCKER ps -qf "ancestor=localhost/prometheus-ui")
$DOCKER kill "$POD_ID"
POD_ID=$($DOCKER ps -qf "ancestor=localhost/optaplanner/school-timetabling-jvm")
$DOCKER kill "$POD_ID"
POD_ID=$($DOCKER ps -qf "ancestor=docker.io/grafana/grafana")
$DOCKER kill "$POD_ID"
