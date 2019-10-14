#!/bin/bash
java -jar ../target/restcms-0.0.1-SNAPSHOT.jar & echo $! > ./pid.file &