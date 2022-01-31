package dao.mongoDAO;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.configuration.ConfigYaml;
import dao.interfaces.DAOItems;
import model.Item;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ItemDAO implements DAOItems {


    @Override
    public List<Item> getAll() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder()
                        .automatic(true).build()));
        MongoCollection<Item> db = load().getCollection("Items",Item.class).withCodecRegistry(pojoCodecRegistry);
        return db.find().into(new ArrayList());
    }

    @Override
    public boolean save(Item t) {
        return false;
    }

    @Override
    public int update(Item t) {
        return 0;
    }

    @Override
    public int deletePurchasesAndItem(Item item) {
        return 0;
    }

    @Override
    public int deleteItem(Item item) {
        return 0;
    }

    @Override
    public Item findItemByID(int id) {
        return null;
    }

    public MongoDatabase load(){
        ConfigYaml configYaml = new ConfigYaml();
        MongoClient mongo = MongoClients.create(configYaml.getRuta());

        return mongo.getDatabase(configYaml.getDatabase());
    }
}
