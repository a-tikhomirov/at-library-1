# language: ru
@unit
@unit123
@web
@web_action_steps.feature
Функционал: WebActionSteps

####################################################################################

  Сценарий: совершен переход на страницу "([^"]*)" по ссылке "([^"]*)" [HARDCODING]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "https://broker.ru/demo"
    И поле "ФИО" отображается на странице

  Сценарий: совершен переход на страницу "([^"]*)" по ссылке "([^"]*)" [VARIABLE]
    Когда установлено значение переменной "url_demo_page" равным "https://broker.ru/demo"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url_demo_page"
    И поле "ФИО" отображается на странице

  Сценарий: совершен переход на страницу "([^"]*)" по ссылке "([^"]*)" [PROPERTIES]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И поле "ФИО" отображается на странице

####################################################################################

  Сценарий: совершен переход на страницу "([^"]*)" в новой вкладке по ссылке "([^"]*)" [HARDCODING]
    Когда совершен переход по ссылке "url.broker"
    И совершен переход на страницу "BCS demo аккаунт" в новой вкладке по ссылке "https://broker.ru/demo"
    То поле "ФИО" отображается на странице


  Сценарий: совершен переход на страницу "([^"]*)" в новой вкладке по ссылке "([^"]*)" [VARIABLE]
    Когда установлено значение переменной "url_demo_page" равным "https://broker.ru/demo"
    И совершен переход по ссылке "url.broker"
    И совершен переход на страницу "BCS demo аккаунт" в новой вкладке по ссылке "url_demo_page"
    То поле "ФИО" отображается на странице


  Сценарий: совершен переход на страницу "([^"]*)" в новой вкладке по ссылке "([^"]*)" [PROPERTIES]
    Когда совершен переход по ссылке "url.broker"
    И совершен переход на страницу "BCS demo аккаунт" в новой вкладке по ссылке "url.broker.demo"
    То поле "ФИО" отображается на странице

####################################################################################

  Сценарий: выполнен переход на страницу "([^"]*)" после нажатия на (?:ссылку|кнопку) "([^"]*)"
    Когда совершен переход на страницу "BCS Брокер" по ссылке "url.broker"
    И выполнен переход на страницу "BCS demo аккаунт" после нажатия на кнопку "Демо-счет"
    То поле "ФИО" отображается на странице

####################################################################################

  Сценарий: (?:страница|блок|форма|вкладка) "([^"]*)" (?:загрузилась|загрузился)
    Когда совершен переход по ссылке "url.broker"
    То страница "BCS Брокер" загрузилась

####################################################################################

  Сценарий: (?:страница|блок|форма|вкладка) "([^"]*)" не (?:загрузилась|загрузился)
    Когда совершен переход по ссылке "url.broker"
    То страница "BCS demo аккаунт" не загрузилась

####################################################################################

  Сценарий: выполнено нажатие на (?:кнопку|ссылку|поле|чекбокс|радиокнопу|текст|элемент) "([^"]*)"
    Когда совершен переход на страницу "BCS Брокер" по ссылке "url.broker"
    И выполнено нажатие на кнопку "Демо-счет"
    То страница "BCS demo аккаунт" загрузилась
    То поле "ФИО" отображается на странице

####################################################################################

  Сценарий: выполнено умное нажатие на (?:кнопку|ссылку|поле|чекбокс|радиокнопу|текст|элемент) "([^"]*)"
    Когда совершен переход на страницу "BCS Брокер" по ссылке "url.broker"
    И выполнено умное нажатие на кнопку "Демо-счет"
    То страница "BCS demo аккаунт" загрузилась
    То поле "ФИО" отображается на странице

####################################################################################

  Сценарий: выполнено нажатие на клавиатуре "([^"]*)"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" набирается значение "Иванова"
    И выполнено нажатие на клавиатуре "а"
    И выполнено нажатие на клавиатуре "BACK_SPACE"
    И выполнено нажатие на клавиатуре "BACK_SPACE"
    Тогда содержимое текста "ФИО" равно "Иванов"

####################################################################################

  Сценарий: выполнено нажатие на сочетание клавиш из таблицы
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" набирается значение "Иванов"
    И выполнено нажатие на сочетание клавиш из таблицы
      | SPACE |
      | И     |
      | в     |
      | а     |
      | н     |
    Тогда содержимое текста "ФИО" равно "Иванов Иван"

####################################################################################

  Сценарий: в поле "([^"]*)" введено значение "(.*)" [однострочно] [HARDCODING]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено значение "Иванов Иван Иванович"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в поле "([^"]*)" введено значение "(.*)" [однострочно] [VARIABLE]
    Когда установлено значение переменной "fio" равным "Иванов Иван Иванович"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено значение "fio"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в поле "([^"]*)" введено значение "(.*)" [однострочно] [PROPERTIES]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено значение "text.fio"
    И значение поля "ФИО" равно "text.fio"

####################################################################################

  Сценарий: в поле "([^"]*)" введено значение "(.*)" [многострочно]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "Номер телефона" введено значение
    """
    9123456789
    """
    И значение поля "Номер телефона" равно "+7 912 345-67-89"

####################################################################################

  Сценарий: в поле "([^"]*)" набирается значение "(.*)" [однострочно] [HARDCODING]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "" посимвольно набирается значение
    И в поле "ФИО" набирается значение "Иванов Иван Иванович"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в поле "([^"]*)" набирается значение "(.*)" [однострочно] [VARIABLE]
    Когда установлено значение переменной "fio" равным "Иванов Иван Иванович"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" набирается значение "fio"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в поле "([^"]*)" набирается значение "(.*)" [однострочно] [PROPERTIES]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" набирается значение "text.fio"
    И значение поля "ФИО" равно "text.fio"

####################################################################################

  Сценарий: в поле "([^"]*)" набирается значение "(.*)" [многострочно]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "Номер телефона" набирается значение
    """
    9123456789
    """
    И значение поля "Номер телефона" равно "+7 912 345-67-89"

####################################################################################

  Сценарий: в поле "([^"]*)" посимвольно набирается значение "([^"]*)" [однострочно] [HARDCODING]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" посимвольно набирается значение "Иванов Иван Иванович"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в поле "([^"]*)" посимвольно набирается значение "([^"]*)" [однострочно] [VARIABLE]
    Когда установлено значение переменной "fio" равным "Иванов Иван Иванович"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" посимвольно набирается значение "fio"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в поле "([^"]*)" посимвольно набирается значение "([^"]*)" [однострочно] [PROPERTIES]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" посимвольно набирается значение "text.fio"
    И значение поля "ФИО" равно "text.fio"

####################################################################################

  Сценарий: в поле "([^"]*)" посимвольно набирается значение "([^"]*)" [многострочно]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "Номер телефона" посимвольно набирается значение
    """
    9123456789
    """
    И значение поля "Номер телефона" равно "+7 912 345-67-89"

####################################################################################

  Сценарий: очищено поле "([^"]*)"$
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" посимвольно набирается значение "Иванов Иван Иванович"
    И очищено поле "ФИО"
    И значение поля "ФИО" равно ""

####################################################################################

  Сценарий: выполнен ховер на (?:кнопку|ссылку|поле|чекбокс|радиокнопу|текст|элемент) "([^"]*)"
    И написание автотеста в работе
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И выполнен ховер на текст "ФИО"

####################################################################################

  Сценарий: в (?:поле|элемент) "([^"]*)" дописывается значение "(.*)" [однострочно] [HARDCODING]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено значение "Иванов "
    И в поле "ФИО" дописывается значение "Иван Иванович"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в (?:поле|элемент) "([^"]*)" дописывается значение "(.*)" [однострочно] [VARIABLE]
    Когда установлено значение переменной "fio" равным "Иван Иванович"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено значение "Иванов "
    И в поле "ФИО" дописывается значение "fio"
    И значение поля "ФИО" равно "text.fio"

  Сценарий: в (?:поле|элемент) "([^"]*)" дописывается значение "(.*)" [однострочно] [PROPERTIES]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено значение "Иванов "
    И в поле "ФИО" дописывается значение "text.io"
    И значение поля "ФИО" равно "text.fio"

####################################################################################

  Сценарий: выполнено нажатие на элемент с текстом "(.*)"
    Когда совершен переход на страницу "BCS Брокер" по ссылке "url.broker"
    И выполнено нажатие на элемент с текстом "Демо-счет"
    То страница "BCS demo аккаунт" загрузилась
    То поле "ФИО" отображается на странице

####################################################################################

  Сценарий: (?:поле|элемент) "([^"]*)" заполняется текущей датой в формате "([^"]*)"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И установлено значение переменной "текущая_дата" с текущей датой в формате "dd.MM.yyyy"
    И поле "ФИО" заполняется текущей датой в формате "dd.MM.yyyy"
    И значение поля "ФИО" равно "текущая_дата"

####################################################################################

  Сценарий: вставлено значение "([^"]*)" в (?:поле|элемент) "([^"]*)" с помощью горячих клавиш [HARDCODING]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И вставлено значение "Иванов Иван Иванович" в поле "ФИО" с помощью горячих клавиш
    И значение поля "ФИО" равно "text.fio"

  Сценарий: вставлено значение "([^"]*)" в (?:поле|элемент) "([^"]*)" с помощью горячих клавиш [VARIABLE]
    Когда установлено значение переменной "fio" равным "Иванов Иван Иванович"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И вставлено значение "fio" в поле "ФИО" с помощью горячих клавиш
    И значение поля "ФИО" равно "text.fio"

  Сценарий: вставлено значение "([^"]*)" в (?:поле|элемент) "([^"]*)" с помощью горячих клавиш [PROPERTIES]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И вставлено значение "text.fio" в поле "ФИО" с помощью горячих клавиш
    И значение поля "ФИО" равно "text.fio"

####################################################################################

  Сценарий: файл по пути "(.*)" выгрузился в поле "(.*)"
    И написание автотеста в работе
    И файл по пути "аааа" выгрузился в поле "ааааа"

####################################################################################

  Сценарий: страница прокручена до элемента "([^"]*)"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И страница прокручена до элемента "ФИО"

####################################################################################

  Сценарий: в поле "([^"]*)" введено (\d+) случайных символов на (кириллице|латинице) [кириллице]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено 10 случайных символов на кириллице
    И значение поля "ФИО" сохранено в переменную "10_символов_на_кириллице"
    И длина строки переменной "10_символов_на_кириллице" равна 10

  Сценарий: в поле "([^"]*)" введено (\d+) случайных символов на (кириллице|латинице) [латиница]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено 10 случайных символов на латинице
    И значение поля "ФИО" сохранено в переменную "10_символов_на_латинице"
    И длина строки переменной "10_символов_на_латинице" равна 10

####################################################################################

  Сценарий: в поле "([^"]*)" введено (\d+) случайных символов на (кириллице|латинице) и сохранено в переменную "([^"]*)"[ кириллице]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено 10 случайных символов на кириллице и сохранено в переменную "10_символов_на_кириллице"
    И длина строки переменной "10_символов_на_кириллице" равна 10

  Сценарий: в поле "([^"]*)" введено (\d+) случайных символов на (кириллице|латинице) и сохранено в переменную "([^"]*)"[ латиница]
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено 10 случайных символов на латинице и сохранено в переменную "10_символов_на_латинице"
    И длина строки переменной "10_символов_на_латинице" равна 10

####################################################################################

  Сценарий: в поле "([^"]*)" введено случайное число из (\d+) (?:цифр|цифры)
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено случайное число из 8 цифр
    И значение поля "ФИО" сохранено в переменную "число_из_8_цифр"
    Тогда длина строки переменной "число_из_8_цифр" равна 8

####################################################################################

  Сценарий: в поле "([^"]*)" введено случайное число из (\d+) (?:цифр|цифры) и сохранено в переменную "([^"]*)"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    И в поле "ФИО" введено случайное число из 8 цифр и сохранено в переменную "число_из_8_цифр"
    Тогда длина строки переменной "число_из_8_цифр" равна 8

####################################################################################

  Сценарий: выполнен js-скрипт "([^"]*)"
    Когда написание автотеста в работе
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    Тогда выполнен js-скрипт "в_работе"

####################################################################################

  Сценарий: страница прокручена до появления элемента "([^"]*)"
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    Тогда страница прокручена до появления элемента "ФИО"

####################################################################################

  Сценарий: страница прокручена до появления элемента с текстом "([^"]*)"
    Когда совершен переход на страницу "BCS Брокер" по ссылке "url.broker"
    Тогда страница прокручена до появления элемента с текстом "Демо-счет"

####################################################################################

  Сценарий: выполнено нажатие на кнопку "([^"]*)" и загружен файл "([^"]*)"
    Когда написание автотеста в работе
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    Тогда выполнено нажатие на кнопку "ААА" и загружен файл "BBB"

####################################################################################

  Сценарий: выполнено нажатие на (?:кнопку|ссылку|поле|чекбокс|радиокнопу|текст|элемент) "([^"]*)" в блоке "([^"]*)"
    Когда написание автотеста в работе
    Когда совершен переход на страницу "BCS Брокер" по ссылке "url.broker"
    Тогда выполнено нажатие на кнопку "Демо-счет" в блоке "Навигация"
    То страница "BCS demo аккаунт" загрузилась
    То поле "ФИО" отображается на странице

####################################################################################

  Сценарий: пользователь "([^"]*)" ввел логин и пароль
    Когда написание автотеста в работе
    Когда совершен переход на страницу "BCS demo аккаунт" по ссылке "url.broker.demo"
    Тогда пользователь "Клиент" ввел логин и пароль

####################################################################################
