import com.google.inject.AbstractModule;
import com.knoldus.dao.jpa.EmployeeDao;
import com.knoldus.dao.jpa.impl.EmployeeDaoImpl;
import com.knoldus.services.ApplicationTimer;
import com.knoldus.services.AtomicCounter;
import com.knoldus.services.Counter;
import com.knoldus.services.jpa.EmployeeService;
import com.knoldus.services.jpa.impl.EmployeeServiceImpl;

import java.time.Clock;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 * <p>
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {
        // Use the system clock as the default implementation of Clock
        bind(Clock.class).toInstance(Clock.systemDefaultZone());
        // Ask Guice to create an instance of ApplicationTimer when the
        // application starts.
        bind(ApplicationTimer.class).asEagerSingleton();
        // Set AtomicCounter as the implementation for Counter.
        bind(Counter.class).to(AtomicCounter.class);
        bind(EmployeeService.class).to(EmployeeServiceImpl.class);
        bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
    }

}
