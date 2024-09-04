package com.example.pomodoro_22.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0007\u001a\u0018\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001aB\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\b\u0010\u0011\u001a\u00020\u0001H\u0007\u001a\u001a\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a\b\u0010\u0017\u001a\u00020\u0001H\u0007\u001a3\u0010\u0018\u001a\u00020\u00012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a&\u0010\u001f\u001a\u00020\u00012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0014H\u0007\u001a\b\u0010#\u001a\u00020\u0001H\u0007\u001a\u000e\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\n\u001a\"\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\n2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010*\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006+"}, d2 = {"DividerLine", "", "DividerLinePreview", "MainScreen", "navController", "Landroidx/navigation/NavHostController;", "taskViewModel", "Lcom/example/pomodoro_22/ui/task/TaskViewModel;", "PomodoroTimer", "timeInMillis", "", "timerRunning", "", "onStartTimer", "Lkotlin/Function0;", "onStopTimer", "onResetTimer", "PomodoroTimerPreview", "PomodoroTitle", "name", "", "modifier", "Landroidx/compose/ui/Modifier;", "PomodoroTitlePreview", "RoundedButton", "onClick", "text", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "RoundedButton-mxwnekA", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;J)V", "RoundedIconButton", "icon", "", "contentDescription", "WorkPhases", "formatTime", "millis", "startTimer", "Landroid/os/CountDownTimer;", "totalTimeInMillis", "onTick", "Lkotlin/Function1;", "app_debug"})
public final class MainActivityKt {
    
    @androidx.compose.runtime.Composable
    public static final void MainScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.ui.task.TaskViewModel taskViewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void WorkPhases() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PomodoroTimer(long timeInMillis, boolean timerRunning, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onStartTimer, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onStopTimer, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onResetTimer) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String formatTime(long millis) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final android.os.CountDownTimer startTimer(long totalTimeInMillis, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onTick) {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    public static final void PomodoroTitle(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void DividerLine() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void RoundedIconButton(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, int icon, @org.jetbrains.annotations.NotNull
    java.lang.String contentDescription) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable
    public static final void PomodoroTitlePreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable
    public static final void DividerLinePreview() {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable
    public static final void PomodoroTimerPreview() {
    }
}