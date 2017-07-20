
# RainyDay: A NiFi/Spark/Solr tutorial (v0.0.1)

## Goal of this project
The goal is to get you started with modern big data tools. 
Many tutorials online only show one aspect of working with big data, our goal is to show how tools work together.

In this project we will build a wheater page called *RainyDay*, it will show the precipitation per day of a location of your choosing.  The data set we will use is freely available climate data from the *European Climate Assessment & Dataset* ([EDAC](http://www.ecad.eu/))

The steps the data has to go through are roughly as follows:

- Gather
- Transform it to an easy to handle format (JSON)
- Index it
- Disclose it as an API
- Build a web/app to show the data/query

For this we are going to use the following software packages:

- Apache NiFi (gather)
- Apache HDFS (save)
- Apache Spark (transform)
- Apache Solr (index)
- DashBoard 

Head over to the [Tutorial](tutorial_start.md) to get started.