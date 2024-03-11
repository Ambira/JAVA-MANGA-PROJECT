package org.example;

import org.example.models.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static final String MANGA_TABLE = "manga";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_VOLUMES = "volumes";

    //connect
    private Connection connection;

    public Database(String dbPath) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            initTable();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database.", e);
        }
    }

    //creating table
    private void initTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + MANGA_TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_VOLUMES + " INTEGER)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing the database table.", e);
        }
    }

    public void create(Manga manga) {
        // Check if manga with the same title already exists and prevent duplication
        if (!isMangaExists(manga.getTitle())) {
            String insertRecordSQL = "INSERT INTO " + MANGA_TABLE + " (" + COLUMN_TITLE + ", "
                    + COLUMN_AUTHOR + ", " + COLUMN_STATUS + ", " + COLUMN_VOLUMES + ") VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertRecordSQL)) {
                preparedStatement.setString(1, manga.getTitle());
                preparedStatement.setString(2, manga.getAuthor());
                preparedStatement.setString(3, manga.getStatus());
                preparedStatement.setInt(4, manga.getVolumes());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error creating a manga record.", e);
            }
        } else {
            System.out.println("Manga with the same title already exists. Skipping creation.");
        }
    }

    private boolean isMangaExists(String title) {
        String checkIfExistsSQL = "SELECT COUNT(*) FROM " + MANGA_TABLE + " WHERE " + COLUMN_TITLE + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkIfExistsSQL)) {
            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if manga exists.", e);
        }
    }

    //read
    public List<Manga> read() {
        List<Manga> mangaList = new ArrayList<>();
        String selectData = "SELECT * FROM " + MANGA_TABLE;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectData)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String title = resultSet.getString(COLUMN_TITLE);
                String author = resultSet.getString(COLUMN_AUTHOR);
                String status = resultSet.getString(COLUMN_STATUS);
                int volumes = resultSet.getInt(COLUMN_VOLUMES);

                Manga manga = new Manga(id, title, author, status, volumes);
                mangaList.add(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading manga records.", e);
        }
        return mangaList;
    }

    //update---
    public void update(Manga manga) {
        // Check if manga with the same title already exists (excluding the manga being updated)
        if (!isMangaExists(manga.getTitle(), manga.getId())) {
            String updateRecordSQL = "UPDATE " + MANGA_TABLE + " SET "
                    + COLUMN_TITLE + " = ?, "
                    + COLUMN_AUTHOR + " = ?, "
                    + COLUMN_STATUS + " = ?, "
                    + COLUMN_VOLUMES + " = ? WHERE " + COLUMN_ID + " = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateRecordSQL)) {
                preparedStatement.setString(1, manga.getTitle());
                preparedStatement.setString(2, manga.getAuthor());
                preparedStatement.setString(3, manga.getStatus());
                preparedStatement.setInt(4, manga.getVolumes());
                preparedStatement.setInt(5, manga.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error updating a manga record.", e);
            }
        } else {
            System.out.println("Manga with the same title already exists. Skipping update.");
        }
    }

    private boolean isMangaExists(String title, int idToExclude) {
        String checkIfExistsSQL = "SELECT COUNT(*) FROM " + MANGA_TABLE + " WHERE " + COLUMN_TITLE + " = ? AND " + COLUMN_ID + " != ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkIfExistsSQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, idToExclude);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if manga exists.", e);
        }
    }

    //delete a record
    public void delete(int id) {
        String deleteRecordSQL = "DELETE FROM " + MANGA_TABLE + " WHERE " + COLUMN_ID + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteRecordSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting a manga record.", e);
        }
    }
}
