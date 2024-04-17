## JDBC API
- Connection - объект, отвечающий за соединение с БД и режим работы с ней.
- Statement - представление выражения при обращении к БД.
- ResultSet - результат запроса, который возвращает БД.
### JDBC Driver Manager
Для конкретных БД (MySQL, PostgreSQL, ...) нужен свой драйвер.
Нужно создать объект Driver и зарегистрировать его в DriverManager.registerDriver();

Connection получается следующим образом:
Connection connection = DriverManager.getConnection(url)

## Statements
JDBC позволяет создавать и выполнять запросы к БД.
- Update statements: CREATE, DELETE, INSERT... (возвращает кол-во изменённый строк)
- Query statments: SELECT (возвращает строки с данными -  ResultSet)

## ResultSet
Перемещение по строкам:
- next()
- previous()
- isLast()

Доступ к полям текущей строки:
- По имени колонки getBoolean(String name), getLong(String name)...
- По индексу колонки getBoolean(int index), getLong(int index)...

## Закрытие соединения с БД
- resultSet.close()
- statement.close()
- connection.close()

Либо можно использовать AutoClosable (try с ресурсами)