package com.example.pomodoro_22.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a`\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a\u001a\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007\u001a=\u0010\u0014\u001a\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a&\u0010\u001b\u001a\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0011H\u0007\u001a0\u0010\u001f\u001a\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007\u001a\u000e\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0004\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"DividerLine", "", "PomodoroTimer", "timeInMillis", "", "timerRunning", "", "currentPhase", "Lcom/example/pomodoro_22/viewmodel/PomodoroPhase;", "totalTimeForCurrentPhase", "onStartTimer", "Lkotlin/Function0;", "onStopTimer", "onResetTimer", "onSkip", "PomodoroTitle", "name", "", "modifier", "Landroidx/compose/ui/Modifier;", "RoundedButton", "onClick", "text", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "RoundedButton-9LQNqLg", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;JLandroidx/compose/ui/Modifier;)V", "RoundedIconButton", "icon", "", "contentDescription", "SubtleSkipButton", "formatTime", "millis", "app_debug"})
public final class MainAdapterKt {
    
    @androidx.compose.runtime.Composable
    public static final void PomodoroTimer(long timeInMillis, boolean timerRunning, @org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.viewmodel.PomodoroPhase currentPhase, long totalTimeForCurrentPhase, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onStartTimer, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onStopTimer, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onResetTimer, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onSkip) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String formatTime(long millis) {
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
    
    @androidx.compose.runtime.Composable
    public static final void SubtleSkipButton(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, int icon, @org.jetbrains.annotations.NotNull
    java.lang.String contentDescription, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
}