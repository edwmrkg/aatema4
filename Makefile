.PHONY: build clean run

build: tema4

run:
	java -Xmx1G Main

tema4:
	javac *.java

clean:
	rm -rf *.class
