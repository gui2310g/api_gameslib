
# Api - Gameslib

This project is a back-end side from project GamesLib, it has various funcionalities like create users and games, login with authentication, create games submodels like genres and publishers and adding / remove games from a user etc

## Tools used

- **Java 21**
- **Spring Boot 3.3**
- **Spring Security**
- **Maven 21**
- **Hibernate**
- **PostgreSQL**
- **JWT**
- **Junit**
- **Lombok**
- **Swagger OpenAPI 2.1.0**


## Instalation

```bash
  git clone https://github.com/gui2310g/api_gameslib.git
  cd api_gameslib
```

After that, create your application.properties file with your hibernate/postgreSQL configuration and your jwt private and public files

## Endpoints

For a better experience use swagger to test these endpoints.

### Auth
#### Returns a logged account
```http
  POST /auth/login
```

### Users
#### Returns a created user

```http
  POST /users/create
```
#### Returns all users

```http
  GET /users/findAll
```

#### Returns a selected user by id
```http
  GET /users/find/{id}
```
| Parameter | Type      | Description                                        |
|:----------|:----------|:---------------------------------------------------|
| `id`      | `integer` | **Obrigatory**. The user id who you want to return |

#### Returns a updated user (email / password / username by user choice)

```http
  PUT /users/update
```

***Obs: Is necessary a token authentication to use this endpoint!***

#### Returns a deleted user

```http
  DELETE /users/delete
```
***Obs: Is necessary a token authentication to use this endpoint!***

#### Returns an added game in user wishlist

```http
  POST /users/wishlist/add/{gameId}
```

***Obs: Is necessary a token authentication to use this endpoint!***

| Parameter    | Type        | Description                                     |
|:-------------|:------------|:------------------------------------------------|
| `gameId`     | `integer`   | **Obrigatory**. The game id who you want to add |


#### Returns a removed game in user wishlist

```http
  DELETE /users/wishlist/delete/{gameId}
```

***Obs: Is necessary a token authentication to use this endpoint!***

| Parameter    | Type        | Description                                        |
|:-------------|:------------|:---------------------------------------------------|
| `gameId`     | `integer`   | **Obrigatory**. The game id who you want to remove |


### Games

#### Returns an added game

```http
  POST /games/add
```

#### Returns all games

```http
  GET /games/findAll?page={page}&size={size}
```

| Parameter    | Type        | Description                                        |
|:-------------|:------------|:---------------------------------------------------|
| `page`       | `integer`   | **Obrigatory**. the number of pages for pagination |
| `size`       | `integer`   | **Obrigatory**. the number of games by page        |

#### Returns a selected user by id

```http
  GET /games/find/{id}
```

| Parameter    | Type        | Description                                        |
|:-------------|:------------|:---------------------------------------------------|
| `Id`         | `integer`   | **Obrigatory**. The game id who you want to return |


#### Returns a searched game

```http
  GET /games/search?name=example
```
| Parameter   | Type     | Description                                          |
|:------------|:---------|:-----------------------------------------------------|
| `name`      | `String` | **Obrigatory**. The game name who you want to search |

#### Returns selected games by platform

```http
  GET /games/search/platforms/{platformsId}
```

| Parameter      | Type       | Description                                            |
|:---------------|:-----------|:-------------------------------------------------------|
| `platformsId`  | `Integer`  | **Obrigatory**. The platform id who you want to return |


#### Returns selected games by publisher

```http
  GET /games/search/publishers/{publishersId}
```

| Parameter      | Type      | Description                                             |
|:---------------|:----------|:--------------------------------------------------------|
| `publishersId` | `Integer` | **Obrigatory**. The publisher id who you want to return |


### Genres

#### Returns an added genre

```http
  POST /genres/add
```

#### Returns all genres

```http
  GET /genres/findAll?page={page}&size={size}
```

| Parameter    | Type        | Description                                        |
|:-------------|:------------|:---------------------------------------------------|
| `page`       | `integer`   | **Obrigatory**. the number of pages for pagination |
| `size`       | `integer`   | **Obrigatory**. the number of genres by page       |


#### Returns an added genre in a specific game

```http
  POST /genres/add/{gameId}/{genresId}
```
| Parameter  | Type      | Description                                                |
|:-----------|:----------|:-----------------------------------------------------------|
| `gameId`   | `integer` | **Obrigatory**. the game Id who you want to add the genre  |
| `genresId` | `integer` | **Obrigatory**. the genre id who you want to add in a game |


#### Returns all genres by a specific game

```http
  POST /genres/game/{gameId}
```
| Parameter  | Type      | Description                                                |
|:-----------|:----------|:-----------------------------------------------------------|
| `gameId`   | `integer` | **Obrigatory**. the game Id who you want to return the genre  |


### Ratings

#### Returns an added rating

```http
  POST /ratings/add
```

#### Returns all ratings

```http
  GET /ratings/findAll
```

#### Returns an added rating in a specific game

```http
  POST /ratings/add/{gameId}/{EsrbRatingsId}
```

| Parameter       | Type      | Description                                                 |
|:----------------|:----------|:------------------------------------------------------------|
| `gameId`        | `integer` | **Obrigatory**. the game Id who you want to add the rating  |
| `EsrbRatingsId` | `integer` | **Obrigatory**. the rating id who you want to add in a game |

#### Returns a rating in a specific game

```http
  POST /ratings/game/{gameId}
```

| Parameter       | Type      | Description                                                 |
|:----------------|:----------|:------------------------------------------------------------|
| `gameId`        | `integer` | **Obrigatory**. the game Id who you want to return the rating  |


### Platforms

#### Returns an added platform

```http
  POST /platforms/add
```

#### Returns all platforms

```http
  GET /platforms/findAll?page={page}&size={size}
```

| Parameter    | Type         | Description                                        |
|:-------------|:-------------|:---------------------------------------------------|
| `page`       | `integer`    | **Obrigatory**. the number of pages for pagination |
| `size`       | `integer`    | **Obrigatory**. the number of platforms by page    |


#### Returns an added platform in a specific game

```http
  POST /platforms/add/{gameId}/{platformsId}
```

| Parameter     | Type      | Description                                                   |
|:--------------|:----------|:--------------------------------------------------------------|
| `gameId`      | `integer` | **Obrigatory**. the game Id who you want to add the platform  |
| `platformsId` | `integer` | **Obrigatory**. the platform id who you want to add in a game |

#### Returns all platforms by a specific game
```http
  GET /platforms/game/{gameId}
```

| Parameter    | Type         | Description                                        |
|:-------------|:-------------|:---------------------------------------------------|
| `gameId`       | `integer`    | **Obrigatory**. the game Id who you want to return the platforms |


### Screenshots

#### Returns an added screenshot

```http
  POST /screenshots/add
```

#### Returns all screenshots

```http
  GET /screenshots/findAll
```

#### Returns an added screenshot in a specific game

```http
  POST /screenshots/add/{gameId}/{screenshotId}
```
| Parameter       | Type      | Description                                                     |
|:----------------|:----------|:----------------------------------------------------------------|
| `gameId`        | `integer` | **Obrigatory**. the game Id who you want to add the screenshot  |
| `screenshotsId` | `integer` | **Obrigatory**. the screenshot id who you want to add in a game |

#### Returns all screenshots by a specific game
```http
  GET /screenshots/game/{gameId}
```

| Parameter    | Type         | Description                                        |
|:-------------|:-------------|:---------------------------------------------------|
| `gameId`       | `integer`    | **Obrigatory**. the game Id who you want to return the screenshots |


## Contact

if you wanna give a feedback or send questions, here is my contacts

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guilherme-campo-873890255/)
[![Gmail](https://img.shields.io/badge/Gmail-333333?style=for-the-badge&logo=gmail&logoColor=red)](mailto:fontesguilherme57@gmail.com)
