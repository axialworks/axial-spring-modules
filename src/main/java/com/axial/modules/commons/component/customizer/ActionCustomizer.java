package com.axial.modules.commons.component.customizer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Example Flow.
 * First define another interface for customizable actions:

 public interface GoToSpace<T, U> extends ActionCustomizer {

 void firstStage(T input);

 void secondStage(U input);

 }

 * Then implement this customizable actions:

 public class GoToSpaceWithFalcon implements GoToSpace {

@Override
public String getIdentifier() {
return "GO_SPACE_WITH_FALCON";
}

@Override
public void firstStage(MerlinMotors motors) {

// implement ignition related actions

}

@Override
public void secondStage(Boosters twoBooster) {
// implement second stage separation related actions
}

}

 * Use this customization in anywhere, inside a method:

 void aMethod() {

 final Optional<GoToSpace> goToSpaceCustomizer = ActionCustomizer.find(GoToSpace.class, applicationContext, identifier);

 goToSpaceCustomizer.ifPresent(customizer -> customizer.firstStage(merlinMotors);
 goToSpaceCustomizer.ifPresent(customizer -> customizer.secondStage(twoBooster);
 }

 */
public interface ActionCustomizer {

    /**
     * It must be unique per customizers
     * @return
     */
    String getIdentifier();

    static <T extends ActionCustomizer> Optional<T> find(Class<T> beanClass, ApplicationContext ctx, String identifier) {

        if (Objects.isNull(identifier)) {
            return Optional.empty();
        }

        try {
            final Map<String, T> beans = ctx.getBeansOfType(beanClass);
            return beans.values()
                    .stream().filter(c -> identifier.equals(c.getIdentifier()))
                    .findFirst();
        } catch (BeansException ex) {
            return Optional.empty();
        }

    }


}