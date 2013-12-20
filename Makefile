all:
	javac `find ./ -name '*.java'`

archive: all
	jar cf iguanacad.jar `find ./ -name '*.class'`

clean:
	rm `find ./ -name '*.class'`
#	rm -f assemble/*.class ui/*.class part/*.class sketch/*.class common/*.class slice/*.class sketch/geom/*.class
