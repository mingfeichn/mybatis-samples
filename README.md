# mybatis-samples
mybatis示例

## mybatis基础配置
### 


### 准备数据库

#### 创建数据库 mybatis
```
CREATE DATABASE mybatis DEFAULT CHARACTER 
SET utf8 COLLATE utf8_general_ci;```
```
#### 创建表 country
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

#### 插入数据
```
INSERT country ( `countryname`, `countrycode` )
VALUES
	( '中国', 'CN' ),
	( '美国', 'US' ),
	( '俄罗斯', 'RU' ),
	( '英国', 'GB' ),
	( '法国', 'FR' );
```

## mybatis XML方式的基本用法
### \<select>元素

### \<insert>元素
- id  

- parameterType  

- flushCache  
默认为true，任何时候只要语句被调用，都会清空一级缓存和二级缓存
- timeout  
设置在抛出异常前，驱动程序等待数据库返回请求结果的描述
- statementType
STATEMENT, PREPARED和CALLABLE分别使用对应的Statement, PreparedStatement和CallableStatement,
默认为PREPARED
- useGenerateKeys  
默认为false，如果设置为true，mybatis会使用JDBC的getGeneratedKeys方法取出数据库内部生成的主键
- keyProperty  
mybatis通过useGenerateKeys获取的主键值后将要赋值的属性名，如果有多个，使用都好分隔
- keyColumn  
仅对insert和update有效。通过生成的键值设置表中的列名（仅PostgreSQl必须），当主键列不是表中的第一列
时需要设置，生成多列时使用都好分隔
- databaseId  
如果设置了databaseIdProvider,mybatis会加载所有不带databaseId的或匹配当前databaseId的语句，如果
同时存在带databaseId和不带databaseId的语句，后者会被忽略

### \<update>元素

### \<delete>元素

### 多个参数接口
- 将参数封装为JavaBean  
参数少时不划算
- 使用Map类型作为参数  
手工封装，不方便
- 使用@Param  
给参数配置@Param注解后，MyBatis就会自动将参数封装成Map类型，@Param注解值会作为Map中的key
当参数为JavaBean时，XML中需要使用点取值方式，如#{user.id}

### Mapper接口动态代理实现原理

### 准备表和数据
```
# 创建用户表
CREATE TABLE sys_user (
id BIGINT NOT NULL auto_increment COMMENT '用户ID', 
user_name VARCHAR ( 50 ) COMMENT '用户名',
user_password VARCHAR ( 50 ) COMMENT '密码',
user_email VARCHAR ( 50 ) COMMENT '邮箱',
user_info text COMMENT '简介',
head_img BLOB COMMENT '头像',
create_time datetime COMMENT '创建时间',
PRIMARY KEY ( id ) 
);

ALTER TABLE sys_user COMMENT '用户表';
```
```
# 创建角色表
CREATE TABLE sys_role (
id BIGINT NOT NULL auto_increment COMMENT '角色ID',
role_name VARCHAR ( 50 ) COMMENT '角色名',
enabled INT COMMENT '有效标志',
create_by BIGINT COMMENT '创建人',
create_time datetime COMMENT '创建时间', 
PRIMARY KEY ( id ) 
);

ALTER TABLE sys_role COMMENT '角色表';
```
```
# 创建权限表
CREATE TABLE sys_privilege (
id BIGINT NOT NULL auto_increment COMMENT '权限ID', 
privilege_name VARCHAR ( 50 ) COMMENT '权限名称',
privilege_url VARCHAR ( 200 ) COMMENT '权限URL',
PRIMARY KEY ( id ) 
);

ALTER TABLE sys_privilege COMMENT '权限表';
```
```
# 创建用户角色关联表
CREATE TABLE sys_user_role ( 
user_id BIGINT COMMENT '用户ID', 
role_id BIGINT COMMENT '角色ID' );

ALTER TABLE sys_user_role COMMENT '用户角色关联表';
```
```
# 创建角色权限关联表
CREATE TABLE sys_role_privilege ( 
role_id BIGINT COMMENT '角色ID', 
privilege_id BIGINT COMMENT '权限ID' ); 

ALTER TABLE sys_role_privilege COMMENT '角色权限关联表';
```
```
# 插入数据
INSERT INTO `sys_user` VALUES ( '1', 'admin', '123456', 'admin@126.com', '管理员', NULL, '2016-04-01 17:00:58' );
INSERT INTO `sys_user` VALUES ( '1001', 'test', '123456', 'test@126.com', '测试用户', NULL, '2016-04-01 17:01:52' );
	
INSERT INTO `sys_role` VALUES ( '1', '管理员', '1', '1', '2016-04-01 17:02:14' );
INSERT INTO `sys_role` VALUES ( '2', '普通用户', '1', '1', '2016-04-01 17:02:14' );
	
INSERT INTO `sys_user_role` VALUES ( '1', '1' );
INSERT INTO `sys_user_role` VALUES ( '1', '2' );
INSERT INTO `sys_user_role` VALUES ( '1001', '2' );
	
INSERT INTO `sys_privilege` VALUES ( '1', '用户管理', '/users' );
INSERT INTO `sys_privilege` VALUES ( '2', '角色管理', '/roles' );
INSERT INTO `sys_privilege` VALUES ( '3', '系统日志', '/logs' );
INSERT INTO `sys_privilege` VALUES ( '4', '人员维护', '/persons' );
INSERT INTO `sys_privilege` VALUES ( '5', '单位维护', '/companies' );

INSERT INTO `sys_role_privilege` VALUES ('1','1');
INSERT INTO `sys_role_privilege` VALUES ('1','3');
INSERT INTO `sys_role_privilege` VALUES ('1','2');
INSERT INTO `sys_role_privilege` VALUES ('2','4');
INSERT INTO `sys_role_privilege` VALUES ('2','5');
```
## mybatis注解方式的基本用法
优点：对于需求简单的系统，效率较高
缺点：更新sql后需要重新编译

- @Select
字段与属性映射三种方式

- @Insert

- @Update

- @Delete

- @Provider
    - @SelectProvider
    
    - @InsertProvider
    
    - @UpdateProvider
    
    - @DeleteProvider

## mybatis动态SQL
OGNL(Object-Graph Navigation Language)

- if
    - 在where条件中使用if
    
    - 在update更新列种使用if
    
    - 在insert动态插入列中使用if

- choose(when, otherwise)
if提供了基本的条件判断，但无法实现if...else的逻辑，此时可以使用choose，when和otherwise
choose元素包含when和otherwise两个标签，至少有一个when，有0个或1个otherwise
- trim(where, set)
    - where
    如果该标签包含的元素中有返回值，就插入一个where  
    如果where后面的字符串是以AND和OR开头，就将他们删除
    - set
    若该标签包含的元素有返回值，就插入一个set  
    若set后面的字符串以逗号结尾，就将逗号删除
    - trim
    where和set底层通过trim实现  
    trim标签属性：
        - prefix: 当trim元素内包含内容时，会给内容增加prefix指定的前缀  
        - prefixOverrides: 当trim元素内包含内容时，会把内容中匹配的前缀字符串去掉
        - suffix： 当trim元素内包含内容时，会给内容增加prefix指定的后缀  
        - suffixOverrides: 当trim元素内包含内容时，会把内容中匹配的后缀字符串去掉  
        
        **where标签对应的trim实现：**
        ```xml
         <trim prifix="WHERE" prefixOverrides="AND |OR ">
         ...     
         </trim>
        ```
        这里的AND和OR后面的空格不能省略，为了避免匹配到andes、orders等单词。  
        实际的prefixeOverrides包含“AND”、“OR”、“AND\n”、“OR\n”、“AND\r”、“OR\r”、“AND\t”、“OR\t”，不仅仅是上面提到的两个带空格的前缀。  

        **set标签对应的trim实现：**
        ```xml
         <trim prifix="SET" suffixOverrides=",">
         ...     
         </trim>
        ```
- foreach  
id in（1，2，3）。可以使用${ids}方式直接获取值，但这种写法不能防止SQL注入，想避免SQL注入就需要用＃{}的方式，这时就要配合使用foreach标签来满足需求。  
foreach可以对数组、Map或实现了Iterable接口（如List、Set）的对象进行遍历。数组在处理时会转换为List对象，因此foreach遍历的对象可以分为两大类：Iterable类型和Map类型。这两种类型在遍历循环时情况不一样  
foreach包含的属性：  
    - collection: 必填，值为要迭代循环的属性名
    - item：变量名，值为从迭代对象中取出的每一个值
    - index： 索引的属性名，在集合数组情况下为当前索引值，当迭代循环的对象是Map类型时，这个值为Map的key
    - open: 整个循环内容开头字符串
    - close：整个循环内容结尾的字符串
    - separator：每次循环的分隔符
    

- bind

## 数据准备
```
ALTER TABLE `sys_user` 
MODIFY COLUMN `user_email` VARCHAR ( 50 ) NOT NULL DEFAULT 'test@126.com' COMMENT '邮箱' 
AFTER `user_password`;
```
