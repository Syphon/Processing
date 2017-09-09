Processing
==========

Syphon library for Processing 2.0 and up. Note that latest revision on master is compatible with 3.0 only. Use the [v2 branch](https://github.com/Syphon/Processing/tree/v2) for the last version compatible with 2.0.

Getting Started:
====

* Clone the repository with ```git clone --recurse-submodules https://github.com/Syphon/Processing.git``` in order to pull in the <a href="https://github.com/Syphon/Java">Java</a> repo.

* You can also pull in the Java repo after cloning the repository with ```git submodule update --init```.

* In order to update the Java sumbodule with the latest upstream changes and then commit the update, you would do:

```bash
cd java/
git pull origin master
cd ..
git add java
git commit java -m "Updated java submodule"
```

* Set the appropriate paths in the resources/build.properties file.

* Build the Processing library:

```bash
cd resources
ant
```

The library package should be created inside the distribution folder, and it should also be installed in the sketchbook folder

Acknowledgments
====

1) Syphon.framework implemented by Tom Butterworth and Anton Marini:
http://syphon.v002.info/

2) Processing Library Template:
https://github.com/processing/processing-library-template
