package org.its.orders;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.mongo.MongoHelper;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.awt.print.Book;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
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
    public UUID save(Order order) {
        MongoCollection<Order> orders = database.getCollection("orders", Order.class);
        orders.insertOne(order);
        return order.getId();
    }

    @Override
    public Order getById(UUID id) {
            MongoCollection<Order> orders = database.getCollection("orders", Order.class);
            Order order = orders.find(eq("id", id)).first();
            return order;
    }
}
