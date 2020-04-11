import java.util.*;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

public class MongoDB {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public MongoDB(){
        mongoClient = new MongoClient();
        db =  mongoClient.getDatabase("local");
    }

    private void Collections(){
        for (String x: db.listCollectionNames() ){
            System.out.println("Collection: "+x);
        }
    }

    //
    //SECTION LABOLATORIUM
    //

    private long task1(){
        MongoCollection<Document> business = db.getCollection("business");
        BasicDBObject query = new BasicDBObject("stars", 5);
        long result = business.countDocuments(query);
        return result;
    }

    private void task2(){
        MongoCollection<Document> business = db.getCollection("business");
        AggregateIterable<Document> ResultDocuments = business.aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("categories", "Restaurants")),
                Aggregates.group("$city", Accumulators.sum("count", 1)),
                Aggregates.sort((Sorts.descending("count")))
                )
        );

        for(Document x : ResultDocuments) {
            System.out.println(x.getString("_id") + " : " + x.getInteger("count"));
        }
    }

    public void task3(){
        MongoCollection<Document> business = db.getCollection("business");
        AggregateIterable<Document> ResultDocuments = business.aggregate(Arrays.asList(
                Aggregates.match(Filters.and(
                        Filters.eq("categories", "Hotels"),
                        Filters.eq("attributes.Wi-Fi", "free"),
                        Filters.gte("stars", 4.5)
                )),
                Aggregates.group("$state", Accumulators.sum("count", 1))

        ));
        for(Document x: ResultDocuments){
            System.out.println(x.getString("_id") + " : " + x.getInteger("count"));
        }
    }

    public void task4(){
        MongoCollection<Document> review = db.getCollection("review");
        Object ResultPerson = Objects.requireNonNull(review.aggregate(Arrays.asList(
                Aggregates.match(Filters.gte("stars", 4.5)),
                Aggregates.group("$user_id", Accumulators.sum("count", 1)),
                Aggregates.sort(Sorts.descending("count"))) ).first()).get("_id");

        String name = db.getCollection("user").find(Filters.eq("user_id",ResultPerson))
                .first().get("name").toString();
        System.out.println(name);
    }

    public void task5(){
        MongoCollection<Document> review = db.getCollection("review");
        long r1, r2, r3;
        r1 = review.countDocuments(Filters.gte("votes.funny", 1));
        r2 = review.countDocuments(Filters.gte("votes.cool", 1));
        r3 = review.countDocuments(Filters.gte("votes.useful", 1));

        System.out.println("Funny: " + r1 + " Cool: " + r2 + " Useful: " + r3);
    }

    //
    //SECTION ZADANIE DOMOWE
    //

    public void task6a(){
        MongoCollection<Document> business = db.getCollection("business");
        AggregateIterable<Document> cities = business.aggregate(Arrays.asList(
                Aggregates.group("$city"),
                Aggregates.sort(Sorts.ascending("_id"))));
        for(Document x: cities){
            System.out.println(x.getString("_id")) ;
        }
    }

    public void task6b(){
        MongoCollection<Document> review = db.getCollection("review");
        System.out.println(review.countDocuments(Filters.gte("date", "2011-01-01")));
    }

    public void task6c(){
        MongoCollection<Document> business = db.getCollection("business");
        AggregateIterable<Document> closedBusinesses = business.aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("open", false)),
                Aggregates.project(Projections.include("name", "full_address", "stars"))
        ));

        for(Document x: closedBusinesses){
            System.out.println(x.getString("name") + ", " + x.getString("full_address") + ", " +
                    x.getDouble("stars"));
        }
    }

    public void task6d(){
        MongoCollection<Document> user = db.getCollection("user");

        AggregateIterable<Document> users = user.aggregate(Arrays.asList(
                Aggregates.match(Filters.or(
                        Filters.eq("votes.funny", 0),
                        Filters.eq("votes.useful", 0)
                )),
                Aggregates.group("$name"),
                Aggregates.sort(Sorts.ascending("_id" ))));

        for(Document x: users){
            System.out.println(x) ;
        }
    }

    public void task6e(){
        MongoCollection<Document> tip = db.getCollection("tip");

        AggregateIterable<Document> tips = tip.aggregate(Arrays.asList(
                Aggregates.match(Filters.and(
                        Filters.gte("date", "2012-01-01"),
                        Filters.lt("date", "2013-01-01")
                )),
                Aggregates.group("$business_id", Accumulators.sum("nrOfTips", 1)),
                Aggregates.sort(Sorts.ascending("nrOfTips" ))));

        for(Document x: tips){
            System.out.println(x) ;
        }
    }

    public void task6f(){
        MongoCollection<Document> business = db.getCollection("business");

        AggregateIterable<Document> avgStars = business.aggregate(Arrays.asList(
                Aggregates.match(Filters.gte("stars", 4)),
                Aggregates.project(Projections.include("name", "stars"))));

        for(Document x: avgStars){
            System.out.println(x) ;
        }
    }

    public void task6g(){
        MongoCollection<Document> business = db.getCollection("business");
        BasicDBObject query = new BasicDBObject("stars", 3);
        DeleteResult result = business.deleteMany(query);
        System.out.println("Number of deleted documents: " + result.getDeletedCount());
    }

    public static void main(String[] args) {
        MongoDB mongoDB = new MongoDB();
        mongoDB.task6g();
    }
}
