package cn.mimiknight.kuca.boot.autoconfigure.ecology;

import cn.mimiknight.kuca.spring.ecology.EcologyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 属性映射转换器
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-08-31 22:17:37
 * @since v1.0
 */
@Mapper
public interface EcologyMapStruct {

    EcologyMapStruct INSTANCE = Mappers.getMapper(EcologyMapStruct.class);

    @Mappings({
            @Mapping(source = "enable", target = "enable")
    })
    EcologyConfig.RequestValidation convert(EcologyProperties.RequestValidation validation);

    @Mappings({
            @Mapping(source = "enable", target = "enable")
    })
    EcologyConfig.ResponseValidation convert(EcologyProperties.ResponseValidation validation);

}
