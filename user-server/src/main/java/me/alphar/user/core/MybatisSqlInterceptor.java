package me.alphar.user.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.util.*;

@Component
@Slf4j
@Intercepts(value = {
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class MybatisSqlInterceptor implements Interceptor {

    private static final String SQL_LOG_PREFIX = "[SQL] ";
    public static final String RAW_SQL_LOG_PREFIX = "执行RAW_SQL: ";
    public static final String SQL_RESULT = "[执行结果]: ";
    public static final String EXECUTE_TIME = "[执行时间]: ";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        // 拦截sql
        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        Object parameterObject = args[1];

        BoundSql boundSql = statement.getBoundSql(parameterObject);
        Configuration configuration = statement.getConfiguration();

        // 未加工的sql，带有 ? 占位符
        String rawSql = boundSql.getSql().replaceAll("[\\s]+", " ");
        String sql = showSql(configuration, boundSql);

        if (StringUtils.isEmpty(sql)) {
            return invocation.proceed();
        }

        Object proceed = invocation.proceed();

        Integer result = 0;
        if (proceed instanceof List) {
            List list = (ArrayList)proceed;
            result = list.size();
        } else if (proceed instanceof  Integer) {
            result = (Integer)proceed;
        }

        // 将未处理的 sql 添加到队列，log 会从队列中获取该 sql 发送到 kafka
        //Queue<String> logQueue = LogQueue.getInstance();
        //logQueue.add(rawSql);

        log.info(SQL_LOG_PREFIX + "{}, "+ SQL_RESULT +"{}, " + EXECUTE_TIME + "{}", sql, result, (System.currentTimeMillis() - start));

        return proceed;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // do nothing
    }

    private String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        StringBuilder sbSql = new StringBuilder(sql);
        String placeholder = "?";
        if (!parameterMappings.isEmpty() && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
                return sql;
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        int i = sbSql.indexOf(placeholder);
                        sbSql.replace(i, i+1, getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        int i = sbSql.indexOf(placeholder);
                        sbSql.replace(i, i+1, getParameterValue(obj));
                    }
                }
            }
        }
        return sbSql.toString();
    }

    private String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

}

