package org.its.domain.Good.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.Entities.Good;
import org.springframework.stereotype.Component;

import javax.inject.Named;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Named("goodDao")
@Component
public class GoodDaoImpl implements GoodDao{

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public GoodDaoImpl(){
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @Override
    public void save(Good good) {
        MongoCollection<Good> goods = database.getCollection("goods", Good.class);
        goods.insertOne(good);
    }

    @Override
    public Good getById(String descrizione) {
        MongoCollection<Good> goods = database.getCollection("goods", Good.class);
        Good good = goods.find(eq("descrizione", descrizione)).first();
        return good;
    }

    @Override
    public void update(Good good) {
        MongoCollection<Good> goods = database.getCollection("goods", Good.class);
        goods.replaceOne(eq("descrizione", good.getDescrizione()), good);
    }
}
