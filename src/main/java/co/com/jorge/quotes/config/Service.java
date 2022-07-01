package co.com.jorge.quotes.config;

import co.com.jorge.quotes.interceptors.TransactionalJDBC;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@TransactionalJDBC
@ApplicationScoped
@Stereotype
@Named
@Target(TYPE)
@Retention(RUNTIME)
public @interface Service {
}
