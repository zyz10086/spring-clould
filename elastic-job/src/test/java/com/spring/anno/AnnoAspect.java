package com.spring.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

@Aspect
public class AnnoAspect {

    /**
     * 针对注解建立切点
     */
    @Pointcut(value = "@annotation(com.spring.anno.UseCases)")
    public void methodPointcut(){
        System.out.println("初始化切面");
    }

    /**
     * 针对切点建立切面
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "methodPointcut()")
    public Object invokeUseCases(ProceedingJoinPoint pjp) throws Throwable{
        Method m= resolveMethod(pjp);
        UseCases useCases=m.getAnnotation(UseCases.class);
        System.out.println("id"+useCases.id());
        System.out.println("description"+useCases.description());
        return pjp.proceed();
    }
    @Pointcut(value = "execution(* com.spring.anno.PasswordUtils.*(..))")
    public void testPoint(){}

    @Around(value = "testPoint()")
    public Object testAround(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("测试切点");
        return pjp.proceed();
    }


    protected Method resolveMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Class<?> targetClass = joinPoint.getTarget().getClass();

        Method method = getDeclaredMethodFor(targetClass, signature.getName(),
                signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("Cannot resolve target method: " + signature.getMethod().getName());
        }
        return method;
    }

    private Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getDeclaredMethodFor(superClass, name, parameterTypes);
            }
        }
        return null;
    }

}
