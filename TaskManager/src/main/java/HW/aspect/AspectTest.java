package HW.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;

@Aspect
@Component
public class AspectTest {
    /**
     * перед вызовом метода с аннотацией @TrackUserAction
     *
     * @param joinPoint
     */
    @Around(value = "@annotation(TrackUserAction)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.printf("%s: Пользователь вызвал метод:\"%s.%s()\"\n",
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        return joinPoint.proceed();
    }
}
