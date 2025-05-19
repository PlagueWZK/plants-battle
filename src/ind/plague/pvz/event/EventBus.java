package ind.plague.pvz.event;

/**
 * @author PlagueWZK
 * description: EventBus
 * date: 2025/5/19 12:57
 */

public class EventBus {
    private static EventBus instance;
    private EventBus(){}
    public static EventBus getInstance(){
        if(instance == null){
            instance = new EventBus();
        }
        return instance;
    }

}
