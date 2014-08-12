
### To Download

```
git clone -o origin https://github.com/andrewsears/shoppingcart.git 
cd shoppingcart
```

### To Start

```
mvn clean install
java -jar target/shoppingcart.jar
```
or
```
mvn clean install
mvn jetty:run
```
or
```
mvn clean install jetty:run
```

And it should end with:
```
[INFO] Started Jetty Server
```

Stop it with `Cmd-c` on Macs or `Ctrl-c` on Windows.

### To Tail the Log

In another terminal window, type in the following commands:
```
cd shoppingcart
tail -f shoppingcart.log
```

Stop it with `Cmd-c` on Macs or `Ctrl-c` on Windows.