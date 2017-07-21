# Scala - Data Proccessing



### Data Structure EDAC
Take a look at the data we downloaded from the EDAC. It contains ASCII text files. 

``` bash
elements.txt
metadata.txt
RR_SOUID000000.txt
RR_SOUID000001.txt
RR_SOUID000002.txt
.
.
.

```
Try to understand what the files mean and how they are structured. 
These are fixed width ASCII files, a common but old data format. A quick explination about these files can be found [here](http://www.softinterface.com/Convert-XLS/Features/Fixed-Width-Text-File-Definition.htm)

We are going to use scala to transform the ASCII data to JSON format, the JSON will then be pushed to solr. Every line in `sources.txt` will be transformed to the following:

```JSON
{
	"sourceId" 			: [SOUID],
	"name" 				: [SOUNAME],
	// Country code (ISO3116 country)
	"countryCode" 		: [CN], codes)
	// Degrees:minutes:seconds (+: North, -: South)
	"latitude" 			: [LAT], 
	// Degrees:minutes:seconds (+: East, -: West)
	"longitude"			: [LON], 
	// Station elevation in meters
	"height"			: [HGHT],
	"elementId"			: [ELEI],
	"beginDate"			: [BEGIN],
	"eindDate" 			: [END],
	"participantId"		: [PARID],
	"participantName"	: [PARNAME]
}
```
Where the values in square brackets will be replaced by the values from the ASCII files.

Every line from every `RR_0000000.txt` file will be placed in the following JSON:
```JSON
{
	"stationId"		: [STAID],
	"sourceId" 		: [SOUID],
	"date"			: [DATE],
	//precipitation amount in 0.1 mm
	"precipitation"	: [RR],
	// Will be filled one of the following strings: Valid, suspect, missing
	"quality"		: [Q_RR]
}
```


### Code

