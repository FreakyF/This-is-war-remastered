package pl.kielce.tu.drylofudala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kielce.tu.drylofudala.entity.Card;
import pl.kielce.tu.drylofudala.persistance.DatabaseSeeder;
import pl.kielce.tu.drylofudala.persistance.DbContext;
import pl.kielce.tu.drylofudala.persistance.IRepository;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final IRepository<Card> cardRepository = new DbContext<>(Card.class);

    public static void main(String[] args) {
        logger.debug("Application started");
        var databaseSeeder = new DatabaseSeeder(cardRepository);
        databaseSeeder.seed();
    }
}