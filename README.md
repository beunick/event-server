## Prerequisites
- Should have Maven, Docker and git installed
- Make sure that the port 8080 is open

## How to Launch the Server in Docker Container (For the small JSON Dataset)

- Clone this project locally
- To view the code use Eclipse or STS and create an existing project 
- Rename the JSON File to "assignment_data.json"
- Copy the JSON file and paste it in "/resource/data/" directory
- Build the project : mvn clean package
- Build the container image: docker build -t eventserver .
- Run the container image  : docker run -p 8080:8080 -t eventserver
- You can view the DataBase from this link http://localhost:8080/console

## How to Launch the Server in Docker Container (For the BIG JSON Dataset)
Using the big JSON DataSet, I had some issue running under Docker container because of memory error
I tried few solution quickly which are: 
- Passing JVM configuration file to the Docker or mounting external disk etc... I couldn't solve it
So the temporary solution is to run it without Docker Container. Here are the steps:
- Clone this project locally
- To view the code use Eclipse or STS and create an existing project 
- Rename the JSON File to "assignment_data.json"
- Copy the JSON file and paste it in "/resource/data/" directory
- Build the project : mvn clean package
- launch the JAR file: java -jar target/events-0.0.1-SNAPSHOT.jar
- You can view the DataBase from this link http://localhost:8080/console


# Client
- Once the server is running go to client repository [Event-Client](https://github.com/beunick/event-client) 

