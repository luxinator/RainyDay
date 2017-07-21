# Spark

Spark jobs can be written in many languages, probably the most used is  [Scala](http://www.scala-lang.org). It is a general purpose programming language with many functional capabilities. 
For beginners it is probably easiest to use an IDE while building scala/spark applications. We will go through setting up the IDE. We assume that you have installed [IntelliJ](https://www.jetbrains.com/idea/) from Jetbrains and that the scala plugin is installed like explained [here](https://www.jetbrains.com/help/idea/creating-and-running-your-scala-application.html).


## Setup IntelliJ IDEA
We will go through the steps needed to setup a project that uses the locally installed spark installation, by doing this we know that the project will run with our Spark install.

- Start IntelliJ.
- Select New Project, either from the initial boot screen or through: File -> New -> Project...
- Select Scala -> SBT.
- Select a project folder and give the project a name (RainyDay). IMPORTANT: make sure the scala version is the same as the spark scala version, see bellow on how to find that out.
- Select the project in the left pane.
- Right-click on the project and select Module-Settings or press F4.
- Go to Global Libraries.
- Press the [+] sign.
- Select Java.
- Go to the directory where you unpacked Spark.
- Select the `jars` folder .
- Select all the jar files in the folder. (use the shift key)
- Press OK
- Select both modules
- Press Ok
- Press Ok
- Wait for the indexing to complete

## Test Class
In the project pane go to src -> main -> scala. Right-click on the scala folder and select new -> Scala Class
Name the class TestClass, replace the content with the code bellow. Note that we use `object` instead of `class`. 

```scala
import org.apache.spark.{SparkConf, SparkContext}

object TestScala {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("SparkTest")
    conf.setMaster("local[2]")
    val sc = new SparkContext(conf)
    println(sc)
  }
}
```
Go through code line by line.


### Run



### *Figuring out the Scala version of Spark
Go to the directory where you unpacked spark (`cd $SPARK_HOME`). Go to the `jars` folder, look for a file named: `scala-compiler-2.*.*.jar`, where the stars are the version numbers. Under linux you can do this by running `ls | grep scala-compiler`. 
In your IntelliJ project select the same version as you found in the `jars` folder. So if you found `scala-compiler-2.11.8.jar` in the jars folder, select 2.11.8 in the new project wizard. The scala version of spark might not be in the list, continue reading to find out how to install a compiler.

#### Installing scala SDK's 

You can install new scala SDK as follows:

- Make a new scala SBT project.
- Use the defaults in the wizard.
- Select the project in left pane.
- Right-click and select module-setting, or press F4.
- Under Platform Settings select Global Libraries.
- Press the [+] sign.
- Select Scala SDK
- In the popup select the version you want to download.
- Press OK and restart the Editor.

If everything worked out you can now select the scala version you want in the new project wizard.

Note: In theory the last digit of a semantic version do not have to match, but better be safe than sorry. However the middle and first digits *must* match.