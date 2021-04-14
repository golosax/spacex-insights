# spacex-insights

Application makes several REST calls to ```https://api.spacexdata.com/``` and provides aggregated data in a browser view or console 

**Prerequisites:** JDK 15, Node.js 14+ (recommended)

## How to start

### backend
- run ```mvn clean install``` in project directory
- exectuce ```java -jar {projectFile.jar}```
>Application cat print output to console if application is run with ```--print_to_console``` parameter

### frontend

- execute ```npm install ``` from the webapp directory
- run ```npm start```
- application can be accessible by ```http://localhost:3000/rockets```

## Tips

API schemas and documenation can be seen here - https://github.com/r-spacex/SpaceX-API/tree/master/docs/v4