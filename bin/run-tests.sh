#!/bin/sh
CLASSPATH=test:src:classes:features

for f in lib/*.jar; do
    CLASSPATH=$CLASSPATH:$f
done

java $@ -Xmx1G -classpath $CLASSPATH clojure.main -e "(use 'circumspec.cli) (run-tests)"