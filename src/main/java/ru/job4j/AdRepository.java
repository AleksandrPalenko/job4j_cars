package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.model.Advertisement;
import ru.job4j.model.Brand;

import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private final SessionFactory sf;

    public AdRepository(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public List<Advertisement> findAdvForTheLastDay() {
        return this.tx(session ->
                session.createQuery("select ad Advertisement ad where a.created => current_date", Advertisement.class)
                        .list()
        );
    }

    public List<Advertisement> findAdvWithPhoto() {
        return this.tx(session ->
                session.createQuery("select ad from Advertisement ad"
                                + "join fetch cars c "
                                + "where c.photo != null")
                        .list()

        );
    }

    public List<Advertisement> findAdvByBrand(Brand brand) {
        return this.tx(
                session -> session.createQuery(
                                "select ad from Advertisement ad"
                                        + "join fetch ad.cars c"
                                        + "join fetch c.brands b"
                                        + "where b.name = :fName")
                        .setParameter("fName", brand.getId())
                        .list()
        );

    }
}
