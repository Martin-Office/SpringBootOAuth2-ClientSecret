# Steps to reproduce issue:
1. Clone code of branch "showcase_client_secret_bug"
2. Go to SpringBootOauth2AuthorizationServer
3. Compile and run the server
3. Go to https://oauthdebugger.com/
    1. set authorize URI to http://localhost:9009/oauth2/authorize
    2. set scope to openid
    3. set client id to client
4. Take note of code_verifier
5. Click send request
6. Copy authorization code
7. Run curl with proper values from previous steps. Decoded value of Authorization: Basic Y2xpZW50OnNlY3JldCsy is client:Secret+2

    curl --request POST \
        --url http://localhost:9009/oauth2/token \
        --header 'Authorization: Basic Y2xpZW50OnNlY3JldCsy' \
        --header 'Content-Type: multipart/form-data' \
        --header 'User-Agent: insomnia/8.1.0' \
        --cookie JSESSIONID=923919ECD59E828E5C972C01E7D005E6 \
        --form redirect_uri=https://oauthdebugger.com/debug \
        --form grant_type=authorization_code \
        --form code=<copy value from step 4 result> \
        --form code_verifier=<copy value from step 3>`
---
   >  **Client secret won't be valid and refused but it must be.** 