# Delete target if it's exist
rm -rf target

#1. Create a directory
mkdir target

# '-d' define *.class store folder - './target/'
# 'src/java/edu/school21/printer/*/*.java' - is path to source *.java files
javac -d ./target/ src/java/edu/school21/printer/*/*.java

# copy resources to jar folder
cp -R src/resources target/.

# 'cmf' - "create archive" "path to manifest.txt" "output file name"
# '-C ./target' - root directory for archive sources is ./target
# '.' everything in this folder will be added
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# Change privileges to file to make it executable
chmod u+x target/images-to-chars-printer.jar

# '-jar' - run jar archive
# './target/images-to-chars-printer.jar' - archive path
# '. 0' -command line arguments
java -jar target/images-to-chars-printer.jar . 0 /opt/goinfre/cflossie/Java_Bootcamp._Day04-3/src/ex01/ImagesToChar/target/resources/it.bmp