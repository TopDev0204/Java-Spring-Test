<h1>Test project</h1>
<p>Simple Java RESTful application that manages users and tasks for those users</p>

#### Functionality:
* list/create/update/delete users via REST.
* pagination and sort of users to consider high number of users
* list/create/update/delete tasks for users via REST.
* schedule job to to check all tasks in the Database as a bonus task.
* used H2 database as a In-Memory database.
* used spring-boot framework.

#### How to start:
* please download the project.
* type "docker build -t test ."
* type "docker run -p 8080:8080 test"

#### additional task.
* To test the pagination and sort for users
```sh
curl --location --request GET 'http://localhost:8080/api/users?pageNo={page_number}&pageSize={page_size}&sortBy={column_name}'
```
* In terms of bonus task, the schedule job runs every 30 seconds so you can test easily.