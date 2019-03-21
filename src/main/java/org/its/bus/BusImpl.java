package org.its.bus;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Named("bus")
public class BusImpl implements Bus {
    public <T> void register(Class<T> clazz, MessageConsumer consumer) {
        if(!consumers.containsKey(clazz)){
            consumers.put(clazz, new ArrayList<>());
        }
        consumers.get(clazz).add(o->consumer.handle(o));
    }
    public void send(BusMessage message) {
        if(!consumers.containsKey(message.getClass()))return;
        List<MessageConsumer> result = consumers.get(message.getClass());
        for(MessageConsumer consumer:result){
            try{
                consumer.handle(message);
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
    private HashMap<Class,List<MessageConsumer>> consumers =
            new HashMap<Class, List<MessageConsumer>>();

}
