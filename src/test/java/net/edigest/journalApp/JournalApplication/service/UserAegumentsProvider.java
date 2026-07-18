package net.edigest.journalApp.JournalApplication.service;

import net.edigest.journalApp.JournalApplication.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserAegumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(
            Arguments.of(User.builder().username("djhu").password("jskh").build()),
            Arguments.of(User.builder().username("hg").password("12").build())
    );
    }
}
