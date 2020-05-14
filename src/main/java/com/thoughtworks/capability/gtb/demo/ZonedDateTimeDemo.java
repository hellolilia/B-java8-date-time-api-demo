package com.thoughtworks.capability.gtb.demo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 脑洞会议系统v3.0
 * 读入的字符串是伦敦的本地时间
 * 输出的字符串是北京的本地时间
 *
 * @author itutry
 * @create 2020-05-12_22:35
 */
public class ZonedDateTimeDemo {

  public static void main(String[] args) {
    String timeStr = "2020-04-01 14:30:00";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime londonLocal = LocalDateTime.parse(timeStr, formatter);

    // 将读进来的本地时间转成带伦敦时区的时间
    ZonedDateTime londonZoned = ZonedDateTime.of(londonLocal, ZoneId.of("Europe/London"));

    // 将伦敦时区的时间转成北京时区的时间
    ZonedDateTime beijingZoned = londonZoned.withZoneSameInstant(ZoneId.systemDefault());
    // 从北京时区的时间获取北京的本地时间
    LocalDateTime meetingTime = beijingZoned.toLocalDateTime();

    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(meetingTime)) {
      LocalDateTime tomorrow = now.plusDays(1);
      int newDayOfYear = tomorrow.getDayOfYear();
      meetingTime = meetingTime.withDayOfYear(newDayOfYear);

      String showTimeStr = formatter.format(meetingTime);
      System.out.println(showTimeStr);
    } else {
      System.out.println("会议还没开始呢");
    }
  }
}