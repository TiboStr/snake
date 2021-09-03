package game;

public interface ModelInterface {

    void addListener(ViewInterface view);
    void notifyListeners();

}
