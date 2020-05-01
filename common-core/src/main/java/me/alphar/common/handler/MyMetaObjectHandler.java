package me.alphar.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import me.alphar.common.util.IdGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        IdGenerator idGenerator = new IdGenerator();
        this.strictInsertFill(metaObject, "tid", Long.class, idGenerator.getGuid());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
