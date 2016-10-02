# GD-ANN (Gradient Descent on an Artificial Neural Network)
Intro to machine learning project: gradient descent on an artificial neural network

Development Platform
-----------

Project was developed using Java 8 (java-1.8.0-openjdk-amd64) in the IntelliJ IDEA Community IDE. The operating system used for development is Linux, specifically Xubuntu 16.04. However, for compiling it should not matter what platform I used or that I used the open JDK as long as you have JDK 8 on your platform.

Compiling and Running
---------
Insure you have the java development kit (JDK) 8 installed ([link](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)) installed for your operating system. 

### linux / unix

Open a terminal and change directory to the GD-ANN folder. Then do the following to compile and run:

```bash
cd src/
javac Main.java
java Main ../resources/dataSet1/train2.dat ../resources/dataSet1/test2.dat 0.3 800
```
Note the arguments:
1st argument: the path to the training set
2nd argument: the path to the test set
3rd argument: learning rate
4th argument: the number of iterations.

### Windows

After installing the JDK 8, locate and get the path for the java compiler `javac.exe`. If you installed `jdk-8u101-windows-x64.exe` from Oracles website, the the path is likely `C:\Program Files\Java\jdk1.8.0_101\bin
`.

Now, navigate to the GD-ANN folder in Windows Explorer. To open a command prompt at this location go to File > open command prompt.

Check the java version, this is what my output looks like:
```shell
C:\Users\Will\Documents\GitHub\GD-ANN>java -version
java version "1.8.0_101"
Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.101-b13, mixed mode)
```

Add the path to the JDK bin so we can run the `javac.exe` compiler.
```shell
C:\Users\Will\Documents\GitHub\GD-ANN>set path=%path%;C:\Program Files\Java\jdk1.8.0_101\bin
```

Note that setting the path here does not persist. Once you close the command prompt and open it again, the path variable will need to be set again.

Now compile and run:

```shell
C:\Users\Will\Documents\GitHub\GD-ANN>cd ./src
C:\Users\Will\Documents\GitHub\GD-ANN\src>javac Main.java
C:\Users\Will\Documents\GitHub\GD-ANN\src>java Main ../resources/dataSet1/train2.dat ../resources/dataSet1/test2.dat 0.3 800
```

Again, note the arguments:
1st argument: the path to the training set
2nd argument: the path to the test set
3rd argument: learning rate
4th argument: the number of iterations.

