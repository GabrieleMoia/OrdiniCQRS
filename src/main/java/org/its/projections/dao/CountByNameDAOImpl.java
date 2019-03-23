package org.its.projections.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.Entities.Order;
import org.its.projections.CountByName;

import javax.inject.Named;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Named("")
public class CountByNameDAOImpl implements CountByNameDAO {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public CountByNameDAOImpl() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @Override
    public void save(CountByName orderNumberByName) {
        MongoCollection<CountByName> orders = database.getCollection("countByName", CountByName.class);
        orders.insertOne(orderNumberByName);
    }

    @Override
    public Order getOrderById(UUID uuid) {
        MongoCollection<Order> orders = database.getCollection("orders", Order.class);
        Order order = (Order) orders.find(eq("_id", uuid)).first();
        return order;
    }

    @Override
    public void update(CountByName orderNumberByName) {
        MongoCollection<CountByName> orders = database.getCollection("countByName", CountByName.class);
        CountByName order = (CountByName) orders.find(eq("nome", orderNumberByName.getNome())).first();
        order.setCount(
                order.getCount() + orderNumberByName.getCount()
        );
        orders.replaceOne(eq("nome", orderNumberByName.getNome()), order);
    }
}
