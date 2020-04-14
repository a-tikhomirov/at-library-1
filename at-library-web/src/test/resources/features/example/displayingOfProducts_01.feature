# language: ru

#  ТК_01. Проверка отображения счетов и карт пользователя на странице
#  Предусловия: У пользователя есть счета Ирбис, счета БИСквит, карты Way4.
#
#  Кейс:
#  1. Проверить отображение продуктов на странице;
#  2. Сравнить количество счетов и карт в ЕФР с количеством счетов и карт на странице "Счета и карты".
#  ОР:
#  1. Продукты отделены друг от друга разделяющей горизонтальной линией. Если продукты связаны одним балансом (мастер счет и карты,
#  главная карты и доп.карты, счетовой контракт), то они отображаются в одном блоке в 2 колонки.
#  2. Количество карт, счетов Ирбис, счетов БИСквит, мастер счетов, счетов и карт Profile  совпадает с количеством со списком счетов и карт из ЕФР.
#  (у которых Плановая дата закрытия > текущей даты).
#  Комментарий:
#  Продукты отделены друг от друга разделяющей горизонтальной линией. - не проверяем,
#  так как горизонтальная линия не выделена в отдельный элемент на веб-странице.
#  ЕФР - внешняя система, не можем реализовать вторую проверку

@mrt_regress
@mrt_regress_cards_and_accounts
Функционал: [Карты и счета][Отображение продуктов][Проверка отображения счетов и карт пользователя на странице]

  Предыстория:
    * написание автотеста в работе
    * создаем клиента для теста
    * создаем связку из карт привязанных к мастер счету
    * создаем группу дебетовых карт для клиента
    * создаем дебетовый счетовой контракт для клиента
    * открываем "autotest_target_url" страницу
    * авторизация с данными созданного клиента
    * открывается страница "Мои продукты"
    * закрываем попапы с предложениями
    * (нажимает кнопку) "Счета и карты"
    * открывается страница "Счета и карты"

  @all
  @denis
  @oddReady
  @regress
  @CardsAndAccounts
  Сценарий: [Шаг 1] Проверка отображения счетов и карт пользователя на странице
    * (Проверяем, что продукты с указанным именем отображаются в одном блоке в 2 колонки) "Мастер счет в рублях"
    * (Проверяем, что продукты с указанным именем отображаются в одном блоке в 2 колонки) "Группа карт"
    * (Проверяем, что продукты с указанным именем отображаются в одном блоке в 2 колонки) "Счетовой контракт"