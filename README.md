# mybatis-samples
mybatis示例


## 准备数据库

### 创建数据库 mybatis
```
CREATE DATABASE mybatis DEFAULT CHARACTER 
SET utf8 COLLATE utf8_general_ci;```
```
### 创建表 country
```
USE mybatis;
```
```
CREATE TABLE `mybatis`.`country` (
	`id` int(3) NOT NULL AUTO_INCREMENT,
	`countryname` varchar(255) NULL,
	`countrycode` varchar(255) NULL,
	PRIMARY KEY (`id`)
);
```

### 插入数据
```
INSERT country ( `countryname`, `countrycode` )
VALUES
	( '中国', 'CN' ),
	( '美国', 'US' ),
	( '俄罗斯', 'RU' ),
	( '英国', 'GB' ),
	( '法国', 'FR' );
```
