.PHONY: build clean run

build: tema1

run:
	java -Xmx1G Main

tema1:
	javac *.java

clean:
	rm -rf *.class
