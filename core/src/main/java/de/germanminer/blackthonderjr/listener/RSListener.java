package de.germanminer.blackthonderjr.listener;

import de.germanminer.blackthonderjr.widgets.RSTimerWidget;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import java.util.Timer;
import java.util.TimerTask;

public class RSListener {
  Timer timer;

  @Subscribe(127)
  public boolean onChatReceive(ChatReceiveEvent e) {
    if (e.chatMessage().getPlainText().contains("kostenpflichtig wiederbelebt") || e.chatMessage()
        .getPlainText().contains("2 Minuten keinen Schaden")) {
      RSTimerWidget.RS_TIME.setVisible(true);
      try {
        this.timer.cancel();
      } catch (NullPointerException exception) {
        System.out.println(exception);
      }
      this.timer = new Timer();
      long countdownDuration = 120000L;
      long startTime = System.currentTimeMillis();
      final long endTime = startTime + countdownDuration;
      this.timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
          long remainingTime = endTime - System.currentTimeMillis();
          if (remainingTime <= 0L) {
            RSListener.this.timer.cancel();
            RSTimerWidget.RS_TIME.setVisible(false);
          } else {
            long time_seconds = remainingTime / 1000L;
            String anzeige = String.format("%02d:%02d", Long.valueOf(time_seconds / 60L), Long.valueOf(time_seconds % 60L));
            if (time_seconds < 10L) {
              RSTimerWidget.RS_TIME.updateAndFlush(Component.text(anzeige).color(NamedTextColor.RED));
            } else if (time_seconds < 30L) {
              RSTimerWidget.RS_TIME.updateAndFlush(Component.text(anzeige).color(NamedTextColor.DARK_RED));
            } else {
              RSTimerWidget.RS_TIME.updateAndFlush(Component.text(anzeige));
            }
          }
        }
      }, 0L, 1000L);
      return false;
    }
    return false;
  }
}
