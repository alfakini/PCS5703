#!/bin/bash

# source server header
source server-header.sh

# Additional settings
configs=$( ls ${conf}/2013* )
resultPage=false;
testServerMode=false;

runServer
