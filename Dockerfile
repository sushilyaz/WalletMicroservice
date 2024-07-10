# Используйте официальный образ OpenJDK в качестве базового образа
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файл JAR в контейнер
COPY build/libs/WalletMicroservice-0.0.1-SNAPSHOT.jar app.jar

# Экспонируем порт, на котором будет работать приложение
EXPOSE 8080

# Определяем команду запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
