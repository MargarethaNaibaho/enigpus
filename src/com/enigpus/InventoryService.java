package com.enigpus;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    void addBook(List<String> newBookInformation);
    Map<String, List<String>> searchBookByTitle(String title);
    Map<String, List<String>> searchBookById(String id);
    void deleteBookById(String id);
    Map<String, List<String>> getAllBook();
}
