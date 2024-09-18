import java.util.Scanner;

public class Program {
  private static String[] calendar = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    short point = 0;

    String[] students = new String[10];
    short student_index = 0;

    String[] schedule = new String[10];
    short schedule_index = 0;

    String[] attendance = new String[10];
    short attendance_index = 0;

    while (point < 3) {
      String input = sc.nextLine();
      if (input.equals(".")) {
        ++point;
        continue;
      }
      if (point == 0) {
        students[student_index++] = input;
      } else if (point == 1) {
        schedule[schedule_index++] = input;
      } else if (point == 2) {
        attendance[attendance_index++] = input;
      }
    }
    schedule = sortSchedule(schedule);
//    for (String s : schedule)
//      System.out.println(s);

    int[] days = PrintHead(schedule);

    sc.close();
  }

  private static int[] PrintHead(String[] schedule) {
    for (short i = 0; i < 10; ++i) {
      System.out.print(" ");
    }

    int[] days = new int[45];
    int idx = 0;

    int date = 1;
    while (date < 31) {
      for (short i = 0; schedule[i] != null && i < schedule[i].length(); ++i) {
        char[] ch_schdl = schedule[i].toCharArray();
        if (ch_schdl[1] == ' ') {
          int temp = checkDay("" + ch_schdl[2] + ch_schdl[3], date);
          if (temp < 31) {
            System.out.print(ch_schdl[0] + ":00 " + ch_schdl[2] + ch_schdl[3] + "  " + temp + "|");
            days[idx++] = date;
          }
        } else {
          date = checkDay("" + ch_schdl[5] + ch_schdl[6], date);System.out.print(schedule[i] + "  " + date + "|");
        }
      }
      date += 7;
    }
    return days;
  }

  private static int checkDay(String day, int date) {
    for (short k = 0; k < 7; ++k) {
      if (calendar[k].equals(day)) {
        return date += k;
      }
    }

    return date;
  }

  private static String[] sortSchedule(String[] schedule) {
    for (int i = 0; schedule[i] != null && i < schedule.length; ++i) {
      for (int j = i; j > 0 && schedule[j - 1].toCharArray()[0] > schedule[j].toCharArray()[0]; --j) {
        String temp = schedule[j];
        schedule[j] = schedule[j - 1];
        schedule[j - 1] = temp;
      }
    }
    String[] retval = new String[schedule.length];
    int idx = 0;

    for (String DAY : calendar) {
      for (int i = 0; schedule[i] != null && i < schedule.length; ++i) {
        char[] ch_schdl = schedule[i].toCharArray();
        if (DAY.equals("" + ch_schdl[2] + ch_schdl[3])) {
          retval[idx++] = schedule[i];
        }
      }
    }

    return retval;
  }

  private static void printAttendance(String[] students, String[] attendance) {
    attendance = sortStudents();

    for (int i = 0; students[i] != null && i < students.length; ++i) {

    }
  }

  private static String[] sortStudents(String[] attendance, int[] days) {
    for (int i = 0; attendance[i] != null && i < attendance.length; ++i) {
      for (int j = i; j > 0 && attendance[j - 1].toCharArray()[0] > attendance[j].toCharArray()[0]; --j) {
        String temp = attendance[j];
        attendance[j] = attendance[j - 1];
        attendance[j - 1] = temp;
      }
    }
    return attendance;
  }

  private static int[] visits(String name, String[] attendance) {
    int[] retval = new int[attendance.length];
    char[] char_att = attendance[0];

    for(String str : attendance) {
      if (name.)
    }

    return retval;
  }

}