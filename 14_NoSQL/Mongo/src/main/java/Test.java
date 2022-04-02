import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.FileReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws Exception {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

        MongoDatabase database = mongoClient.getDatabase("test");

        // Создаем коллекцию
        MongoCollection<Document> collection = database.getCollection("Students");

        // Удалим из нее все документы
        collection.drop();

        String csvFilename = "src/main/resources/mongo.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFilename), ',', '"');
        List<String[]> allStrings = reader.readAll();
        List<Student> allStudents = allStrings.stream().map(Student::new).collect(Collectors.toList());
        for (Student student : allStudents) {
            Document document = new Document()
                    .append("FullName", student.getFullName())
                    .append("Age", student.getAge())
                    .append("Courses", student.getCourses());
            // Вставляем документ в коллекцию
            collection.insertOne(document);
        }

        // Выводим отчет из базы данных
        System.out.println("Файл успешно загружен. Общее количество студентов в базе: " + collection.countDocuments() +
                "\n" + "Количество студентов старше 40 лет: " + findCountByAgeOlder(collection, 40) +
                "\n" + "Имя самого молодого студента: " + findNameOfYoungerStudent(collection) +
                "\n" + "Список курсов самого старого студента: \n" + findCoursesOfOlderStudent(collection));
    }

    private static int findCountByAgeOlder(MongoCollection collection, int age){
        BsonDocument query = BsonDocument.parse("{Age : {$gt : " + age + "}}");
        List<Document> list = new ArrayList<>();
        collection.find(query).forEach((Consumer<Document>) list::add);
        return list.size();
    }

    private static String findNameOfYoungerStudent(MongoCollection collection){
        BsonDocument query = BsonDocument.parse("{Age : 1}");
        String name = "";
        FindIterable<Document> docs = collection.find().sort(query).limit(1);
        for (Document doc : docs){
            name = doc.getString("FullName");
        }
        return name;
    }

    private static String findCoursesOfOlderStudent(MongoCollection collection){
        BsonDocument query = BsonDocument.parse("{Age : -1}");
        List<String> courses = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        FindIterable<Document> docs = collection.find().sort(query).limit(1);
        for (Document doc : docs){
            courses = doc.getList("Courses", String.class);
        }
        for (String course : courses){
            builder.append(course + "\n");
        }
        return builder.toString();
    }
}
