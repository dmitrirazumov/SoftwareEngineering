Build and Run:

git clone https://github.com/oddishwolf/SoftwareEngineering.git
gradle bootRun
curl http://localhost:8080/?year=1998
/-or-/
curl http://localhost:8080/?currentDate=10042005

Examples:

request: curl http://localhost:8080/?year=1998
response: {"errorCode":200,"dataMessage":"13/09/1998"}

request: curl http://localhost:8080/?currentDate=25042000
response: {"errorCode":200,"dataMessage":"140"}
    
request: curl http://localhost:8080?year=0  
response: {"errorCode":200, "dataMessage":"INCORRECT INPUT"}