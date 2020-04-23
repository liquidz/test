#!/bin/sh

#bb --classpath "${1}" -m bb-test-runner
echo "1: '${1}'"
echo "2: '${2}'"

time=$(date)
echo "::set-output name=time::$time"
