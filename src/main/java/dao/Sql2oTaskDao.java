package dao;

import org.sql2o.Sql2o;

public class Sql2oTaskDao implements taskDao {
    private final Sql2o sql2o;

    public Sql2oTaskDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
}
