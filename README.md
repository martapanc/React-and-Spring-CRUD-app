# CRUD app with React and Spring Boot

[Tutorial](https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot)


### Run API
```bash
./mvnw spring-boot:run
```

- Run in production mode:
```bash
./mvnw spring-boot:run -Pprod
```

### Run React app
```
yarn install
yarn start
```

### Create an Application in Okta

You will need to [create an OIDC Application in Okta]() to get your values to perform authentication. 

Log in to your Okta Developer account (or [sign up](https://developer.okta.com/signup/) if you don’t have an account) and navigate to **Applications** > **Add Application**. Click **Web**, click **Next**, and give the app a name you’ll remember. Specify `http://localhost:8080/login/oauth2/code/okta` as a Login redirect URI. Click **Done**, then click **Edit** to edit General Settings. Add `http://localhost:3000` and `http://localhost:8080` as Logout redirect URIs, then click **Save**. 

#### Server Configuration

Set the `issuer` and copy the `clientId` and `clientSecret` into `src/main/resources/application.yml`. 

**NOTE:** The value of `{yourOktaDomain}` should be something like `dev-123456.oktapreview.com`. Make sure you don't include `-admin` in the value!

```yaml
spring:
  profiles:
    active: @spring.profiles.active@
  security:
    oauth2:
      client:
        registration:
          okta:
            client-id: {clientId}
            client-secret: {clientSecret}
            scope: openid email profile
        provider:
          okta:
            issuer-uri: https://{yourOktaDomain}/oauth2/default
```

## Links

This example uses the following open source libraries:

* [React](https://reactjs.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)