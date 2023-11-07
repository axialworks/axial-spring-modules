package com.axial.modules.commons.outline.enum_code;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumCodeUtils {

    public static <T extends EnumCode> T findByCode(Class<T> clazz,
                                                    String enumCode) {

        if (StringUtils.isBlank(enumCode) || !clazz.isEnum()) {
            return null;
        }

        try {
            final Method method = clazz.getMethod("values");
            return Arrays.stream((T[]) method.invoke(null))
                    .filter(enumValue -> enumValue.getCode()
                            .equalsIgnoreCase(enumCode))
                    .findFirst().orElse(null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
