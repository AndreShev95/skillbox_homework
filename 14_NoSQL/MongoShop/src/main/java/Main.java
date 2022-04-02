import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.codecs.BsonCodecProvider;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.jsr310.Jsr310CodecProvider;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.*;
import java.util.function.Consumer;

public class Main {
    private static final double PRICE = 100.0d;

    public static void main(String[] args) {
        try (MongoClient mongoClient = new MongoClient("127.0.0.1", 27017)) {
            MongoDatabase database = mongoClient.getDatabase("test")
                    .withCodecRegistry(CodecRegistries
                            .fromProviders(PojoCodecProvider.builder()
                                            .register(Shop.class, Good.class)
                                            .build(),
                                    new Jsr310CodecProvider(),
                                    new DocumentCodecProvider(),
                                    new BsonCodecProvider(),
                                    new ValueCodecProvider()));
            MongoCollection<Shop> shops = database.getCollection("Shops", Shop.class);
            shops.drop();
            MongoCollection<Good> goods = database.getCollection("Goods", Good.class);
            goods.drop();

            System.out.println("Добро пожаловать в программу для управления товарами в магазинах.");
            help();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String command = scanner.nextLine();
                if (command.trim().matches("ДОБАВИТЬ_МАГАЗИН\\s+(.+)")) {
                    String nameShop = command.substring(17).trim();
                    addShop(nameShop, shops);
                }
                if (command.trim().matches("ДОБАВИТЬ_ТОВАР\\s+(.+)\\s(\\d+)")) {
                    String textCommand = command.substring(15).trim();
                    int x = textCommand.indexOf(' ');
                    String nameGood = textCommand.substring(0, x).trim();
                    double price = Double.parseDouble(textCommand.substring(x).trim());
                    addGood(nameGood, price, goods);
                }
                if (command.trim().matches("ВЫСТАВИТЬ_ТОВАР\\s+(.+)\\s+(.+)")) {
                    String textCommand = command.substring(16).trim();
                    int x = textCommand.indexOf(' ');
                    String nameGood = textCommand.substring(0, x).trim();
                    String nameShop = textCommand.substring(x).trim();
                    putGood(nameGood, goods, nameShop, shops);
                }
                if (command.trim().matches("СТАТИСТИКА_ТОВАРОВ")) {
                    printStats(shops, goods);
                }
                if (command.trim().matches("ПОМОЩЬ")) {
                    help();
                }
            }
        }
    }
    private static void help(){
        System.out.println("Введите одну из доступных команд: ");
        System.out.println("ДОБАВИТЬ_МАГАЗИН <Название магазина>");
        System.out.println("ДОБАВИТЬ_ТОВАР <Название товара> <Цена товара>");
        System.out.println("ВЫСТАВИТЬ_ТОВАР <Название товара> <Название магазина>");
        System.out.println("СТАТИСТИКА_ТОВАРОВ");
        System.out.println("ПОМОЩЬ");
    }

    private static void addShop(String nameShop, MongoCollection<Shop> collection){
        if(isExistsShop(nameShop, collection)){
            System.out.println("Такой магазин уже существует!");
        }
        else {
            Shop shop = new Shop();
            shop.setName(nameShop);
            collection.insertOne(shop);
            System.out.println("Добавлен магазин - " + nameShop);
        }
    }

    private static void addGood(String nameGood, double price, MongoCollection<Good> collection){
        if(isExistsGood(nameGood, collection)){
            System.out.println("Такой товар уже существует!");
        }
        else {
            Good good = new Good();
            good.setName(nameGood);
            good.setPrice(price);
            collection.insertOne(good);
            System.out.println("Добавлен товар - " + nameGood + " по цене " + price + " рублей");
        }
    }

    private static void putGood(String nameGood, MongoCollection<Good> collectionGood,
                                String nameShop, MongoCollection<Shop> collectionShop){
        if (isExistsGood(nameGood, collectionGood)){
            if (isExistsShop(nameShop, collectionShop)){
                Shop shop = collectionShop.find(Filters.eq("Name", nameShop)).first();
                List<Good> newGoods = new ArrayList<>(shop.getGoodsList());
                Good good = collectionGood.find(Filters.eq("Name", nameGood)).first();
                newGoods.add(good);
                shop.setGoodsList(newGoods);
                Document filterByGradeId = new Document("_id", shop.getId());
                FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().
                        returnDocument(ReturnDocument.AFTER);
                collectionShop.findOneAndReplace(filterByGradeId, shop, returnDocAfterReplace);
                System.out.println("Выставлен товар - " + nameGood + " в магазине " + nameShop);
            }
            else {
                System.out.println("Данный магазин не заведен в базу! Сначала нужно добавить магазин.");
            }
        }
        else {
            System.out.println("Данный товар не заведен в базу! Сначала нужно добавить товар.");
        }
    }

    private static void printStats(MongoCollection<Shop> collectionShop, MongoCollection<Good> collectionGood){
        if (collectionShop.countDocuments() == 0.0) {
            System.out.println("В базе отсутствуют магазины. Сначала нужно добавить магазин.");
        }
        else {
            System.out.println("Общая статистика для всех магазинов.");
            collectionShop.find().forEach((Consumer<Shop>) shop -> {
                String nameShop = shop.getName();
                System.out.println("Магазин " + nameShop + ":");
                int count = shop.getGoodsList().size();
                if (count == 0) {
                    System.out.println("В данном магазине нет товаров.");
                } else {
                    List<Good> listGoods = shop.getGoodsList();
                    double minPrice = 0.0d;
                    double maxPrice = 0.0d;
                    int countCheaper = 0;
                    double sum = 0.0d;

                    for (Good good : listGoods) {
                        double price = good.getPrice();
                        sum += price;
                        if (price < PRICE){
                            countCheaper++;}
                        if (price < minPrice){
                            minPrice = price;}
                        if (price > maxPrice){
                            maxPrice = price;}
                    }
                    double avg = sum / listGoods.size();

                    Good minPriceGood = collectionGood.find(Filters.eq("Price", minPrice)).first();
                    Good maxPriceGood = collectionGood.find(Filters.eq("Price", maxPrice)).first();

                    System.out.println("Общее количество наименований товаров - " + count + " шт.;");
                    System.out.println("Средняя цена товаров - " + avg + " руб.;");
                    if (maxPrice != 0.0d) {
                        System.out.println("Самый дорогой товар - " + maxPriceGood.getName() + " - "
                                + maxPrice + " руб.;");
                    }
                    if (minPrice != 0.0d) {
                        System.out.println("Самый дешевый товар - " + minPriceGood.getName() + " - "
                                + minPrice + " руб.;");
                    }
                    System.out.println("Количество товаров дешевле " + PRICE + " руб. - " + countCheaper + " шт.");
                }
            });
        }
    }

    private static boolean isExistsGood(String name, MongoCollection<Good> collection){
        Good good = collection.find(Filters.eq("Name", name)).first();
        return good != null;
    }

    private static boolean isExistsShop(String name, MongoCollection<Shop> collection){
        Shop shop = collection.find(Filters.eq("Name", name)).first();
        return shop != null;
    }
}