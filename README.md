# clickup_sdk

## Инструкция по подключению для среды разработки: 

1. Склонировать/Обновить проект на своей машине 
2. Прописать `mvn clean install` для импорта проекта в локальный репозиторий Maven
3. Прописать зависимость в pom.xml:
```
<dependency>
   <groupId>ru.egartech</groupId>
   <artifactId>click-up-sdk</artifactId>
   <version>0.0.1-SNAPSHOT</version>
</dependency>
```
4. Прописать аннотацию `@ComponentScan(basePackages = "ru.egartech.*")` над `Application.class`