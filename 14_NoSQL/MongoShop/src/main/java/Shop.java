import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shop {

    private ObjectId id;
    @BsonProperty(value = "Name")
    private String name;
    @BsonProperty(value = "ListGoods")
    private List<Good> goodsList;

    public Shop() {
        goodsList = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Good> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Good> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return Objects.equals(getId(), shop.getId()) && Objects.equals(getName(), shop.getName()) && Objects.equals(getGoodsList(), shop.getGoodsList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGoodsList());
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsList=" + goodsList +
                '}';
    }
}
