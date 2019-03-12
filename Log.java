import java.util.ArrayList;

public class Log {

  public static final ArrayList<Log> list = new ArrayList<>();

  String date = "-1";
  String regNr = "-1";
  double taxa = -1;
  double timeUnit = -1;
  int startTime = -1;
  int endTime = -1;
  int parkedTime = -1;
  double debit = -1;

  public Log(String date, String regNr, double taxa, double timeUnit,
             int startTime, int endTime, int parkedTime, double debit) {
    this.date = date;
    this.regNr = regNr;
    this.taxa = taxa;
    this.timeUnit = timeUnit;
    this.startTime = startTime;
    this.endTime = endTime;
    this.parkedTime = parkedTime;
    this.debit = debit;
  }

  public static void addEntry(String date, String regNr, double taxa,
                              double timeUnit, int startTime, int endTime,
                              int parkedTime, double debit) {
    Log logEntry = new Log(date, regNr, taxa, timeUnit, startTime, endTime,
                           parkedTime, debit);
    list.add(logEntry);
  }

}
