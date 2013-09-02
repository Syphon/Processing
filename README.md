Processing
==========

Syphon Implementation for Processing 2.0

In order to build the library, you need to clone the Java submodule, which contains the JSyphon jar and JNI native libraries.

Submodules can can a bit tricky to use, below you have a couple of references that can be useful:

Chapter in Git Pro book about submodules: http://git-scm.com/book/en/Git-Tools-Submodules

Submodule cheat sheet: http://blog.jacius.info/git-submodule-cheat-sheet/

Submodules in EGit:  http://wiki.eclipse.org/EGit/User_Guide#Working_with_Submodules

For example, in order to update the java sumbodule with the latest upstream changes and then commit the update, you would do:

```bash
cd Processing_2_0/java/
git pull origin master
cd ..
git commit java -m "Updated java submodule"
```

ACKNOWLEDGMENTS

1) Syphon.framework implemented by Tom Butterworth and Anton Marini:
http://syphon.v002.info/

2) Processing Library Template:
https://github.com/processing/processing-library-template