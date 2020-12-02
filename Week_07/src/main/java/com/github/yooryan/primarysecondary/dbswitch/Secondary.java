package com.github.yooryan.primarysecondary.dbswitch;

import java.lang.annotation.*;

/**
 * @author linyunrui
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface Secondary {
}
