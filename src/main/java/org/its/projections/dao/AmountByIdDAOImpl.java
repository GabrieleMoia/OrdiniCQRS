package org.its.projections.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.Entities.Order;
import org.its.projections.AmountById;

import javax.inject.Named;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Named("AmountByIdDAOImpl")
public class AmountByIdDAOImpl implements AmountByIdDAO {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public AmountByIdDAOImpl() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @Override
    public void save(AmountById amount) {
        MongoCollection<AmountById> orders = database.getCollection("amountById", AmountById.class);
        orders.insertOne(amount);
    }

    @Override
    public void update(AmountById amount) {
        MongoCollection<AmountById> orders = database.getCollection("amountById", AmountById.class);
        AmountById order = (AmountById) orders.find(eq("idOrdine", amount.getIdOrdine())).first();
        order.setAmount(
                order.getAmount()+amount.getAmount()
        );
        orders.replaceOne(eq("idOrdine", amount.getIdOrdine()), order);
    }

    @Override
    public List<Order> getById(UUID id) {
        MongoCollection<Order> orders = database.getCollection("orders", Order.class);
        List<Order> order = (List<Order>) orders.find(eq("id", id));
        return order;
    }
}
