# KotlinDatabase

这个依赖包主要解决了 android 使用本地数据库的繁琐操作 

好处是可以直接使用对象进行数据操作，不需要再次使用对象转成ContentValues那么麻烦


java


    //检查是否存在数据库和表   
    //如果没有这个数据库和表 则会自动创建
    CheckDatabase.Companion.CheckData(context , "数据库名称" , "表名" , Database.Check);
    
    
    //这个是一个接口
    Database.Check
    
    //这个方法是接口的实现方法
    //创建表的实现方法
    @NotNull
    @Override
    public Establish CreateTable(@NotNull String database, @NotNull String table, boolean state) {
        //如果state 等于true说明这个表已经创建
        //否则就是没有创建  这个时候应该将创建表的参数给传进来
        if (state) {
            return null;
        } else {
            //判断是什么表
            if (TextUtils.equals(table, "test")) {
                establish = getTopic();
            }
            return establish;
        }
    }
    
    //这个是将表的参数 封装成需要的包
    private Establish getTopic() {
        Establish establish = new Establish();
        //第一个是参数名称，第二个是参数的属性
        establish.put("comment_num", "int");
        establish.put("content", "varchar(50)");
        return establish;
    }

    //以上就是自动创建表的操作
    
    接下来就是数据库增删查改
    BaseUser  这个类
    
    //例如插入数据
    new BaseUser(context).INSERT("数据库" , "表名" , 数据库对象)
    
    
Kotlin

    //检查是否存在数据库和表   
    //如果没有这个数据库和表 则会自动创建
    CheckDatabase.CheckData(context , "数据库" , "表名" , Database.Check)


    //这个是一个接口
    Database.Check
    
    
    
    override fun CreateTable(database: String, table: String, state: Boolean): Establish {
        //如果state 等于true说明这个表已经创建
        //否则就是没有创建  这个时候应该将创建表的参数给传进来
        if (state) {
            return null
        } else {
            //判断是什么表
            if (TextUtils.equals(table, "test")) {
                establish = getTopic()
            }
            return establish
        }
    }
    
    
    //这个是将表的参数 封装成需要的包
    fun getTopic() : Establish{
        var establish : Establish = Establish()
        //第一个是参数名称，第二个是参数的属性
        establish.put("comment_num", "int")
        establish.put("content", "varchar(50)")
        return establish
    }
    
    
    
    //以上就是自动创建表的操作
    
    接下来就是数据库增删查改
    BaseUser  这个类
    
    //例如插入数据
    BaseUser(context).INSERT("数据库" , "表名" , 数据库对象)
    
    
    
上面就是实现数据库操作的所有代码


调用这个库的的链接

    compile 'ruan.database.com:mydatabase:1.0'
    
