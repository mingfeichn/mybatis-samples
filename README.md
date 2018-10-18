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
- MyBatis中可以使用OGNL的地方有两处：  
    - 动态SQL表达式中
    - ${param}参数中



- MyBatis常用OGNL表达式:  
    - e1 or e2
    - e1 and e2
    - e1 == e2,e1 eq e2
    - e1 != e2,e1 neq e2
    - e1 lt e2：小于
    - e1 lte e2：小于等于，其他gt（大于）,gte（大于等于）
    - e1 in e2
    - e1 not in e2
    - e1 + e2,e1 * e2,e1/e2,e1 - e2,e1%e2
    - !e,not e：非，求反
    - e.method(args)调用对象方法
    - e.property对象属性值
    - e1[e2]按索引取值，List,数组和Map
    - @class@method(args)调用类的静态方法
    - @class@field 调用类的静态字段值

### if
    - 在where条件中使用if
    
    - 在update更新列种使用if
    
    - 在insert动态插入列中使用if

### choose(when, otherwise)
if提供了基本的条件判断，但无法实现if...else的逻辑，此时可以使用choose，when和otherwise
choose元素包含when和otherwise两个标签，至少有一个when，有0个或1个otherwise
### trim(where, set)
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
### foreach  
id in（1，2，3）。可以使用${ids}方式直接获取值，但这种写法不能防止SQL注入，想避免SQL注入就需要用＃{}的方式，这时就要配合使用foreach标签来满足需求。  
foreach可以对数组、Map或实现了Iterable接口（如List、Set）的对象进行遍历。数组在处理时会转换为List对象，因此foreach遍历的对象可以分为两大类：Iterable类型和Map类型。这两种类型在遍历循环时情况不一样  
数组或集合类型的参数默认的名字。推荐使用@Param来指定参数的名字，这时collection就设置为通过@Param注解指定的名字。  
    - foreach包含的属性：  
        - collection: 必填，值为要迭代循环的属性名
        - item：变量名，值为从迭代对象中取出的每一个值
        - index： 索引的属性名，在集合数组情况下为当前索引值，当迭代循环的对象是Map类型时，这个值为Map的key
        - open: 整个循环内容开头字符串
        - close：整个循环内容结尾的字符串
        - separator：每次循环的分隔符

    - 参数为List
    
    - 参数为Array
    
    - 参数为Map
    使用Map和使用@Param注解方式类似，将collection指定为对应Map中的key即可。如果要循环所传入的Map，推荐使用@Param注解指定名字，此时可将collection设置为指定的名字，如果不想指定名字，就使用默认值_parameter。
    
    - 参数为对象
    这种情况下指定为对象的属性名即可。当使用对象内多层嵌套的对象时，使用属性.属性（集合和数组可以使用下标取值）的方式可以指定深层的属性值。
    
    - foreach批量插入
    从MyBatis3.3.1版本开始，MyBatis开始支持批量新增回写主键值的功能，这个功能首先要求数据库主键值为自增类型，同时还要求该数据库提供的JDBC驱动可以支持返回批量插入的主键值（JDBC提供了接口，但并不是所有数据库都完美实现了该接口），因此到目前为止，可以完美支持该功能的仅有MySQL数据库。由于SQLServer数据库官方提供的JDBC只能返回最后一个插入数据的主键值，所以不能支持该功能。
    
    - foreach实现动态更新
    当参数是Map类型的时候，foreach标签的index属性值对应的不是索引值，而是Map中的key，利用这个key可以实现动态UPDATE。
    
### bind
    bind标签可以使用OGNL表达式创建一个变量并将其绑定到上下文中。  
    bind标签的两个属性都是必选项，name为绑定到上下文的变量名，value为OGNL表达式。创建一个bind标签的变量后，就可以在下面直接使用，使用bind拼接字符串不仅可以避免因更换数据库而修改SQL，也能预防SQL注入

### 多数据库支持
bind标签并不能解决更换数据库带来的所有问题，那么还可以通过什么方式支持不同的数据库呢？  
这需要用到`if`标签以及由MyBatis提供的`databaseIdProvider`数据库厂商标识配置。
MyBatis可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的databaseId属性的。MyBatis会加载不带databaseId属性和带有匹配当前数据库databaseId属性的所有语句。如果同时找到带有databaseId和不带databaseId的相同语句，则后者会被舍弃。为支持多厂商特性，只要在mybatis-config.xml文件中加入databaseIdProvider配置即可。  
```
＜databaseIdProvidertype=＂DB_VENDOR＂/＞
```  
这里的DB_VENDOR会通过DatabaseMetaData＃getDatabaseProductName（）返回的字符串进行设置。由于通常情况下这个字符串都非常长而且相同产品的不同版本会返回不同的值，所以通常会通过设置属性别名来使其变短，
在有property配置时，databaseId将被设置为第一个能匹配数据库产品名称的属性键对应的值，如果没有匹配的属性则会被设置为null。在这个例子中，如果getDatabaseProductName（）返回MicrosoftSQLServer，databaseId将被设置为sqlserver。
DB_VENDOR的匹配策略为，DatabaseMetaData＃getDatabaseProductName（）返回的字符串包含property中name部分的值即可匹配，所以虽然SQLServer的产品全名一般为MicrosoftSQLServer，但这里只要设置为SQLServer就可以匹配。数据库产品名一般由所选择的当前数据库的JDBC驱动所决定，只要找到对应数据库DatabaseMetaData接口的实现类，一般在getDatabaseProductName（）方法中就可以直接找到该值。任何情况下都可以通过调用DatabaseMetaData＃getDatabaseProductName（）方法获取具体的值。除了增加上面的配置外，映射文件也是要变化的，关键在于以下几个映射文件的标签中含有的databaseId属性：
- select
- insert
- delete
- update
- selectKey
- sql

  

## 数据准备
```
ALTER TABLE `sys_user` 
MODIFY COLUMN `user_email` VARCHAR ( 50 ) NOT NULL DEFAULT 'test@126.com' COMMENT '邮箱' 
AFTER `user_password`;
```
