cd ..\build\libs && java -javaagent:D:\dev-cloud\skywalking-agent\skywalking-agent.jar -Dskywalking.agent.service_name=oauth2-auth -Dskywalking.collector.backend_service=192.168.56.29:11800 -jar -Xms128m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=512m spring-cloud-gradle-oauth2-client-user-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev --server.port=8094 --spring.cloud.consul.discovery.hostname=192.168.56.1 > spring-cloud-gradle-oauth2-client-user-8094.log 2>&1