import java.util.Map;
import java.util.HashMap;
import java.io.*;
//import java.util.Iterator;

public class Signatures {
  Signatures() {
    signatures = new HashMap<String, String>();
  }

  public Map<String, String> ReadSignatures(String file_name) throws IOException {
    File file = new File(file_name);
    FileReader fr = new FileReader(file);
    BufferedReader reader = new BufferedReader(fr);
    String line = reader.readLine();

    while (line != null) {
      int comma = line.indexOf(',');
      int space = line.indexOf(' ');
      if (comma != - 1 && space != -1) {
        signatures.put(line.substring(0, comma), line.substring(space + 1));
      }
      line = reader.readLine();;
    }

    return signatures;
  }

  public void checkSignatureFile(String file_name) throws IOException {
    if (signatures.isEmpty()) {
      return;
    }

    FileOutputStream fos = new FileOutputStream("result.txt", true);
    StringBuilder data = toHex(file_name);

    if (file_name.charAt(0) == '/') {
      for (Map.Entry<String, String> entry : signatures.entrySet()) {
        if (data.toString().startsWith(entry.getValue().toLowerCase())) {
          byte[] buffer = (entry.getKey() + "\n").getBytes();
          fos.write(buffer, 0, buffer.length);
          System.out.println("PROCESSED");
          fos.close();
          return;
        }
      }
      System.out.println("UNDEFINED");
    } else {
      System.out.println("Path must be absolute");
    }

    fos.close();
  }

  private StringBuilder toHex(String file_name) throws IOException {
    FileInputStream input_file = new FileInputStream(file_name);
    StringBuilder data = new StringBuilder();

    int maxLen = maxLength();
    int readByte = input_file.read();

    for (int i = 0; readByte != -1 && i < maxLen; ++i) {
      String hexByte = Integer.toHexString(readByte);
      if (hexByte.length() < 2) {
        data.append("0");
      }
      data.append(hexByte + " ");
      readByte = input_file.read();
    }

    input_file.close();
    return data;
  }

  private int maxLength() {
    int max = 0;

    for (Map.Entry<String, String> entry : signatures.entrySet()) {
      if (entry.getValue().length() > max) {
        max = entry.getValue().length();
      }
    }

    return max;
  }

  private Map<String, String> signatures;
}