package com.example.pomodoro_22.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lcom/example/pomodoro_22/ui/main/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_timeLeftInMillis", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_timerRunning", "", "timeLeftInMillis", "Lkotlinx/coroutines/flow/StateFlow;", "getTimeLeftInMillis", "()Lkotlinx/coroutines/flow/StateFlow;", "timer", "Landroid/os/CountDownTimer;", "timerRunning", "getTimerRunning", "resetTimer", "", "startTimer", "stopTimer", "app_debug"})
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
    
    public final void startTimer() {
    }
    
    public final void stopTimer() {
    }
    
    public final void resetTimer() {
    }
}