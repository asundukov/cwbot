package ru.my2i.cwbot.hero.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser implements Parser {

  protected static String getFirstStringByRegexp(String msg, String regExp) {
    Pattern p = Pattern.compile(regExp);
    Matcher m = p.matcher(msg);
    if (m.find()) {
      return m.group(1);
    };
    return null;
  }

}
