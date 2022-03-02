package by.it.academy.MK_JD2_88_2.cw1.storage.api;

public class DBInitializer {

    private static DBInitializer instance;

    private DBInitializer() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Проблемы с загрузкой драйвера");
            return;
        }
    }

    public static DBInitializer getInstance() {
        if (instance == null) {
            synchronized (DBInitializer.class) {
                if (instance == null) {
                    instance = new DBInitializer();
                }
            }
        }
        return instance;
    }
}
