package org.its.projections.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.projections.GoodNotAvailable;

import javax.inject.Named;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Named("GoodNotAvailableDaoImpl")
public class GoodNotAvailableDaoImpl implements GoodsCountDao {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public GoodNotAvailableDaoImpl() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @Override
    public void save(GoodNotAvailable count) {
        MongoCollection<GoodNotAvailable> GoodNotAvailable = database.getCollection("goodsNotAvailable", GoodNotAvailable.class);
        GoodNotAvailable.insertOne(count);
    }

    @Override
    public GoodNotAvailable getByName(String descrizione) {
        MongoCollection<GoodNotAvailable> goods = database.getCollection("goodsNotAvailable", GoodNotAvailable.class);
        GoodNotAvailable goodFound = (GoodNotAvailable) goods.find(eq("descrizione", descrizione)).first();
        return goodFound;
    }

    @Override
    public void update(GoodNotAvailable count) {
        MongoCollection<GoodNotAvailable> counts = database.getCollection("goodsNotAvailable", GoodNotAvailable.class);
        GoodNotAvailable GoodNotAvailable = (GoodNotAvailable) counts.find(eq("descrizione", count.getDescrizione())).first();
        counts.replaceOne(eq("descrizione", count.getDescrizione()), count);
    }
}
