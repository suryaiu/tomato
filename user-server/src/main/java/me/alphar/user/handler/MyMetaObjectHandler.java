package me.alphar.user.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import me.alphar.user.util.IdGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        IdGenerator idGenerator = new IdGenerator();
        this.strictInsertFill(metaObject, "tid", Long.class, idGenerator.getGuid());
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "creatorTid", Long.class, Long.valueOf(1));
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updaterTid", Long.class, 1L);
        this.strictInsertFill(metaObject, "isDeleted", Boolean.class, false);
        this.strictInsertFill(metaObject, "version", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updaterTid", Long.class, 1L);
    }
}
