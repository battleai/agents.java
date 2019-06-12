# agents.java

A simple random java agent to get started.

Requirements 
 * java 1.8+ 
 * maven 3

Usage

 * `mvn clean package`
 * `java -jar target/agents-1.0.0-SNAPSHOT.jar`

If your localhost is not reachable from the internet you can use

 * `ssh -R 0:localhost:5001 serveo.net` to get a publicly accessible host:port.
 
For instance

```
$ ssh -R 0:localhost:5001 serveo.net
Allocated port 38234 for remote forward to localhost:5001
Forwarding TCP connections from serveo.net:38234
```

Means requests to  `serveo.net:38234` is routed to your `localhost:5001` where your agent is listening.

Happy AI'ing :)
