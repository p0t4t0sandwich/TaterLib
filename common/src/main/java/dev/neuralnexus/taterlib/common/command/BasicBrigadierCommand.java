package dev.neuralnexus.taterlib.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.concurrent.CompletableFuture;

/**
 * A basic brigadier command.
 * @param <S> The command source.
 */
public class BasicBrigadierCommand<S> implements Command<S> {//, SuggestionProvider<S> {
    @Override
    public int run(CommandContext<S> context) throws CommandSyntaxException {
        return 0;
    }

//    @Override
//    public CompletableFuture<Suggestions> getSuggestions(CommandContext<S> context, SuggestionsBuilder builder) throws CommandSyntaxException {
//        return null;
//    }
}
