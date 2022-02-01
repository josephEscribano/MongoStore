package model.converters;

import model.Item;
import org.bson.Document;

public class ItemConverter {
    //clase UUID que tiene un randomUUID
    public Document convertItemDocument(Item item){
        return new Document()
                .append("name",item.getName())
                .append("company",item.getCompany())
                .append("price",item.getPrice());
    }
}
