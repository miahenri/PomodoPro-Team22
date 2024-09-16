package com.example.pomodoro_22.ui.task;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a&\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\b\u0010\t\u001a\u00020\u0001H\u0007\u001a8\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\b\u0010\u0010\u001a\u00020\u0001H\u0007\u001aH\u0010\u0011\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001a\b\u0010\u0016\u001a\u00020\u0001H\u0007\u001a\u001a\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001a\b\u0010\u0019\u001a\u00020\u0001H\u0007\u00a8\u0006\u001a"}, d2 = {"DividerLine", "", "RoundedIconButton", "onClick", "Lkotlin/Function0;", "icon", "", "contentDescription", "", "TaskDivider", "TaskItem", "task", "Lcom/example/pomodoro_22/model/Task;", "onTaskClick", "Lkotlin/Function1;", "onTaskDelete", "TaskItemPreview", "TaskList", "tasks", "", "modifier", "Landroidx/compose/ui/Modifier;", "TaskListPreview", "TaskTitle", "name", "TaskTitlePreview", "app_debug"})
public final class TaskAdapterKt {
    
    @androidx.compose.runtime.Composable
    public static final void TaskTitle(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TaskItem(@org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.model.Task task, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.pomodoro_22.model.Task, kotlin.Unit> onTaskClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.pomodoro_22.model.Task, kotlin.Unit> onTaskDelete) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TaskList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.pomodoro_22.model.Task> tasks, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.pomodoro_22.model.Task, kotlin.Unit> onTaskClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.pomodoro_22.model.Task, kotlin.Unit> onTaskDelete, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void DividerLine() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TaskDivider() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void RoundedIconButton(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, int icon, @org.jetbrains.annotations.NotNull
    java.lang.String contentDescription) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview
    @androidx.compose.runtime.Composable
    public static final void TaskTitlePreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview
    @androidx.compose.runtime.Composable
    public static final void TaskItemPreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview
    @androidx.compose.runtime.Composable
    public static final void TaskListPreview() {
    }
}