package org.its.projections.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.Entities.Good;
import org.its.projections.GoodCount;

import javax.inject.Named;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Named("GoodCountDaoImpl")
public class GoodCountDaoImpl implements GoodsCountDao {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public GoodCountDaoImpl() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @Override
    public void save(GoodCount count) {
        MongoCollection<GoodCount> goodCount = database.getCollection("goods", GoodCount.class);
        goodCount.insertOne(count);
    }

    @Override
    public Good getByName(String descrizione) {
        MongoCollection<Good> goods = database.getCollection("goods", Good.class);
        Good goodFound = (Good) goods.find(eq("descrizone", descrizione)).first();
        return goodFound;
    }

    @Override
    public void update(GoodCount count) {
        MongoCollection<GoodCount> counts = database.getCollection("goods", GoodCount.class);
        GoodCount goodCount = (GoodCount) counts.find(eq("descrizione", count.getDescrizione())).first();
        goodCount.setQuantity(count.getQuantity());
        counts.replaceOne(eq("descrizone", count.getDescrizione()), goodCount);
    }
}
