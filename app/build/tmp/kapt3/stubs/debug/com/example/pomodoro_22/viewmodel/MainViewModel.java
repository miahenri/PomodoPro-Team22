package com.example.pomodoro_22.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\b\u0010$\u001a\u00020\tH\u0002J\b\u0010%\u001a\u00020\u000bH\u0002J\u0006\u0010&\u001a\u00020!J\u0006\u0010\'\u001a\u00020!J\b\u0010(\u001a\u00020!H\u0002J\u0010\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020!J\u0010\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020\u000bH\u0002J\u0018\u0010/\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\tH\u0002J\u0006\u00101\u001a\u00020!J\b\u00102\u001a\u00020!H\u0002J\u0006\u00103\u001a\u00020!J\b\u00104\u001a\u00020!H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u001d\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\u00a8\u00065"}, d2 = {"Lcom/example/pomodoro_22/viewmodel/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "settingsViewModel", "Lcom/example/pomodoro_22/viewmodel/SettingsViewModel;", "(Landroid/app/Application;Lcom/example/pomodoro_22/viewmodel/SettingsViewModel;)V", "_phase", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/pomodoro_22/viewmodel/PomodoroPhase;", "_timeLeftInMillis", "", "_timerRunning", "", "completedWorkSessions", "", "context", "Landroid/content/Context;", "phase", "getPhase", "()Landroidx/lifecycle/MutableLiveData;", "sharedPreferences", "Landroid/content/SharedPreferences;", "timeLeftInMillis", "getTimeLeftInMillis", "timer", "Landroid/os/CountDownTimer;", "timerRunning", "getTimerRunning", "totalTimeForCurrentPhase", "getTotalTimeForCurrentPhase", "()J", "checkAndRequestNotificationPermission", "", "activity", "Landroid/app/Activity;", "getPhaseFromPreferences", "getTimeFromPreferences", "resetTimer", "restoreTimerState", "saveTimerStateToSharedPreferences", "sendServiceCommand", "action", "", "skipPhase", "startCountdownTimer", "initialMillis", "startForegroundService", "currentPhase", "startTimer", "stopForegroundService", "stopTimer", "transitionToNextPhase", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.pomodoro_22.viewmodel.SettingsViewModel settingsViewModel = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.pomodoro_22.viewmodel.PomodoroPhase> _phase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.pomodoro_22.viewmodel.PomodoroPhase> phase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Long> _timeLeftInMillis = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Long> timeLeftInMillis = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _timerRunning = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> timerRunning = null;
    @org.jetbrains.annotations.Nullable
    private android.os.CountDownTimer timer;
    private int completedWorkSessions;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application, @org.jetbrains.annotations.NotNull
    com.example.pomodoro_22.viewmodel.SettingsViewModel settingsViewModel) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.pomodoro_22.viewmodel.PomodoroPhase> getPhase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Long> getTimeLeftInMillis() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getTimerRunning() {
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
    
    public final void stopTimer() {
    }
    
    public final void resetTimer() {
    }
    
    public final void skipPhase() {
    }
    
    private final void startCountdownTimer(long initialMillis) {
    }
    
    private final void transitionToNextPhase() {
    }
    
    private final com.example.pomodoro_22.viewmodel.PomodoroPhase getPhaseFromPreferences() {
        return null;
    }
    
    private final long getTimeFromPreferences() {
        return 0L;
    }
    
    private final void sendServiceCommand(java.lang.String action) {
    }
    
    private final void startForegroundService(long timeLeftInMillis, com.example.pomodoro_22.viewmodel.PomodoroPhase currentPhase) {
    }
    
    private final void stopForegroundService() {
    }
    
    public final void restoreTimerState() {
    }
    
    private final void saveTimerStateToSharedPreferences() {
    }
}