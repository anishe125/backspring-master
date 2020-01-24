package pack.dao;

import pack.dto.ItemAmountDto;
import pack.dto.ItemCreationDto;
import pack.dto.ItemDto;
import pack.entity.Item;
import org.springframework.stereotype.Repository;
import pack.dto.ItemCreationDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository("sqlite")
public class WarehouseDataService implements ItemDao {

    private static final String CONNECTION_STRING = "jdbc:sqlite:D:/Anna/backspring-master/omg/items.db";
    private Connection connection;

    public WarehouseDataService() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemDto addItem(ItemCreationDto itemCreationDto) {
        Item createdItem = itemCreationDto.toItem();

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO items ('name', 'price', 'actualAmount', 'availableAmount')" +
                            "VALUES (?, ?, ?, ?)"
            );

            statement.setString(1, itemCreationDto.getName());
            statement.setFloat(2, itemCreationDto.getPrice());
            statement.setInt(3, itemCreationDto.getAmount());
            statement.setInt(4, itemCreationDto.getAmount());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                createdItem.setId(resultSet.getInt(1));
                resultSet.close();
                return ItemDto.fromItem(createdItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ItemDto> getAllItems() {
        ResultSet resultSet = null;
        List<ItemDto> items = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM items"
            );
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(ItemDto.fromItem(new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("actualAmount"),
                        resultSet.getInt("availableAmount")
                )));
            }
            resultSet.close();
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ItemDto getItemById(int id) {
        ResultSet resultSet = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM items WHERE id = ?"
            );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ItemDto itemDto;
                itemDto = ItemDto.fromItem(new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getInt("actualAmount"),
                        resultSet.getInt("availableAmount")
                ));
                resultSet.close();
                return itemDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ItemDto updateItemAmount(int id, ItemAmountDto itemAmountDto) {
        ItemDto itemDto = getItemById(id);
        if (itemDto == null) {
            return null;
        }

        PreparedStatement statement = null;
        try {
            if (itemAmountDto.getAmountType().equals("actual")) {
                itemDto.setActualAmount(itemDto.getActualAmount() + itemAmountDto.getAmount());
                statement = connection.prepareStatement(
                        "UPDATE items SET actualAmount = ? WHERE id = ?"
                );
                statement.setInt(1, itemDto.getActualAmount());
                statement.setInt(2, itemDto.getId());
            } else if (itemAmountDto.getAmountType().equals("available")) {
                itemDto.setAvailableAmount(itemDto.getAvailableAmount() + itemAmountDto.getAmount());
                statement = connection.prepareStatement(
                        "UPDATE items SET availableAmount = ? WHERE id = ?"
                );
                statement.setInt(1, itemDto.getAvailableAmount());
                statement.setInt(2, itemDto.getId());
            } else {
                return null;
            }

            int resultSet = statement.executeUpdate();
            if (resultSet != 0) {
                return itemDto;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}