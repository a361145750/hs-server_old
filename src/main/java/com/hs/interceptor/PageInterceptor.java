package com.hs.interceptor;

import com.hs.util.ReflectUtil;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * Created by work_tl on 2016/4/12.
 *
 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。
 * 利用拦截器实现Mybatis分页的原理：
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句
 * 是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。在Mybatis中Statement语句是通过RoutingStatementHandler对象的
 * prepare方法生成的。所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用
 * StatementHandler对象的prepare方法，即调用invocation.proceed()。
 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设
 * 置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。
 */
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class PageInterceptor implements Interceptor {

    Logger log = LoggerFactory.getLogger(PageInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler target = ((RoutingStatementHandler) invocation.getTarget());
        StatementHandler  delegate = ((StatementHandler ) ReflectUtil.getFieldValue(target, "delegate"));
        BoundSql boundSql = delegate.getBoundSql();
        Object parameterObject = boundSql.getParameterObject();
        if(Map.class.isAssignableFrom(parameterObject.getClass())){
            Map parameterMap = (Map)parameterObject;
            oprateSql(boundSql,parameterMap);
        }
        Object result = invocation.proceed();
        return result;
    }

    private void oprateSql(BoundSql boundSql, Map parameterMap) {
        String orgSql = boundSql.getSql();
        String retSql = orgSql;
        if(parameterMap.containsKey("sqlType")){
            if("count".equals(parameterMap.get("sqlType"))){
                retSql = "select count(*) as count from (" + orgSql + ") t";
            } else {
                long page = Long.valueOf(String.valueOf(parameterMap.get("page")));
                long rows = Long.valueOf(String.valueOf(parameterMap.get("rows")));
                retSql = "select * from (" + orgSql + ") t limit " + (page -1)*rows +"," + rows;
            }
        }
        log.debug("orgSql[" + orgSql +"]");
        log.debug("orgSql[" + retSql +"]");
        ReflectUtil.setFieldValue(boundSql,"sql",retSql);
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }

}
