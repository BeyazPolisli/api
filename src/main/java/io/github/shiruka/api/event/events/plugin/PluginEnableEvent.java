package io.github.shiruka.api.event.events.plugin;

import io.github.shiruka.api.event.events.PluginEvent;
import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents plugin enable events.
 *
 * @param plugin the plugin.
 */
public record PluginEnableEvent(
  @NotNull Plugin.Container plugin
) implements PluginEvent {

}
