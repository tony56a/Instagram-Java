Instagram for Java
==============
Java wraper for Instagram's API

## Authentication
 Firstly, build the authorization URL
```java
InstagramAuthentication auth =  new InstagramAuthentication();
String authUrl = auth.setRedirectUri("your_redirect_url")
  			 	 .setClientSecret("your_app_secrect")
  			 	 .setClientId("your_cliend_id")
  			 	 .getAuthorizationUri();
```
 After the user has authorized the app, begin the session by passing the code given in the callback URL
```java
InstagramSession session = auth.build("code");
```
 And your off
```java 
 session.searchUsersByName("instagram").get(0).getId();
``` 
## Endpoints

### Get basic information about a user 
```java
  //Endpoint: GET /users/{user_id}
  User user = session.getUserById(3);
```

### See the authenticated user's feed
Results are paginated, so the required page must also be indicated. The page number is a 1-based index.
```java
  //Endpoint: GET /users/self/feed
  List<Media> user = session.getFeed(1); //first page
```

### Get the most recent media published by a user.
Results are paginated, so the required page must also be indicated. The page number is a 1-based index.
```java
  //Endpoint: GET /users/{user_id}/media/recent
  int userId = 3;
  int pageNumber = 1;
  List<Media> recentMedia = session.getRecentPublishedMedia(userId, pageNumber);
```
## About me
* Email : juniorsola@yahoo.com
* LinkedIn : http://ca.linkedin.com/pub/sola-ogunsakin/45/a5/5a0/
* Twitter : @SolaOgunsakin
