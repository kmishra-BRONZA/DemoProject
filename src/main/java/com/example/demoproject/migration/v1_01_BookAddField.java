package com.example.demoproject.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOneModel;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

//@ChangeUnit(id="BookStoreDatabase", order = "1", author = "mongock")
@ChangeLog(order = "001")
public class v1_01_BookAddField {

    private static final String COLLECTION = "Book";

    @ChangeSet(order = "01", id = "AddNewField", author = "kmishra")
    public void addNewFieldInBook(MongoTemplate template) {
//        BookRepository

        MongoCollection<Document> collection = template.getCollection(COLLECTION);

        try (MongoCursor<Document> cursor = collection.find().batchSize(2).iterator()) {
            List<UpdateOneModel<Document>> bulkUpdate = new LinkedList<>();

            while (cursor.hasNext()) {
                Document book = cursor.next();
                System.out.println("Book instance ######## : " + book);

                Map<String, Object> field = new HashMap<>();
//                field.put("NumberOfPages", book.get("_id").toString());

                Map<String, Object> attr = new HashMap<>();
                attr.put("number", book.getString("bookName"));
                attr.put("universalNumber", book.getString("authorName"));

                List<Bson> update = new LinkedList<>();
                update.add(set("NoOfPage", new Document(attr)));


                bulkUpdate.add(new UpdateOneModel<>(eq(book.get("_id")), combine(update)));
//                bulkUpdates.add(new UpdateOneModel<>(eq(rscAttribute.getObjectId("_id")), combine(updates)));

                if (bulkUpdate.size() == 2) {
                    collection.bulkWrite(bulkUpdate);
                    bulkUpdate.clear();
                }
            }

            if (!bulkUpdate.isEmpty()) {
                collection.bulkWrite(bulkUpdate);
            }
        }
    }

}
