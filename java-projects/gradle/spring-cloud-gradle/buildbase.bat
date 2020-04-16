rem start cmd /C gradle :spring-cloud-gradle-spring-admin:build
rem start cmd /C gradle :spring-cloud-gradle-hystrix-dashboard:build
rem start cmd /C gradle :spring-cloud-gradle-turbine:build

rem start cmd /C gradle :spring-cloud-gradle-config:build
rem start cmd /C gradle :spring-cloud-gradle-zuul:build
rem start cmd /C gradle :spring-cloud-gradle-gateway:build
rem start cmd /C gradle :spring-cloud-gradle-oauth2-auth:build
rem start cmd /C gradle :spring-cloud-gradle-oauth2-client-user:build

rem ----------------------------------------------------------------------------------

gradle :spring-cloud-gradle-eureka:build & ^
gradle :spring-cloud-gradle-spring-admin:build & ^
gradle :spring-cloud-gradle-hystrix-dashboard:build & ^
gradle :spring-cloud-gradle-turbine:build & ^
gradle :spring-cloud-gradle-config:build & ^
gradle :spring-cloud-gradle-zuul:build & ^
gradle :spring-cloud-gradle-gateway:build & ^
gradle :spring-cloud-gradle-oauth2-auth:build & ^
gradle :spring-cloud-gradle-oauth2-client-user:build 

