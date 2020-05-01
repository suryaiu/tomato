package me.alphar.user.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class IdGenerator {
    /**判断环境参数 server_num（启动服务时传递参数，代码服务的号码）格式是否为 ServerName-00 */
    private static final Pattern isServerNum = Pattern.compile("^.*[-][0-9]{1,2}$", Pattern.CASE_INSENSITIVE);

    private static final LocalDateTime SYSTEM_MIN_DATE = LocalDateTime.of(1991, 1, 1, 0, 0);
    private static Integer guid = 0;
    private static long serverId = 0;

    static {
        guid = 10000;
        String server_num = System.getenv("server_num");
        if(StrUtil.isNotBlank(server_num)) {
            Matcher matcher = isServerNum.matcher(server_num);
            if (matcher.matches()) {
                String podNameStr = matcher.group();
                // 获取根据“-”切割后的最后一个
                String[] podNameStrArray = podNameStr.split("-");
                String index = podNameStrArray[podNameStrArray.length - 1];
                serverId = Integer.valueOf(index);
            }
        }
    }

    /**
     * 生成guid
     */
    public long getGuid() {
        synchronized (guid) {
            guid += 1;
            return Duration.between(SYSTEM_MIN_DATE, LocalDateTime.now()).toMillis() / 1000 * 10000000 + guid * 100 + serverId;
        }
    }

    /**
     * 根据Tid解析日期
     */
    public LocalDateTime getDateTimeFromTid(long tid) {
        long totalSecond = tid / 10000000;
        return SYSTEM_MIN_DATE.plusSeconds(totalSecond);
    }
}
