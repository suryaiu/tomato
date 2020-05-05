package me.alphar.user.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import me.alphar.core.utils.IdGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static String TID = "tid";

    @Override
    public void insertFill(MetaObject metaObject) {
        IdGenerator idGenerator = new IdGenerator();
        Long guid = idGenerator.getGuid();
        System.out.println(guid);

        if (metaObject.hasSetter(TID)
                && metaObject.hasGetter(TID)
                && (metaObject.getValue(TID) == null
                || (metaObject.getValue(TID) instanceof Long
                && ((Long) metaObject.getValue(TID)).compareTo(0L) == 0))) {
            setFieldValByName(TID, guid, metaObject);
        }

//        this.strictInsertFill(metaObject, "tid", Long.class, guid);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "creatorTid", Long.class, 1L);
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
