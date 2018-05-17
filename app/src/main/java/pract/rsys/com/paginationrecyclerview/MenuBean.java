package pract.rsys.com.paginationrecyclerview;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by akash.sharma on 5/16/2018.
 */
@Root(name = "menu",strict = false)
public class MenuBean {

    @ElementList(name = "item",inline = true)
    List<Item> itemList;

    @Root(name = "item" , strict = false)
    public static class Item
    {
        @Element(name = "id",required = true)
        String id;
        @Element(name = "name",required = true)
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
