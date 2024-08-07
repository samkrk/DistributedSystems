# Variables
JAVAC = javac
JAVA = java

# Directory paths
SRC_DIR = .
BIN_DIR = bin

# Classes
SERVER = CalculatorServer
CLIENT = CalculatorClient
MULT_CLIENTS = ConcurrentCalculatorClient

# Compile Java sources
.PHONY: all
all: clean compile

# Compile .java files to .class files
compile:
	$(JAVAC) -d $(BIN_DIR) $(SRC_DIR)/*.java

# Clean compiled files
clean:
	rm -rf $(BIN_DIR)

# Run the RMI server
.PHONY: server
server:
	$(JAVA) -cp $(BIN_DIR) $(SERVER)

# Run the RMI client
.PHONY: client
client:
	$(JAVA) -cp $(BIN_DIR) $(CLIENT)

# Run the RMI clients
.PHONY: test
test:
	$(JAVA) -cp $(BIN_DIR) $(MULT_CLIENTS)