package org.jaudiotagger.logging;

import org.tinylog.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * For Formatting log output
 *
 * <p>This is not required by jaudiotagger, but its advantage over the default formatter is that all
 * the format for a log entry is on one line, making it much easier to read. To use this formatter
 * with your code edit loggin.properties within your jre/lib folder and modify as follows e.g
 * java.util.logging.ConsoleHandler.formatter = org.jaudiotagger.logging.LogFormatter
 */
public final class LogFormatter extends Formatter {

  // Line separator string.  This is the value of the line.separator
  // property at the moment that the SimpleFormatter was created.
  private final String lineSeparator = System.lineSeparator();

  private final SimpleDateFormat sfDateOut = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss:");
  private final Date date = new Date();

  public LogFormatter() {
    // because proxying
  }

  public final String format(final LogRecord record) {
    final StringBuilder sb = new StringBuilder();

    date.setTime(record.getMillis());

    sb.append(sfDateOut.format(date));

    String recordName;

    if (record.getSourceClassName() != null) {
      recordName = record.getSourceClassName() + ":" + record.getSourceMethodName();
    } else {
      recordName = record.getLoggerName() + ":";
    }
    sb.append(recordName);
    sb.append(":");
    final String message = formatMessage(record);
    sb.append(record.getLevel().getLocalizedName());
    sb.append(": ");
    sb.append(message);
    sb.append(lineSeparator);

    if (record.getThrown() != null) {
      try {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        record.getThrown().printStackTrace(pw);
        pw.close();
        sb.append(sw.toString());
      } catch (Exception e) {
        Logger.trace("Error: {}", e.getMessage(), e);
      }
    }
    return sb.toString();
  }

}
