package cn.mimiknight.developer.kuca.spring.validation.validator;

import cn.mimiknight.developer.kuca.spring.validation.annotation.validation.KucaNotBlank;
import org.apache.commons.lang3.StringUtils;

/**
 * 参数非空校验器
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-07 20:13:23
 */
public class NotBlankValidator implements ConstraintValidator<KucaNotBlank, String> {
    @Override
    public void initialize(KucaNotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value) {
        return StringUtils.isNotBlank(value);
    }
}
