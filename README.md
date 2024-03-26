
# Playtopia

Playtopia is a Java Spring Framework project currently under development. It operates on a PostgreSQL database and serves as an online game shop. Here are the planned features.

### Completed features:


1. Admins have special privileges, including adding new games, deleting existing ones, and updating game details. (login and password to admin account is 'admin')

2. Registration and logging in system created with Spring Security.

3. Users can view specific details about each game.

4. Users can browse a paginated list of all available games and filter them according to their preferences.

5. Users can change their data and delete account after registration in profile details page.

6. Users have the ability to delete and add games to their shopping basket.

7. Users can complete purchases using a straightforward ordering system implementation.

8. Users can see their order history.


# How to run 

1. Navigate to project folder in CMD
2. Set your database password and username (default: postgres)
```
SET DB_USERNAME=your_username
SET DB_PASSWORD=your_password

docker-compose up -d
```
3. Add test data to project
```
docker exec -i playtopia-db psql -U %DB_USERNAME% -d playtopia -f /docker-entrypoint-initdb.d/02-data.sql

```
4. If you want to access container database:
```
docker exec -it playtopia-db psql -U %DB_USERNAME% playtopia

```
5. Open in your browser
```
http://localhost:8080/games
```
