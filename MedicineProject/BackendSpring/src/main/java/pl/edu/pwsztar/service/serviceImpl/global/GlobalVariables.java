package pl.edu.pwsztar.service.serviceImpl.global;

public class GlobalVariables {
    private static volatile GlobalVariables instance;

    public final Integer notificationTime = 10;
    public final Integer maxDelayTime = 5;
    public final Integer testAddingTime = 97;
    public final Integer acceptingTime = 10;


    public static GlobalVariables getInstance() {
        if(instance == null) {
            synchronized (GlobalVariables.class) {
                if (instance == null) {
                    instance = new GlobalVariables();
                }
            }
        }
        return instance;
    }
}
