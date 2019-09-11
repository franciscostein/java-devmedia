package br.com.devmedia.cursos.oqueecdi.unitedburger.domain.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@Qualifier
@Retention(RetentionPolicy.RUNTIME) // Mostra pra CDI que essa anotação estará disponível durante tora a aplicação
@Target({METHOD, FIELD, PARAMETER})
public @interface UnitedBurgerQualifier {
}
