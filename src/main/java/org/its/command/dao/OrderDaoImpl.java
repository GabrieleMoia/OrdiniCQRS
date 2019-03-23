package org.its.command.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.Entities.Order;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Named("orderDao")
@Component
public class OrderDaoImpl implements OrderDao {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public OrderDaoImpl() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @Override
    public void save(Order order) {
        MongoCollection<Order> orders = database.getCollection("orders", Order.class);
        orders.insertOne(order);
    }

    @Override
    public Order getById(UUID id) {
        MongoCollection<Order> orders = database.getCollection("orders", Order.class);
        Order order = orders.find(eq("_id", id)).first();
        return order;
    }

    @Override
    public void update(Order order) {
        MongoCollection<Order> orders = database.getCollection("orders", Order.class);
        orders.replaceOne(eq("_id", order.getId()), order);
    }
}
