package az.transactional.aop;

import az.transactional.db.DBAccess;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@RequiredArgsConstructor
public class MyAOP {
    private final DBAccess dbAccess;
    private static final Logger LOG = LoggerFactory.getLogger(MyAOP.class);

    @Around("@annotation(az.transactional.transactional.MyTransactional)")
    public Object transactionSql(ProceedingJoinPoint pjd)throws Throwable{
        Object output = null;
try {
    dbAccess.beginTransaction();
    output=pjd.proceed();
    dbAccess.commitTransaction();
}catch (Exception ex){
    dbAccess.rollBackTransaction();
    LOG.info("exception occured inside transactional method." + ex.getMessage());
}

        return output;
    }

}
