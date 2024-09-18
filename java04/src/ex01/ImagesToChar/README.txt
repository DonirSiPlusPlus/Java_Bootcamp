- Сборка и копирование исходников в директорию target:
javac -d ./target src/java/edu/school21/printer/*/*.java && cp -R ./src/resources ./target

- Добавление зархивированого проекта в директорию target:
jar -cfmv ./target/images-to-char-printer.jar src/manifest.txt -C ./target ./edu -C ./src ./resources

При запуске необходимио указать два аргумента в параметрах (два символа), пример:
java -jar target/images-to-char-printer.jar . O
