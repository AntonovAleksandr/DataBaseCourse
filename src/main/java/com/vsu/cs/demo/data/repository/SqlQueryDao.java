package com.vsu.cs.demo.data.repository;

import com.vsu.cs.demo.data.dto.StatisticDto;
import com.vsu.cs.demo.data.entity.Technologies;
import jdk.dynalink.beans.StaticClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlQueryDao {
    private SessionFactory sessionFactory;

    @Autowired
    public SqlQueryDao(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public  List<StatisticDto> getThreeMostPopularTechnologies(){
        Session session = sessionFactory.openSession();

        Query query = session.createSQLQuery(
                "WITH RATING(reiting) as\n" +
                        "         (\n" +
                        "             SELECT count(*)\n" +
                        "             FROM \"my_hh\".public.technologies as tt\n" +
                        "             GROUP BY tt.title\n" +
                        "             ORDER BY count(*) DESC\n" +
                        "             LIMIT 3\n" +
                        "         ),\n" +
                        "     TECHNOLOGYIES (title, rating) as (\n" +
                        "         SELECT tt.title, count(*)\n" +
                        "         FROM \"my_hh\".public.technologies as tt\n" +
                        "         GROUP BY tt.title\n" +
                        "     )\n" +
                        "SELECT distinct title, rating\n" +
                        "FROM TECHNOLOGYIES,\n" +
                        "     RATING\n" +
                        "WHERE RATING.reiting = rating\n" +
                        ";");
        List sqlRes = query.getResultList();
        List<StatisticDto> res = new ArrayList<>();
        for (Object row : sqlRes) {
            Object[] args = (Object[])row;
            res.add(new StatisticDto((String)args[0], ((BigInteger)args[1])));
        }
        return res;
    }

    public  List<StatisticDto> getTopAllTechnologies(){
        Session session = sessionFactory.openSession();

        Query query = session.createSQLQuery(
                "WITH RATING(reiting) as\n" +
                        "         (\n" +
                        "             SELECT count(*)\n" +
                        "             FROM \"my_hh\".public.technologies as tt\n" +
                        "             GROUP BY tt.title\n" +
                        "             ORDER BY count(*) DESC\n" +
                        "         ),\n" +
                        "     TECHNOLOGYIES (title, rating) as (\n" +
                        "         SELECT tt.title, count(*)\n" +
                        "         FROM \"my_hh\".public.technologies as tt\n" +
                        "         GROUP BY tt.title\n" +
                        "     )\n" +
                        "SELECT distinct title, rating\n" +
                        "FROM TECHNOLOGYIES,\n" +
                        "     RATING\n" +
                        "WHERE RATING.reiting = rating\n" +
                        ";");
        List sqlRes = query.getResultList();
        List<StatisticDto> res = new ArrayList<>();
        for (Object row : sqlRes) {
            Object[] args = (Object[])row;
            res.add(new StatisticDto((String)args[0], ((BigInteger)args[1])));
        }
        return res;
    }
}
