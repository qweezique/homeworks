import java.util.List;

public interface Node {

    List<String> getChildren();

    String getValue(String link);
}
