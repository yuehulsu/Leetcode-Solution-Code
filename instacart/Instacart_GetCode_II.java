package instacart;

import java.io.BufferedInputStream;
import java.util.*;

public class Instacart_GetCode_II {
    public String genPassword(Scanner scanner) {
        String line;
        char[] chars = new char[100];
        int consecutiveEmptyLines = 0, index = 0, x = 0, y = 0;
        List<String> list = new ArrayList<>();
        while ((line = scanner.nextLine()) != null && consecutiveEmptyLines <= 1) {
            if (line.matches("[0-9]+")) { // digits
                if (!list.isEmpty() && x < list.size() && y < list.get(0).length()) {
                    chars[index] = list.get(list.size() - x - 1).charAt(y);
                    list = new ArrayList<>();
                }
                consecutiveEmptyLines = 0;
                index = Integer.parseInt(line);
            } else if (line.indexOf('[') != -1) {
                String[] strs = line.replace("[", "")
                        .replace("]", "").replace(" ", "")
                        .split(",");
                y = Integer.parseInt(strs[0]);
                x = Integer.parseInt(strs[1]);
            } else if (!line.isEmpty()) {
                list.add(line);
            } else {    // empty
                consecutiveEmptyLines++;
            }
        }
        if (!list.isEmpty()) {
            chars[index] = list.get(list.size() - x - 1).charAt(y);
        }
        return new String(chars);
    }

    /* Test case 1:
    Input:
        1
        [5, 6]
        RXIBDIBF
        DVMPXTBG
        BMWAERXR
        UPEIJGMW
        YTALDXDH
        JNPMQUEJ
        XDRHCHWG

        0
        [0, 1]
        HUTQW3
        NLEVCU
    Expected output:
        K
*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        Instacart_GetCode_II c = new Instacart_GetCode_II();
        System.out.println(c.genPassword(scanner));
    }
}