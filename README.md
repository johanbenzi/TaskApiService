# TaskApiService

A service that is responsible for creating updating and deleting tasks for users

## Running locally:

Step 1: Build the app to fetch all dependencies
<pre>mvn clean install</pre>

Step 2: Start up the postgres image using docker compose file
<pre>docker compose up</pre>

Step 3: Run the app through your IDE or run the following command in your terminal, this will also perform db migration
using integrated liquibase
<pre>mvn spring-boot:run</pre>

Swagger URL: http://localhost:8080/swagger-ui.html

## Test Reports:

Serenity BDD test reports are available at location below
<pre> target/site/sernity/index.html </pre>

The tests are minimal and just a poc, the suite isn't finished. Only one api is tested here

## Contract testing

Stubs are generated for apis, this can be used for testing a consumer of these endpoints to test the bounded context.
The stubs are available as a jar and can be found in the target directory

## Docker

Docker image for the app can be created locally by the following command
<pre>docker build -t task-api-service --build-arg JAR_FILE=target/task-api-service-0.0.1-SNAPSHOT.jar .</pre>

## Kubernetes and templating(ytt and kapp)

For executing this you will need a configured remote k8s instance or a local k8s instance such as minikube Also you need
to install ytt and kapp which is part of the k14s stack from carvel
<pre> https://carvel.dev/</pre>

To deploy the app using kapp run the following command

<pre>kapp deploy -a task-api-service-nonprod -c -f <(deploy/k8s/tools/render -f deploy/k8s/envs/nonprod --data-value image.name="$image_name" --data-value image.tag="$image_tag") </pre>
Image name and tag is obtained from the previous docker image that was built

## Things that I would have done if there was time

* The UI part of the requirement
* Full suite of UTs and BDD ITs(at the moment the tests were created as a poc in a tdd manner)
* Persisting the database secret in a PAM solution such as Hashicorp vault.
* Extract out liquibase to a docker job to decouple the app from schema migration
