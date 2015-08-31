Processing
==========

Syphon library for Processing 2.0 and up. Note that latest revision on master is compatible with 3.0 only. Use the [v2 branch](https://github.com/Syphon/Processing/tree/v2) for the last version compatible with 2.0.

Getting Started:
====

* After cloning the repository, use ```git submodule update --init``` to pull in the <a href="https://github.com/Syphon/Java">Java</a> repo.

* Set the appropriate paths in the resources/build.properties file.

* For example, in order to update the Java sumbodule with the latest upstream changes and then commit the update, you would do:

```bash
cd java/
git pull origin master
cd ..
git add java
git commit java -m "Updated java submodule"
```

Acknowledgments
====

1) Syphon.framework implemented by Tom Butterworth and Anton Marini:
http://syphon.v002.info/

2) Processing Library Template:
https://github.com/processing/processing-library-template
