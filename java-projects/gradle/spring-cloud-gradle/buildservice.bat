rem start cmd /C gradle :spring-cloud-gradle-service-provider:build
rem start cmd /C gradle :spring-cloud-gradle-ribbon-customer:build
rem start cmd /C gradle :spring-cloud-gradle-ribbon-r4j:build
rem start cmd /C gradle :spring-cloud-gradle-feign-customer:build
rem start cmd /C gradle :spring-cloud-gradle-task:build

rem ----------------------------------------------------------------------------------

gradle :spring-cloud-gradle-service-provider:build & ^
gradle :spring-cloud-gradle-ribbon-customer:build & ^
gradle :spring-cloud-gradle-ribbon-r4j:build & ^
gradle :spring-cloud-gradle-feign-customer:build & ^
gradle :spring-cloud-gradle-task:build
