package org.example;

import org.example.models.Manga;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database = new Database("mangaaDb.db");

        // Create Operation

        Manga mangaOnePiece = new Manga(0, "One Piece", "Eiichiro Oda", "ongoing", 70);
        database.create(mangaOnePiece);

        Manga mangaJojo = new Manga(0, "JoJo's Bizarre Adventure", "Hirohiko Araki", "ongoing", 133);
        database.create(mangaJojo);


        // Read Operation
        List<Manga> mangaList = database.read();
        System.out.println("Before Update:");
        for (Manga manga : mangaList) {
            System.out.println(manga.getTitle());
        }

        // Update Operation
        Manga mangaToUpdate = mangaList.get(0); // Update the first manga in the list One Piece
        mangaToUpdate.setTitle("One Piece ワンピース");
        mangaToUpdate.setAuthor("Eiichiro Oda 尾田 栄一郎");
        mangaToUpdate.setStatus("ongoing");
        mangaToUpdate.setVolumes(107);
        database.update(mangaToUpdate);

        // Read again after update
        mangaList = database.read();
        System.out.println("\nAfter Update:");
        for (Manga manga : mangaList) {
            System.out.println(manga.getTitle());
        }

        // create again

        Manga mangaBleach = new Manga(0, "Bleach", "Tite Kubo", "completed", 74);
        database.create(mangaBleach);

        Manga mangaNaruto = new Manga(4, "Naruto", "Masashi Kishimoto", "completed", 72);
        database.create(mangaNaruto);

        Manga manga4 = new Manga(0, "Ouran High School Host Club", "Bisco Hatori", "completed", 18);
        database.create(manga4);

        Manga mangaGintama = new Manga(0, "Gintama", "Hideaki Sorachi", "ongoing", 0);
        database.create(mangaGintama);

        Manga mangaCSM = new Manga(0, "Chainsaw Man", "Tatsuki Fujimoto", "ongoing", 0);
        database.create(mangaCSM);

        Manga mangaJJK = new Manga(0, "Jujutsu Kaisen", "Gege Akutami", "ongoing", 21);
        database.create(mangaJJK);

        Manga mangaDoraemon = new Manga(0, "Doraemon", "Fujiko F. Fujio", "completed", 45);
        database.create(mangaDoraemon);

        Manga mangaDemonSlayer = new Manga(0, "Demon Slayer: Kimetsu no Yaiba", "Koyoharu Gotouge", "completed", 23);
        database.create(mangaDemonSlayer);

        Manga mangaAttackOnTitan = new Manga(0, "Attack on Titan", "Hajime Isayama", "completed", 34);
        database.create(mangaAttackOnTitan);

        Manga mangaDragonBall = new Manga(0, "Dragon Ball", "Akira Toriyama", "completed", 42);
        database.create(mangaDragonBall);

        Manga mangaSlamDunk = new Manga(0, "Slam Dunk", "Takehiko Inoue", "completed", 31);
        database.create(mangaSlamDunk);



        // Delete Operation
        database.delete(4);
    }
}
