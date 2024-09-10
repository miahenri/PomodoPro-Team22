package com.example.pomodoro_22.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\u001aJ\u0006\u0010\u001c\u001a\u00020\u001aJ\u0006\u0010\u001d\u001a\u00020\u001aJ\u0006\u0010\u001e\u001a\u00020\u001aR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0014\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/pomodoro_22/ui/main/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_phase", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/pomodoro_22/ui/main/PomodoroPhase;", "_timeLeftInMillis", "", "_timerRunning", "", "phase", "Lkotlinx/coroutines/flow/StateFlow;", "getPhase", "()Lkotlinx/coroutines/flow/StateFlow;", "timeLeftInMillis", "getTimeLeftInMillis", "timer", "Landroid/os/CountDownTimer;", "timerRunning", "getTimerRunning", "totalTimeForCurrentPhase", "getTotalTimeForCurrentPhase", "()J", "workSessionCount", "", "onTimerFinish", "", "resetTimer", "skipPhase", "startTimer", "stopTimer", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.Nullable
    private android.os.CountDownTimer timer;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _timeLeftInMillis = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> timeLeftInMillis = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _timerRunning = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> timerRunning = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.pomodoro_22.ui.main.PomodoroPhase> _phase = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.pomodoro_22.ui.main.PomodoroPhase> phase = null;
    private int workSessionCount = 0;
    
    public MainViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getTimeLeftInMillis() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getTimerRunning() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.pomodoro_22.ui.main.PomodoroPhase> getPhase() {
        return null;
    }
    
    public final long getTotalTimeForCurrentPhase() {
        return 0L;
    }
    
    public final void startTimer() {
    }
    
    private final void onTimerFinish() {
    }
    
    public final void stopTimer() {
    }
    
    public final void resetTimer() {
    }
    
    public final void skipPhase() {
    }
}