.PHONY: run 
run:
	rm -r out/
	javac -cp . -sourcepath src/ -d out/ src/*.java
	java -cp out/ Mahjong



