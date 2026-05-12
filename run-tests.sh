#!/usr/bin/env bash

ENVIRONMENT=$1

sbt clean -Denv="${ENVIRONMENT:=local}" "testOnly uk.gov.hmrc.api.specs.*"
