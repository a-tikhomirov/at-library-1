# language: ru
@unit
@web
@web_check_steps.feature
Функционал: WebCheckSteps

  Предыстория: Предыстрория
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" отображается на странице
    И поле "ФИО" отображается на странице

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" отобразился на странице в течение (\d+) (?:секунд|секунды)
    И поле "ФИО" отобразился на странице в течение 5 секунд

####################################################################################

  Сценарий: ожидается исчезновение (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) "([^"]*)"
    Тогда ожидается исчезновение поля "ФИО"

####################################################################################

  Сценарий: (?:страница|блок|форма) "([^"]*)" (?:скрыт|скрыта)
    Тогда страница "BCS Брокер" скрыта

####################################################################################

  Сценарий: поле "([^"]*)" пусто
    Тогда поле "ФИО" пусто

####################################################################################

  Сценарий:  значение (?:элемента|поля) "([^"]*)" сохранено в переменную "([^"]*)"
    И в поле "ФИО" введено значение "text.fio"
    Тогда значение поля "ФИО" сохранено в переменную "fio"
    И значение переменной "fio" равно "Иванов Иван Иванович"

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" кликабельно
    Тогда поле "ФИО" кликабельно

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" кликабельно в течение (\d+) (?:секунд|секунды)
    Тогда поле "ФИО" кликабельно в течение 10 секунд

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" содержит атрибут "([^"]*)" со значением "(.*)"
    Тогда поле "ФИО" содержит атрибут "type" со значением "text"

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" содержит класс со значением "(.*)" [HARDCODING]
    Тогда поле "ФИО" содержит класс со значением "ui-form-control"

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" содержит класс со значением "(.*)" [VARIABLE]
    И установлено значение переменной "класс" равным "ui-form-control"
    Тогда поле "ФИО" содержит класс со значением "класс"

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" содержит класс со значением "(.*)" [PROPERTIES]
    Тогда поле "ФИО" содержит класс со значением "css.class.valid"

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" не содержит класс со значением "(.*)" [HARDCODING]
    Тогда поле "ФИО" не содержит класс со значением "password"


  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" не содержит класс со значением "(.*)" [VARIABLE]
    И установлено значение переменной "класс" равным "password"
    Тогда поле "ФИО" не содержит класс со значением "класс"


  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" не содержит класс со значением "(.*)" [PROPERTIES]
    Тогда поле "ФИО" не содержит класс со значением "css.class.invalid"

####################################################################################

  Сценарий: (?:поле|элемент|текст) "([^"]*)" содержит значение "(.*)" [многострочно][HARDCODING]
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" содержит текст
    """
    Иван
    """

  Сценарий: (?:поле|элемент|текст) "([^"]*)" содержит значение "(.*)" [HARDCODING]
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" содержит текст "Иван"

  Сценарий: (?:поле|элемент|текст) "([^"]*)" содержит внутренний текст "(.*)" [VARIABLE]
    И установлено значение переменной "name" равным "Иван"
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" содержит текст "name"

  Сценарий: (?:поле|элемент|текст) "([^"]*)" содержит текст "(.*)" [PROPERTIES]
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" содержит текст "text.i"

####################################################################################
  Сценарий: (?:поле|элемент|текст) "([^"]*)" не содержит значение "(.*)" [многострочно][HARDCODING]
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" не содержит текст
    """
    Николай
    """

  Сценарий: (?:поле|элемент|текст) "([^"]*)" не содержит значение "(.*)" [HARDCODING]
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" не содержит текст "Николай"

  Сценарий: (?:поле|элемент|текст) "([^"]*)" не содержит внутренний текст "(.*)" [VARIABLE]
    И установлено значение переменной "name" равным "Николай"
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" не содержит текст "name"

  Сценарий: (?:поле|элемент|текст) "([^"]*)" не содержит текст "(.*)" [PROPERTIES]
    И в поле "ФИО" введено значение "text.fio"
    Тогда поле "ФИО" не содержит текст "text.invalid.i"

####################################################################################

  Сценарий: содержимое (?:поля|элемента|текста) "([^"]*)" равно "([^"]*)" [многострочно][HARDCODING]
    И в поле "ФИО" введено значение "text.fio"
    Тогда текст поля "ФИО" равен
    """
    Иванов Иван Иванович
    """

  Сценарий: содержимое (?:поля|элемента|текста) "([^"]*)" равно "([^"]*)" [HARDCODING]
    И в поле "ФИО" введено значение "text.fio"
    Тогда текст поля "ФИО" равен "Иванов Иван Иванович"

  Сценарий: содержимое (?:поля|элемента|текста) "([^"]*)" равно "([^"]*)" [VARIABLE]
    И установлено значение переменной "fio" равным "Иванов Иван Иванович"
    И в поле "ФИО" введено значение "text.fio"
    Тогда текст поля "ФИО" равен "fio"

  Сценарий: содержимое (?:поля|элемента|текста) "([^"]*)" равно "([^"]*)" [PROPERTIES]
    И в поле "ФИО" введено значение "text.fio"
    Тогда текст поля "ФИО" равен "Иванов Иван Иванович"

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" (?:недоступна|недоступен) для нажатия
    Тогда кнопка "Открыть счет" недоступна для нажатия

####################################################################################

  Сценарий: радиокнопка "([^"]*)" выбрана
    И написание автотеста в работе
    Тогда радиокнопка "Открыть счет" выбрана

####################################################################################

  Сценарий: радиокнопка "([^"]*)" не выбрана
    И написание автотеста в работе
    Тогда радиокнопка "Открыть счет" не выбрана

####################################################################################

  Сценарий: чекбокс "([^"]*)" выбран
    И написание автотеста в работе
    Тогда чекбокс "Открыть счет" выбран

####################################################################################

  Сценарий: чекбокс "([^"]*)" не выбран
    И написание автотеста в работе
    Тогда чекбокс "Открыть счет" не выбран

####################################################################################

  Сценарий: (?:поле|элемент) "([^"]*)" (?:недоступно|недоступен) для редактирования
    И написание автотеста в работе
    Тогда поле "ФИО" недоступно для редактирования

####################################################################################

  Сценарий: открыта read-only форма
    И написание автотеста в работе
    Тогда открыта read-only форма

####################################################################################

  Сценарий: файл "(.*)" загрузился в папку /Downloads
    И написание автотеста в работе
    Тогда файл "ФАЙЛ" загрузился в папку /Downloads

####################################################################################

  Сценарий: в поле "([^"]*)" содержится (\d+) символов
    И в поле "ФИО" введено значение "text.fio"
    Тогда в поле "ФИО" содержится 20 символов

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" в блоке "([^"]*)" содержит текст "([^"]*)"
    Тогда кнопка "Трейдинг" в блоке "Навигация" содержит текст "ТРЕЙДИНГ"

####################################################################################

  Сценарий: текст (?:кнопки|ссылки|поля|чекбокса|радиокнопки|текста|элемента) "([^"]*)" в блоке "([^"]*)" равен "([^"]*)"
    Тогда текст поля "Трейдинг" в блоке "Навигация" равен "ТРЕЙДИНГ"

####################################################################################

  Сценарий: (?:кнопка|ссылка|поле|чекбокс|радиокнопка|текст|элемент) "([^"]*)" в блоке "([^"]*)" отображается на странице
    Тогда поле "Трейдинг" в блоке "Навигация" отображается на странице

####################################################################################

  Сценарий: текст (?:поля|элемента|текста) "([^"]*)" в блоке "([^"]*)" сохранен в переменную "([^"]*)"
    Тогда текст поля "Трейдинг" в блоке "Навигация" сохранен в переменную "traiding"
    И значение переменной "traiding" равно "ТРЕЙДИНГ"

####################################################################################