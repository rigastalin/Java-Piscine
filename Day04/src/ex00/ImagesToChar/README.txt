# Delete target if it's exist
rm -rf target

#1. Create a directory
mkdir target

#2. Compile files to the 'target' directory:
javac /opt/goinfre/cflossie/Java_Bootcamp._Day04-3/src/ex00/ImagesToChar/src/java/edu/school21/printer/*/*.java -d ./target

#3. Run program:
java -classpath target ex00.ImagesToChar.src.java.edu.school21.printer.app.App . 0 /opt/goinfre/cflossie/Java_Bootcamp._Day04-3/src/ex00/ImagesToChar/it.bmp