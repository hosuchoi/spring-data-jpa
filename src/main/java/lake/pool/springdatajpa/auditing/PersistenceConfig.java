//package lake.pool.springdatajpa.auditing;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.auditing.DateTimeProvider;
//
//import java.time.OffsetDateTime;
//import java.time.temporal.TemporalAccessor;
//import java.util.Optional;
//
//@Configuration
//public class PersistenceConfig {
//
//    @Bean(name = "auditingDateTimeProvider")
//    public DateTimeProvider dateTimeProvider() {
//        return new DateTimeProvider() {
//            @Override
//            public Optional<TemporalAccessor> getNow() {
//                return Optional.of(OffsetDateTime.now());
//            }
//        };
//    }
//}