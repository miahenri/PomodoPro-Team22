package com.example.pomodoro_22.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0007J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020\"H\u0002J\u0006\u0010\'\u001a\u00020\"J\b\u0010(\u001a\u00020\"H\u0002J\u0006\u0010)\u001a\u00020\"J\u0006\u0010*\u001a\u00020\"J\u0006\u0010+\u001a\u00020\"J\b\u0010,\u001a\u00020\"H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u001e\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 \u00a8\u0006-"}, d2 = {"Lcom/example/pomodoro_22/ui/main/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "settingsViewModel", "Lcom/example/pomodoro_22/ui/settings/SettingsViewModel;", "(Landroid/app/Application;Lcom/example/pomodoro_22/ui/settings/SettingsViewModel;)V", "_phase", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/pomodoro_22/ui/main/PomodoroPhase;", "_timeLeftInMillis", "", "_timerRunning", "", "completedWorkSessions", "", "context", "Landroid/content/Context;", "phase", "Lkotlinx/coroutines/flow/StateFlow;", "getPhase", "()Lkotlinx/coroutines/flow/StateFlow;", "sharedPreferences", "Landroid/content/SharedPreferences;", "timeLeftInMillis", "getTimeLeftInMillis", "timer", "Landroid/os/CountDownTimer;", "timerRunning", "getTimerRunning", "totalTimeForCurrentPhase", "getTotalTimeForCurrentPhase", "()J", "checkAndRequestNotificationPermission", "", "activity", "Landroid/app/Activity;", "createNotificationChannel", "onTimerFinish", "resetTimer", "showNotification", "skipPhase", "startTimer", "stopTimer", "transitionToNextPhase", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.pomodoro_22.ui.settings.SettingsViewModel settingsViewModel = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable
    private android.os.CountDownTimer timer;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.pomodoro_22.ui.main.PomodoroPhase> _phase = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.pomodoro_22.ui.main.PomodoroPhase> phase = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _timeLeftInMillis = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> timeLeftInMillis = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _timerRunning = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> timerRunning = null;
    private int completedWorkSessions = 0;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application, @org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.ui.settings.SettingsViewModel settingsViewModel) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.pomodoro_22.ui.main.PomodoroPhase> getPhase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getTimeLeftInMillis() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getTimerRunning() {
        return null;
    }
    
    public final long getTotalTimeForCurrentPhase() {
        return 0L;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.TIRAMISU)
    public final void checkAndRequestNotificationPermission(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
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
    
    private final void transitionToNextPhase() {
    }
    
    private final void showNotification() {
    }
    
    private final void createNotificationChannel() {
    }
}