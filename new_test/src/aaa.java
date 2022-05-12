import java.util.ArrayList;
import java.util.List;

public class aaa {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("1");
        names.add("2");
        names.add("3");
        System.out.println(String.join("", names));

        String[] arrStr = new String[]{"a", "b", "c"};
        System.out.println(String.join("-", arrStr));
    }
}