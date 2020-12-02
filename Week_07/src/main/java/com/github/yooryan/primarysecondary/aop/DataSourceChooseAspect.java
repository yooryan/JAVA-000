package com.github.yooryan.primarysecondary.aop;

import com.github.yooryan.primarysecondary.context.DBTypeHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author linyunrui
 */

@Aspect
@Component
public class DataSourceChooseAspect {

    /**
     * 只读：
     * 不是Master注解的对象或方法  && select开头的方法  ||  get开头的方法
     */
    @Pointcut("!@annotation(com.github.yooryan.primarysecondary.dbswitch.Secondary) " +
            "&& (execution(* com.github.yooryan.primarysecondary.service..*.select*(..)) " +
            "|| execution(* com.github.yooryan.primarysecondary.service..*.get*(..)))")
    public void readPointcut() {
    }

    /**
     * 写方法 或 @Primary注解
     */
    @Pointcut("@annotation(com.github.yooryan.primarysecondary.dbswitch.Primary) " +
            "|| execution(* com.github.yooryan.primarysecondary.service..*.insert*(..)) " +
            "|| execution(* com.github.yooryan.primarysecondary.service..*.add*(..)) " +
            "|| execution(* com.github.yooryan.primarysecondary.service..*.update*(..)) " +
            "|| execution(* com.github.yooryan.primarysecondary.service..*.edit*(..)) " +
            "|| execution(* com.github.yooryan.primarysecondary.service..*.delete*(..)) " +
            "|| execution(* com.github.yooryan.primarysecondary.service..*.remove*(..))")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void read(){
        DBTypeHolder.secondary();
    }

    @Before("writePointcut()")
    public void write(){
        DBTypeHolder.primary();
    }

}
