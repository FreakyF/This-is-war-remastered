package pl.kielce.tu.drylofudala;

import pl.kielce.tu.drylofudala.persistance.DbContext;

public class Main {
    public static void main(String[] args) {
        try(final DbContext dbContext = new DbContext("ThisIsWarPU")){
            // Perform your database-related operations using the DbContext
        }
    }
}