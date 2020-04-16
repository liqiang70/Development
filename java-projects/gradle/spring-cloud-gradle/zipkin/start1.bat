rem java -jar zipkin-server-2.19.3-exec.jar 

mkdir logs

java -jar zipkin-server-2.19.3-exec.jar --zipkin.collector.rabbitmq.addresses=192.168.56.26 --zipkin.collector.rabbitmq.username=rabbit --zipkin.collector.rabbitmq.password=123456 --storage_type=mysql --mysql_db=zipkin --mysql_user=root --mysql_pass=123456 --mysql_host=192.168.56.22 --mysql_tcp_port=3306 > logs\zipkin-9411.log 2>&1