package org.its.events;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.Entities.Order;
import org.its.projections.OrderAmountById;

import javax.inject.Named;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Named("OrderEventDaoImpl")
public class OrderEventDaoImpl implements OrderEventDao {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public OrderEventDaoImpl() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @Override
    public void saveOrderAmount(OrderAmountById orderAmountById) {
        MongoCollection<OrderAmountById> event = database.getCollection("amountById", OrderAmountById.class);
        event.insertOne(orderAmountById);
    }

    @Override
    public List<Order> getById(UUID id) {
        MongoCollection<Order> orders = database.getCollection("orders", Order.class);
        List<Order> order = (List<Order>) orders.find(eq("_id", id));
        return order;
    }

    @Override
    public void updateOrderAmount(OrderAmountById orderAmountById) {
        MongoCollection<OrderAmountById> orders = database.getCollection("amountById", OrderAmountById.class);
        OrderAmountById order = (OrderAmountById) orders.find(eq("orderId", orderAmountById.getId())).first();
        order.setAmount(orderAmountById.getAmount()+order.getAmount());
        orders.replaceOne(eq("orderId", order.getId()), order);
    }
}
