package site.beanyon.apt.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.beanyon.apt.common.aspect.service.IAspectService;

/**
 * 日志切面
 *
 * @author BeanYon
 * 2019.07.24
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private IAspectService logAspectService;

    /**
     * site.beanyon.apt.common.service.impl.UserServiceImpl 类中所有方法的切面处理
     */
    @Pointcut("within(site.beanyon.apt.common.service.impl.FrontUserServiceImpl)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object logHandler(ProceedingJoinPoint process) throws Throwable {
        // 切面前置处理，当切面切入的方法出现异常时，前置处理器不执行
        logAspectService.preExecute(process);

        // 执行被切面拦截的方法
        Object result = null;
//        try {
            result = process.proceed();
//        } catch (Throwable throwable) {
//            // 异常处理
//            logAspectService.exceptionExecute(throwable);
//        }

        // 切面后置处理
        logAspectService.postExecute(result);

        return result;
    }
}
