package de.norbertspiess.spring.boot.events.spring;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

@RequiredArgsConstructor
public class Event<T> implements ResolvableTypeProvider {

    @Getter
    private final T content;

    /**
     * Extracts the type of the content so it can be matched with listeners that expect only a certain type of the content
     */
    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(content));
    }
}
