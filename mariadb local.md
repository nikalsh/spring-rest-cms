##Lokal MySQL MariaDB
0. Ladda ner och installera MariaDB 10.1
https://downloads.mariadb.org/mariadb/10.1.41/

2. Använd UTF-8 och sätt port till 3306
3. Sätt användare och lösenord till root/root
4. Kör sedan följande i en kommandoprompt:
```bash
mysql -uroot -proot
create database c1brablogg;
create user 'c1brablogg_admin'@localhost identified by '5gyc!FHJ6jnL';
grant usage on *.* to 'c1brablogg_admin'@localhost identified by '5gyc!FHJ6jnL';
grant all privileges on c1brablogg.* to 'c1brablogg_admin'@'localhost' identified by '5gyc!FHJ6jnL';
```