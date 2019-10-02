```bash
                     .__                  ___.   .__                 __    
  ___________________|__| ____    ____    \_ |__ |  | _____    ____ |  | __
 /  ___/\____ \_  __ \  |/    \  / ___\    | __ \|  | \__  \ _/ ___\|  |/ /
 \___ \ |  |_> >  | \/  |   |  \/ /_/  >   | \_\ \  |__/ __ \\  \___|    < 
/____  >|   __/|__|  |__|___|  /\___  / /\ |___  /____(____  /\___  >__|_ \
     \/ |__|                 \//_____/  \/     \/          \/     \/     \/
```

An opinionated, multi-module, spring boot set of modules.

* Common: Utilities.
* Logging: Easy log handling to mysql or elasticsearch.
* Security: Drop in JWT security.
* Organizations: Organization and User Management.

# Configuration

* You must specify a `JWT_SECRET` environment variable when running your application.

# Starting Backend Services
This repo comes with a `docker-compose` file to start MySQL, elasticsearch, etc..
for your development environment.

```bash
docker-compose up -d
```

# Setup
We need to create a test organization and user before being able to perform REST API calls as we need a JWT token.

```bash
docker exec -it platform-mysql mysql platform -uroot -pmysql -e "INSERT INTO organizations SET id=UNHEX(REPLACE(uuid(), '-', '')),name='default',status=0"
docker exec -it platform-mysql mysql platform -uroot -pmysql -e "INSERT INTO users SET id=UNHEX(REPLACE(uuid(), '-', '')),organization_id=(SELECT id FROM organizations),email='admin@admin.com',password='supersecret',status=0"
```

This will create a user with the email `admin@admin.com` and password `supersecret`.

# Testing Authentication

Test authentication with the following cURL command:

```bash
curl -XPOST http://localhost:18080/users/login -H 'Content-Type: application/json' -d '{"email": "admin@admin.com", "password": "supersecret"}' -vv
```
Should output the following JSON object:
```json
{"result":"ok","message":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJleHAiOjE1NzA5MDU4ODd9._pf2BEF7D_ZMy2Jz4aR_tWgW9XI_lpfCur8vHwTg48oUql6T6DKmIUhC05_TgeXutYkWc0jmBoZx4whB1OV7Jw","data":null}
```
