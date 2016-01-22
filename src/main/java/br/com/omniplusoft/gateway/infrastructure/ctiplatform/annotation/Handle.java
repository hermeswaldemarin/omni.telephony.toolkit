package br.com.omniplusoft.gateway.infrastructure.ctiplatform.annotation;

import br.com.omniplusoft.gateway.infrastructure.ctiplatform.CTIEvents;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Handle {

    CTIEvents value();

}
