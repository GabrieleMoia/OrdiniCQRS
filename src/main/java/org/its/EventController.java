package org.its;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.its.projections.AmountById;
import org.its.projections.CountByName;
import org.its.projections.GoodCount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Controller
@RequestMapping("/api/ordine")
public class EventController {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;

    public EventController() {
        pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017),
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        database = mongoClient.getDatabase("myDb");
    }

    @RequestMapping(
            path = "/{id}/getAmount",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public AmountById getAmountOfOrdersById(@PathVariable("id") String id) {
        MongoCollection<AmountById> data = database.getCollection("amountById", AmountById.class);
        return (AmountById) data.find(eq("idOrdine", UUID.fromString(id))).first();
    }

    @RequestMapping(
            path = "/{nome}/getCountByName",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public CountByName getNumberOfOrdersByID(@PathVariable("nome") String nome) {
        MongoCollection<CountByName> data = database.getCollection("countByName", CountByName.class);
        return data.find(eq("nome", nome)).first();
    }

    @RequestMapping(
            path = "/{nome}/getGoodCount",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public GoodCount getGoodCount(@PathVariable("nome") String nome) {
        MongoCollection<GoodCount> data = database.getCollection("goodCount", GoodCount.class);
        return data.find(eq("descrizione", nome)).first();
    }
}
