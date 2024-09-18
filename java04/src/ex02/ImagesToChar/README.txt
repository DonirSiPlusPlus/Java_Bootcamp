- Сборка и копирование исходников в директорию target:
javac -cp "lib/jcommander-1.83.jar:lib/JColor-5.5.1.jar" -d ./target src/java/edu/school21/printer/*/*.java &&
cd ./target &&
jar -xf ../lib/jcommander-1.83.jar &&
jar -xf ../lib/JColor-5.5.1.jar && cd .. &&
cp -R ./src/resources ./target

- Добавление зархивированого проекта в директорию target:
jar -cfmv ./target/images-to-char-printer.jar src/manifest.txt -C ./target .

При запуске необходимио указать два аргумента в параметрах, пример:
java -jar target/images-to-char-printer.jar --white=RED --black=GREEN
