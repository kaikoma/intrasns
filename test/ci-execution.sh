#!/bin/bash

# Setup some colours to output with
RED=$(printf "\x1b[31;1m")
GREEN=$(printf "\x1b[32;1m")
NORMAL=$(printf "\x1b[0m")

# Move to the correct directory
BASE_DIR=`dirname $0`
echo Setting base directory to ${GREEN}$BASE_DIR${NORMAL}

# Run Play! tests
echo [${GREEN}Test Framework${NORMAL}]Starting up Sbt! tests.
sbt test
SBT_RESULT=$?

# Check if Sbt! has build issues
[ $SBT_RESULT -ne 0 ] && \
echo [${RED}FAILED${NORMAL}]Sbt! test failure - see logs. && \
exit `expr $SBT_RESULT`

# Run Jasmine tests via Karma
echo [${GREEN}Test JavaScript${NORMAL}]Starting up Karma to execute Jasmine tests.
cd ngapp
grunt test
JASMINE_RESULT=$?

# Check if Jasmine has build issues
[ $JASMINE_RESULT -ne 0 ] && \
echo [${RED}FAILED${NORMAL}]Jasmine test failure - see logs. && \
exit `expr $JASMINE_RESULT`

# Finish gracefully (should only get here if both are 0 but it is safe to check)
exit `expr $SBT_RESULT + $JASMINE_RESULT`;
