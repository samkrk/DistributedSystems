To test the java RMI, compile all files using "make all"
Then start the server using "make server"
Then for testing a single client, run "make client"
    or "make test" for multiple clients at once

Note that in CalculatorImplementation, each client is given its 
own stack upon construction, so bonus marks are satisfied.  