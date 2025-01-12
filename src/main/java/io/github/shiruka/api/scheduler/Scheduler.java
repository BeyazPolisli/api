package io.github.shiruka.api.scheduler;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

  /**
   * gets the implementation of async Scheduler.
   *
   * @return implementation of async Scheduler.
   */
  @NotNull
  static Async async() {
    return Shiruka.asyncScheduler();
  }

  /**
   * gets the implementation of sync Scheduler.
   *
   * @return implementation of sync Scheduler.
   */
  @NotNull
  static Sync sync() {
    return Shiruka.syncScheduler();
  }

  /**
   * cancels the the task of the id.
   *
   * @param taskId the task id to cancel.
   */
  void cancelTask(int taskId);

  /**
   * cancels all the task of the plugin.
   *
   * @param plugin the plugin to cancel.
   */
  void cancelTasks(@NotNull Plugin.Container plugin);

  /**
   * executes the given task.
   *
   * @param task the task to execute.
   *
   * @return scheduled task.
   */
  @NotNull
  ScheduledTask execute(@NotNull Task task);

  /**
   * heartbeats the scheduler.
   *
   * @param currentTick the current tick to heartbeat.
   */
  void heartbeat(int currentTick);

  /**
   * checks if the scheduler is async.
   *
   * @return {@code true } if the scheduler is async.
   */
  boolean isAsync();

  /**
   * checks if the scheduler is sync.
   *
   * @return {@code true } if the scheduler is sync.
   */
  boolean isSync();

  /**
   * creates a task builder.
   *
   * @return a newly created task builder.
   */
  @NotNull
  default Task.Builder newBuilder() {
    return new Task.Builder.Impl(this);
  }

  /**
   * a marker interface to determine async schedulers.
   */
  interface Async extends Scheduler {

    @Override
    default boolean isAsync() {
      return true;
    }

    @Override
    default boolean isSync() {
      return false;
    }
  }

  /**
   * a marker interface to determine sync schedulers.
   */
  interface Sync extends Scheduler {

    @Override
    default boolean isAsync() {
      return false;
    }

    @Override
    default boolean isSync() {
      return true;
    }
  }
}
