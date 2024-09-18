- Компиляция (компилирует исходные файлы *.java в директорию target):
javac -d ./target src/java/edu/school21/printer/*/*.java

- Запуск (запуск программы Main из директории target)
java -cp ./target edu.school21.printer.app.Main

Необходимио при запуске указать три аргумента в параметрах, два символа и полный путь к изображению
Пример:
java -cp ./target edu.school21.printer.app.Main O . /Users/birdpers/it.bmp