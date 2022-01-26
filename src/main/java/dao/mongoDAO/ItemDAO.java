package dao.mongoDAO;

import dao.interfaces.DAOItems;
import model.Item;

import java.util.List;

public class ItemDAO implements DAOItems {
    @Override
    public List<Item> getAll() {
        return null;
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
}
