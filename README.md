At-Library-Core
=========================

Настройка проекта
====================

Подключение репозитория:
```xml
<distributionManagement>
    <snapshotRepository>
        <id>snapshots</id>
        <name>s-cicd-artif-01.global.bcs-snapshots</name>
        <url>https://artifactory.gitlab.bcs.ru/artifactory/bcs-main-snapshots</url>
    </snapshotRepository>
    <repository>
        <id>bcs-main-releases</id>
        <url>https://artifactory.gitlab.bcs.ru/artifactory/bcs-main-releases</url>
    </repository>
</distributionManagement>
```
Подключение завимости:
```xml
<dependency>
    <groupId>ru.bcs</groupId>
    <artifactId>at-library-core</artifactId>
    <version>03.03.2019</version>
</dependency>
```

BDD библиотека
=======================
BDD библиотека шагов для тестирования на основе cucumber и selenide.
Тесты пишутся на русском языке и представляют собой пользовательские сценарии, которые могут выступать в качестве пользовательской документации на приложение.

Для написания тестового сценария достаточно подключить библиотеку и воспользоваться любым готовым шагом из ru.bcs.at.library.core.steps

Например:
```gherkin
Функционал: Страница депозитов
  Сценарий: Открытие депозита
    Допустим совершен переход на страницу "Депозиты" по ссылке "depositsUrl"
    Когда выполнено нажатие на кнопку "Открыть депозит"
    Тогда страница "Открытие депозита" загрузилась
```


application.properties
=======================
Для указания дополнительных параметров или тестовых данных создайте в своем проекте файл application.properties
в main/java/resources

Работа со страницами
====================
Для работы с элементами страницы ее необходимо задать как текущую.
Таким образом можно получить доступ к методам взаимодействия с элементами, описанным в CorePage.

Новую текущую страницу можно установить шагом
```Когда страница "<Имя страницы>" загрузилась```

Для страницы депозитов шаг может выглядеть так
```Когда страница "Депозиты" загрузилась```

Каждая страница, с которой предполагается взаимодействие, должна быть описана в классе наследующемся от CorePage.
Для страницы и ее элементов следует задать имя на русском, через аннотацию Name, чтобы искать можно было именно по русскому описанию.
Элементы страницы ищутся по локаторам, указанным в аннотации FindBy и должны иметь тип SelenideElement или List<SelenideElement>.

Пример описания страницы:
```java
    @Name("Депозиты")
    public class DepositsPage extends CorePage {

        @FindBy(css = ".deposit_open")
        @Name("Открыть депозит")
        private SelenideElement depositOpenButton;

        @FindBy(css = ".deposit_close")
        @Name("Закрыть депозит")
        private SelenideElement depositCloseButton;

        @FindBy(css = ".deposit_list")
        @Name("Список депозитов")
        private List<SelenideElement> depositList;
    }
```

Инициализация страницы
Страница инициализируется каждый раз, когда вызываются методы initialize(<Имя класса страницы>.class)

Пример инициализации страницы "Депозиты":
```java
DepositsPage page = (DepositsPage) getCurrentPage();
CoreScenario.setCurrentPage(page.initialize().appeared());
```

Пример получения конкретной страницы:
```java
DepositsPage page = CoreScenario.getPage(DepositsPage.class);
```

Другой способ работы с методами страницы - это использование CoreScenario.withPage
Пример использования: 
```java
withPage(TestPage.class, page -> { some actions with TestPage methods});
```

Для страницы инициализируется карта ее элементов - это те поля, что помечены аннотацией Name.
Кроме того, осуществляется проверка, что загружена требуемая страница.
Страница считается загруженной корректно, если за отведенное по умолчанию время были загружены основные ее элементы.
Основными элементами являются поля класса страницы с аннотацией Name, но без аннотации Optional.
Аннотация Optional указывает на то, что элемент является не обязательным для принятия решения о загрузке страницы.
Например, если на странице есть список, который раскрывается после нажатия не него, т.е. видим не сразу после загрузки страницы,
его можно пометить как Optional.
Реализована возможность управления временем ожидания появления элемента на странице.
Чтобы установить timeout, отличный от базового, нужно добавить в application.properties строку
waitingAppearTimeout=150000

Доступ к элементам страницы
============================
Данные строки позволяют по имени элемента найти его в карте элементов текущей страницы.

```java
CoreScenario.getCurrentPage().getElement("Открыть депозит")
CoreScenario.getCurrentPage().getElementsList("Список депозитов")
 ```
Блоки на странице
============================
Реализована возможность описывать блоки на странице (Page Element)
Например:
```java
@FindBy(className = "header")
@Name("Шапка страницы")
public HeaderBlock header;
```
При загрузке страницы будут учитываться элементы, описанные в блоке

Работа с REST запросами
=======================

В библиотеке реализована возможность отправки REST запросов и сохранения ответа в переменную.

Поддерживаются следующие типы запросов: GET, POST, PUT, DELETE.
   ```gherkin
    Когда выполнен POST запрос на URL "url.token" с headers и parameters из таблицы. Полученный ответ сохранен в переменную "token_response"
      | HEADER         | Content-Type | application/x-www-form-urlencoded |
      | FORM_PARAMETER | username     | ОСКО.login                        |
      | FORM_PARAMETER | password     | ОСКО.password                     |
      | FORM_PARAMETER | grant_type   | password                          |
      | FORM_PARAMETER | client_id    | ef-front                          |
  ```
В таблице переменных поддерживаются типы: header, parameter, body
Для body-параметра сейчас поддерживается как работа с телом запроса, хранящимся в папке restBodies, так и с указанием текста body в самом шаге в соответствующей ячейке
Значения параметров таблицы и частей url можно указывать в application.properties

Отображение в отчете справочной информации
============================================

Для того, чтобы в отчете появился блок Output с информацией, полезной для анализа отчета, можно воспользоваться следующим методом
 ```java
CoreScenario.write("Текущий URL = " + currentUrl + " \nОжидаемый URL = " + expectedUrl);
 ```

Проверка логического выражения
===============================
У нас есть шаг, который например может выглядеть так:
 ```java
Тогда верно что "amountToPay == amountMonthly + penalty + 100"
 ```
Важно отметить, что равенство проверяется использованием операнда "==", неравенство, как "!="

Использование переменных
=========================
Иногда есть необходимость использовать значения из одного шага в последующих.
Для этого реализовано хранилище переменных в CoreScenario.
Для сохранения/изъятия переменных используются методы setVar/getVar.

Сохранение переменной в хранилище:
```
CoreScenario.setVar(<имя переменной>, <значение переменной>);
```

Получение значения переменной из хранилища:
```
CoreScenario.getVar(<имя переменной>)
```


Краткое описание главных классов
=================================

```java
ru.bcs.at.library.core.cucumber.api.CoreEnvironment
```
Используется для хранения страниц и переменных внутри сценария
scenario - Сценарий из Cucumber.api, с которым связана среда

```java
ru.bcs.at.library.core.cucumber.api.CorePage
```
Класс для реализации паттерна PageObject. Тут описаны основные методы взаимодействия с элементами страницы

```java
ru.bcs.at.library.core.cucumber.api.CoreScenario
```
Позволяет заполнить хранилище переменных, существующее в рамках одного сценария, значениями и читать эти значения при необходимости.

```java
ru.bcs.at.library.core.steps.ApiSteps
```
Шаги для тестирования API, доступные по умолчанию в каждом новом проекте

```java
ru.bcs.at.library.core.steps.WebSteps
```
Шаги для тестирования UI, доступные по умолчанию в каждом новом проекте

```java
ru.bcs.at.library.core.setup.InitialSetupSteps
```
Хуки предустановок, где происходит создание, закрытие браузера, получение скриншотов


Подключение плагинов:
====================

Настроенный для UTF-8 плагин компиляции:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.0</version>
    <configuration>
        <encoding>UTF-8</encoding>
        <source>1.8</source>
        <target>1.8</target>
    </configuration>
</plugin>
```
Запускает тесты и генерирует отчёты по результатам их выполнения:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.1</version>
    <configuration>
        <forkCount>5</forkCount>
        <reuseForks>true</reuseForks>
        <includes>
            <include>**/Parallel*IT.class</include>
        </includes>
        <testFailureIgnore>true</testFailureIgnore>
        <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.1/aspectjweaver-1.9.1.jar"
            -Dcucumber.options="
            --plugin io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"
        </argLine>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.1</version>
        </dependency>
    </dependencies>
</plugin>
```
Для просмотра allure отчетов:
```xml
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.10.0</version>
    <configuration>
        <reportVersion>2.9.0</reportVersion>
        <resultsDirectory>allure-results</resultsDirectory>
    </configuration>
</plugin>

```