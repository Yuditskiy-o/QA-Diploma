# Дипломный проект по курсу «Тестировщик ПО»
## О проекте

Проект представляет собой комплексное автоматизированное тестирование сервиса по покупке туров через интернет-банк. Купить тур можно с помощью двух способов:
- оплата с помощью дебетовой карты
- покупка в кредит

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
- сервису платежей (Payment Gate)
- кредитному сервису (Credit Gate)

Приложение в собственной СУБД сохраняет информацию о том, каким способом был совершён платёж и успешно ли он был совершён.

## Документация

- [План автоматизации тестирования](https://github.com/Yuditskiy-o/QA-Diploma/blob/master/documents/Plan.md)
- [Отчет по итогам тестирования](https://github.com/Yuditskiy-o/QA-Diploma/blob/master/documents/Report.md)
- [Комплексный отчет по итогам всего процесса автоматизации](https://github.com/Yuditskiy-o/QA-Diploma/blob/master/documents/Summary.md)

## **Перед началом работы**
1. Загрузите на свой ПК данный репозиторий. Для этого либо воспользуйтесь командой git clone, либо в IntelliJ IDEA нажмите "Get from Version Control" и вставьте ссылку https://github.com/Yuditskiy-o/QA-Diploma в поле URL, нажмите "Clone".
![image](https://i.gyazo.com/d39a0b3f4e85db1b434e515a683d4cfd.png)
![image](https://i.gyazo.com/401bf3ea85f02b6ebd91793de9550d57.png)
2. Установите Docker Desktop или Docker Toolbox (в зависимости от вашей ОС). Ознакомьтесь с [этой](https://github.com/netology-code/aqa-homeworks/blob/aqa4/docker/installation.md) инструкцией.
3. Если установили Docker Toolbox, то запустите ярлык "Docker Quickstart Terminal" на рабочем столе, дождитесь появления вот такого окна:
![image](https://i.gyazo.com/c9c95ee6362f841dd2f22d63844404e8.png)

## **Запуск**
1. **Запускаем docker-контейнер с СУБД MySQL и PostgreSQL, а также Node.js.**

Все параметры для запуска уже есть в соответствующем файле — docker-compose.yml. Вам необходимо лишь ввести в терминале команду:
```
docker-compose up
```

---------

2. **Запускаем SUT**

Для этого открываем новую вкладку в Терминале IDEA и вводим следующую команду:
- для СУБД **MySQL**:
```java
java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar artifacts/aqa-shop.jar
```
- для СУБД **PostgreSQL**:
```java
java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar artifacts/aqa-shop.jar
```
Дожидаемся сообщения, которое будет означать готовность SUT к работе:
```java
2020-10-26 22:12:27.961  INFO 6868 --- [           main] ru.netology.shop.ShopApplication         : Started ShopApplication in 4.56 seconds (JVM running fo
r 5.038)
```

В браузере сервис будет доступен по адресу http://localhost:8080/.

---------

3. **Запускаем авто-тесты**

Для этого открываем еще одну вкладку в Терминале и вводим следующую команду:
- для СУБД **MySQL**:
```java
gradlew clean test -Ddb.url=jdbc:mysql://192.168.99.100:3306/app 
```
- для СУБД **PostgreSQL**:
```java
gradlew clean test -Ddb.url=jdbc:postgresql://192.168.99.100:5432/app
```
---------

4. **Генерируем отчет Allure по итогам тестирования, который автоматически откроется в браузере**

Используем команду:
```java
gradlew allureServe
```
После просмотра отчета останавливаем действие allureServe комбинацией клавиш **Ctrl + C**, подтверждаем закрытие клавишей **Y** и нажимаем **Enter**.

## **Если возникла необходимость перезапустить тесты**
Если нужно перезапустить тесты, либо переподключить контейнер и SUT не выходя при этом из проекта, то во вкладках терминала с запущенным контейнером 
и SUT нажмите **CTRL + C**, соответственно и контейнер, и SUT прекратят свою работу. Затем опять включаем по очереди, вводя команды из п.1 и п.2.
