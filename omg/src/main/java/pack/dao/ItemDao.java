package pack.dao;

import pack.dto.ItemAmountDto;
import pack.dto.ItemCreationDto;
import pack.dto.ItemDto;

import java.util.List;

public interface ItemDao {

    ItemDto addItem(ItemCreationDto item);

    List<ItemDto> getAllItems();

    ItemDto getItemById(int id);

    ItemDto updateItemAmount(int id, ItemAmountDto itemAmountDto);
}