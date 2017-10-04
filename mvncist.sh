#!/bin/bash
#  maven command that executes a clean install and skips the tests and exclude the rest-web project
mvn clean install -DskipTests=true -pl "!resource-bundles-rest-web"