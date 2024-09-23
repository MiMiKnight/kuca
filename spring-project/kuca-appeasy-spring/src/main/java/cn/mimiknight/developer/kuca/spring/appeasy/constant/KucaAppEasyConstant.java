package cn.mimiknight.developer.kuca.spring.appeasy.constant;

import cn.mimiknight.developer.kuca.spring.api.common.utils.KucaSpringContextUtils;
import cn.mimiknight.developer.kuca.spring.appeasy.KucaAppEasyProperties;

/**
 * kuca app easy constant
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-23 19:49:59
 */
public interface KucaAppEasyConstant {


    interface DB {

        /**
         * 数据库时区
         */
        String TIME_ZONE = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class).getDatabase().getTimezone();

        /**
         * 数据库时间格式
         */
        String DATE_TIME_FORMAT = KucaSpringContextUtils.getBean(KucaAppEasyProperties.class).getDatabase().getDateTimeFormat();
    }
}
