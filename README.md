This is a basic Todo RESTful web service with CRUD functionalities using Jersey RESTful web services framework. It uses MyBatis as SQL mapper and have used MySQL database in the project.

Setup:

1. Download/ clone the project into your repository.
2. Given that you already have maven installed in your computer, download all the project dependencies executing the command "mvn clean install" from terminal (Linux).
3. Start your MySQL server and from the root folder of the project execute "mysql -f -u {username} < src/tools/sql/init_db.sql" from terminal. Provide your username for MySQL server in place of {username}.
4. Start the web service by typing "mvn clean compile exec:java".

N.B. Format of the JSON object is as follows:
{
	"id" : {id},
	"title": {title},
	"isDone": {isDone}
}
