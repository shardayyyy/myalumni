# These values are used in the spring.properties file
# This file holds configuration for other databases
# such as Oracle, PostgreSQL, DB2, SQL Server, Sybase


#Oracle config
#driver=oracle.jdbc.driver.OracleDriver
#dbip=127.0.0.1
#dbname=myalumnidb
#dbport=1521
#url=jdbc:oracle:thin:@${dbip}:${dbport}:${dbname}
#username=root
#password=root
#hibernate.dialect=net.sf.hibernate.dialect.OracleDialect
# Path to oracle database dump executable, Used for DB backup
#dbpath=

#PostgreSQL config
#driver=org.postgresql.Driver
#dbip=127.0.0.1
#dbname=myalumnidb
#dbport=5432
#url=jdbc:postgresql://${dbip}:${dbport}/${dbname}
#username=myalunniuser
#password=myalunnipswd
#hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
# Path to postgresql database dump executable, Used for DB backup
#dbpath=

# DB2 config
#driver=com.ibm.db2.jdbc.app.DB2Driver
#dbip=127.0.0.1
#dbname=myalumnidb
#dbport=
#url=jdbc:db2:${dbname}
#username=db2admin
#password=db2admin
#hibernate.dialect=net.sf.hibernate.dialect.DB2Dialect
# Path to db2 database dump executable, Used for DB backup
#dbpath=

#SQL SERVER config
#driver=
#dbip=127.0.0.1
#dbname=myalumnidb
#dbport=
#url=
#username=
#password=
#hibernate.dialect=net.sf.hibernate.dialect.SQLServerDialect
# Path to sql server database dump executable, Used for DB backup
#dbpath=

#Sybase config
#driver=
#dbip=127.0.0.1
#dbname=myalumnidb
#dbport=
#url=
#username=
#password=
#hibernate.dialect=net.sf.hibernate.dialect.SybaseDialect
# Path to sybase database dump executable, Used for DB backup
#dbpath=