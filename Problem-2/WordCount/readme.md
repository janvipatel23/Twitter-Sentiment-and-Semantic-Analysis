# Assignment-4
## Word Count program for given keywords
### Task: To return the total word count for "flu", "snow", and "cold" from the raw file data using Spark

Steps to run the project:
1. Clone the project.
2. Replace the internal master node URL with your master node internal URL in WordCount.java file.
3. Run the "mvn clean install" command to generate the jar file for the project.
4. Upload to generate jar file manually to the Spark using the Settings button on Spark shell.
5. Run the master node and slave node using following command:
    - start-master.sh
    - start-slave.sh<Internal URL for the master node>
6. Generate the zip folder containing the raw file data and upload manually on the Spark.
7. Unzip the folder on the Spark shell using following command:
         - jar -xvf<FolderName>
8. To execute the program on Spark shell use the following command:
         - spark-submit --class "<main classname>" <jar file name>

About the project dependency:
Add the spark dependency in the pom.xml file.

About the project: Following class is included in the project
1. WordCount - The class counting the required keywords in the raw file data by using flatMap, filter, and map function.

###References:

[1] “RDD Programming Guide,” RDD Programming Guide - Spark 3.2.1 Documentation. [Online]. Available: https://spark.apache.org/docs/latest/rdd-programming-guide.html. [Accessed: 10-Mar-2022].

[2] “Transferring files to linux VMS Compute Engine documentation google cloud,” Google. [Online]. Available: https://cloud.google.com/compute/docs/instances/transfer-files. [Accessed: 13-Mar-2022].