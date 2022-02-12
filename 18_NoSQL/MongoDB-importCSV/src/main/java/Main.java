import org.bson.BsonDocument;

public class Main {

    static final String CSV_FILE_PATH = "/Users/qweezique/IdeaProjects/java_basics/18_NoSQL/MongoDB-importCSV/src/main/resources/mongo.csv";
    static final String BOOKS_COLLECTION = "books";
    static final String STUDENTS_COLLECTION = "students";


    public static void main(String[] args) throws InterruptedException {
        MongodbClient mongodbClient = new MongodbClient();
//        mongodbClient.printCollection(mongodbClient.getCollection(BOOKS_COLLECTION));
//        mongodbClient.printCollection(mongodbClient.getCollection(STUDENTS_COLLECTION));
        mongodbClient.importCSVfile(CSV_FILE_PATH);
        mongodbClient.printAllCollections();


        //HOMEWORK 18.3 pt. 2
        //Кол-во студентов
        System.out.printf("Задание 1: Количество студентов в базе: %s\n", mongodbClient.studentsCollection.countDocuments());

        //Студентов старше 40
        BsonDocument queryGt40 = BsonDocument.parse("{ age: {$gt: 40}}");
        System.out.printf("Задание 2: Студентов в базе старше 40: %d\n", (int) mongodbClient.studentsCollection.countDocuments(queryGt40));

        //Имя самого молодого студента
        BsonDocument querySortByAge = BsonDocument.parse("{ age: 1}");
        String name = mongodbClient.studentsCollection.find().sort(querySortByAge).limit(1).first().getString("name");
        System.out.printf("Задание 3: Имя самого молодого студента: %s\n", name);

        //Список курсов самого старшего студента
        BsonDocument querySortByAgeReverse = BsonDocument.parse("{ age: -1}");
        String courses = mongodbClient.studentsCollection.find().sort(querySortByAgeReverse).limit(1).first().getString("courses");
        System.out.printf("Задание 4: Список курсов самого старого студента: %s\n", courses);

        mongodbClient.getCollection(STUDENTS_COLLECTION).drop();
    }
}
