#!/bin/bash
#  maven command that executes a clean deploy and skips the rest-web project
(cd ../../..; mvn clean deploy -pl "!resource-bundles-rest-web" -Poss.sonatype.org-staged-release)