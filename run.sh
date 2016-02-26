#!/bin/bash

mvn install
java -jar target/sortable-assignment-1.0-SNAPSHOT-jar-with-dependencies.jar products.txt listings.txt > results.txt
