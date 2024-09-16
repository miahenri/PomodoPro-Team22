package com.example.pomodoro_22.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\"\u0010\u0012\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0017\u001a\u00020\rH\u0002J\u001a\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/pomodoro_22/ui/main/PomodoroForegroundService;", "Landroid/app/Service;", "()V", "currentPhase", "Lcom/example/pomodoro_22/ui/main/PomodoroPhase;", "timeLeftInMillis", "", "timer", "Landroid/os/CountDownTimer;", "createNotification", "Landroid/app/Notification;", "millisUntilFinished", "createNotificationChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "startForegroundService", "stopForegroundService", "updateNotification", "Companion", "app_debug"})
public final class PomodoroForegroundService extends android.app.Service {
    @org.jetbrains.annotations.Nullable
    private android.os.CountDownTimer timer;
    private long timeLeftInMillis = 0L;
    @org.jetbrains.annotations.NotNull
    private com.example.pomodoro_22.ui.main.PomodoroPhase currentPhase = com.example.pomodoro_22.ui.main.PomodoroPhase.WORK;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_START = "START";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_STOP = "STOP";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_RESET = "RESET";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_SKIP = "SKIP";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_SYNC = "SYNC";
    public static final int NOTIFICATION_ID = 1;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TIMER_CHANNEL_ID = "timer_channel";
    @org.jetbrains.annotations.NotNull
    public static final com.example.pomodoro_22.ui.main.PomodoroForegroundService.Companion Companion = null;
    
    public PomodoroForegroundService() {
        super();
    }
    
    @java.lang.Override
    public int onStartCommand(@org.jetbrains.annotations.Nullable
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void createNotificationChannel() {
    }
    
    private final void startForegroundService(long timeLeftInMillis, com.example.pomodoro_22.ui.main.PomodoroPhase currentPhase) {
    }
    
    private final void stopForegroundService() {
    }
    
    private final void updateNotification(long millisUntilFinished, com.example.pomodoro_22.ui.main.PomodoroPhase currentPhase) {
    }
    
    private final android.app.Notification createNotification(long millisUntilFinished, com.example.pomodoro_22.ui.main.PomodoroPhase currentPhase) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/pomodoro_22/ui/main/PomodoroForegroundService$Companion;", "", "()V", "ACTION_RESET", "", "ACTION_SKIP", "ACTION_START", "ACTION_STOP", "ACTION_SYNC", "NOTIFICATION_ID", "", "TIMER_CHANNEL_ID", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}