CC=gcc

CFLAGS=-std=c99 -c -Wall -Wextra -g

HEADERS=functions.h

OBJECTS=main.o board.o gameplay.o

EXECUTABLE=frigate

all: $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(OBJECTS) -o $(EXECUTABLE)

%.o: %.c $(HEADERS)
	$(CC) $(CFLAGS) -o $@ $<

clean:
	rm -rf *.o $(EXECUTABLE)
