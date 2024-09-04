package com.example.pomodoro_22.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00100\u000fJ\u0019\u0010\u0011\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/pomodoro_22/repository/TaskRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "taskDao", "Lcom/example/pomodoro_22/data/TaskDao;", "addTask", "", "task", "Lcom/example/pomodoro_22/model/Task;", "(Lcom/example/pomodoro_22/model/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTask", "", "getAllTasks", "Landroidx/lifecycle/LiveData;", "", "updateTask", "app_debug"})
public final class TaskRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.pomodoro_22.data.TaskDao taskDao = null;
    
    public TaskRepository(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.pomodoro_22.model.Task>> getAllTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addTask(@org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.model.Task task, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateTask(@org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.model.Task task, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteTask(@org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.model.Task task, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}